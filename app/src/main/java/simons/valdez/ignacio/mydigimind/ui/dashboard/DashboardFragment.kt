package simons.valdez.ignacio.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import simons.valdez.ignacio.mydigimind.R
import simons.valdez.ignacio.mydigimind.databinding.FragmentDashboardBinding
import simons.valdez.ignacio.mydigimind.ui.Task
import simons.valdez.ignacio.mydigimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var button_tiempo:Button = root.findViewById(R.id.button_time) as Button

        button_tiempo.setOnClickListener{
            var cal = Calendar.getInstance()
            var timeSetListener = TimePickerDialog.OnTimeSetListener{ view: TimePicker?, hourOfDay: Int, minute: Int ->
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(Calendar.MINUTE, minute)

                button_tiempo.text = SimpleDateFormat("HH:mm").format(cal.time)

            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        var button_done = root.findViewById(R.id.button_done) as Button
        var button_time = root.findViewById(R.id.button_time) as Button
        var textView_title = root.findViewById(R.id.textView_name) as TextView
        var checkMonday = root.findViewById(R.id.checkBox_monday) as CheckBox
        var checkTuesday = root.findViewById(R.id.checkBox_tuesday) as CheckBox
        var checkWednesday = root.findViewById(R.id.checkBox_wednesday) as CheckBox
        var checkThursday = root.findViewById(R.id.checkBox_thursday) as CheckBox
        var checkFriday = root.findViewById(R.id.checkBox_friday) as CheckBox
        var checkSaturday = root.findViewById(R.id.checkBox_saturday) as CheckBox
        var checkSunday = root.findViewById(R.id.checkBox_sunday) as CheckBox

        button_done.setOnClickListener{
            var title = textView_title.text.toString()
            var time = button_time.text.toString()
            var days = ArrayList<String>()

            if(checkMonday.isChecked){
                days.add("Monday")
            }
            if(checkTuesday.isChecked){
                days.add("Tuesday")
            }
            if(checkWednesday.isChecked){
                days.add("Wednesday")
            }
            if(checkThursday.isChecked){
                days.add("Thursday")
            }
            if(checkFriday.isChecked){
                days.add("Friday")
            }
            if(checkSaturday.isChecked){
                days.add("Saturday")
            }
            if(checkSunday.isChecked){
                days.add("Sunday")
            }

            var task = Task(title, days, time)

            HomeFragment.tasks.add(task)

            Toast.makeText(root.context, "new task added", Toast.LENGTH_LONG).show()

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}