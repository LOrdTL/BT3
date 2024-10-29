package com.example.bt3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale


class StudentAdapter(private val studentList: MutableList<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    private val studentListFull: List<Student> =
        ArrayList(studentList) // Full copy for search purposes
    // Create a full copy for searching

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.tvName.text = student.name
        holder.tvStudentId.text = student.studentId
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun filter(text: String) {
        var text = text
        studentList.clear()
        if (text.length <= 2) {
            studentList.addAll(studentListFull) // Show all students when text length <= 2
        } else {
            text = text.lowercase(Locale.getDefault())
            for (student in studentListFull) {
                if (student.name.lowercase(Locale.getDefault())
                        .contains(text) || student.studentId.lowercase(
                        Locale.getDefault()
                    ).contains(text)
                ) {
                    studentList.add(student)
                }
            }
        }
        notifyDataSetChanged()
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var tvStudentId: TextView = itemView.findViewById(R.id.tvStudentId)
    }
}
