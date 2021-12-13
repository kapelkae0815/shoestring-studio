package com.example.shoestringstudio.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.shoestringstudio.R
import com.example.shoestringstudio.database.entities.Project

class Adapter:
    RecyclerView.Adapter<Adapter.ProjectHolder>(){

    class ProjectHolder(val projectView: View): RecyclerView.ViewHolder(projectView)

    private var projects: List<Project>? = null

    fun setProjects(
        project: List<Project>
    ) {
        projects = project
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectHolder {
        val view = (LayoutInflater.from(parent.context).inflate(R.layout.project_recyclerview_item, parent, false))
        return ProjectHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectHolder, position: Int) {
        val current = projects?.get(position)
        val textView = holder.projectView.findViewById<TextView>(R.id.projectName)

        if (current != null) {
            textView.text = current.name
        }
    }

    override fun getItemCount(): Int {
        return projects?.size?:0
    }

}