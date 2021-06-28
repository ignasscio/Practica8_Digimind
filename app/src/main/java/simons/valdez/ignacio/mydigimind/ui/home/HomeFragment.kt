package simons.valdez.ignacio.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.w3c.dom.Text
import simons.valdez.ignacio.mydigimind.R
import simons.valdez.ignacio.mydigimind.databinding.FragmentHomeBinding
import simons.valdez.ignacio.mydigimind.ui.*
import java.util.zip.Inflater

class HomeFragment : Fragment() {
    private var adapter:AdapterTasks?= null
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    companion object{
        var tasks:ArrayList<Task> = ArrayList<Task>()
        var first = true
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if(first){
            fillTasks()
            first = false
        }

        adapter = AdapterTasks(root.context, tasks)

        val gridView:GridView = root.findViewById(R.id.gridView_reminders)

        gridView.adapter = adapter

        return root
    }

    fun fillTasks(){
        tasks.add(Task("Practice 1", arrayListOf("Monday","Sunday"), "10:00"))
        tasks.add(Task("Practice 2", arrayListOf("Monday","Sunday"), "11:00"))
        tasks.add(Task("Practice 3", arrayListOf("Monday","Sunday"), "12:00"))
        tasks.add(Task("Practice 4", arrayListOf("Monday","Sunday"), "13:00"))
        tasks.add(Task("Practice 5", arrayListOf("Monday","Sunday"), "14:00"))
        tasks.add(Task("Practice 6", arrayListOf("Monday","Sunday"), "15:00"))
        tasks.add(Task("Practice 7", arrayListOf("Monday","Sunday"), "16:00"))
        tasks.add(Task("Practice 8", arrayListOf("Monday","Sunday"), "17:00"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private class AdapterTasks:BaseAdapter{
        var tasks = ArrayList<Task>()
        var context:Context?= null

        constructor(context:Context, tasks:ArrayList<Task>){
            this.context = context
            this.tasks = tasks
        }

        override fun getCount(): Int {
            return this.tasks.size
        }

        override fun getItem(position: Int): Any {
            return this.tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var task:Task = this.tasks[position]
            var inflator = LayoutInflater.from(this.context)
            var vista = inflator.inflate(R.layout.task_view, null)

            var title = vista.findViewById(R.id.textView_titulo) as TextView
            var days = vista.findViewById(R.id.textView_days) as TextView
            var time = vista.findViewById(R.id.textView_time) as TextView

            title.setText(task.title)
            days.setText(task.days.toString())
            time.setText(task.time)

            return vista

        }


    }
}