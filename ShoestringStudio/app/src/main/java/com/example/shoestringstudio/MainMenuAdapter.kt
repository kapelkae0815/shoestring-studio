package com.example.shoestringstudio

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.shoestringstudio.database.Repository
import com.example.shoestringstudio.database.relationships.UserWithProjects

class MainMenuAdapter:
    RecyclerView.Adapter<MainMenuAdapter.ProjectHolder>(){
    class ProjectHolder(val projectView: View): RecyclerView.ViewHolder(projectView)
    private var projects: List<UserWithProjects>? = null
    private lateinit var repository: Repository

    private lateinit var context: Context
    fun setProjects(
        project: List<UserWithProjects>?
    ) {
        projects = project
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectHolder {
        context = parent.context

        val view = (LayoutInflater.from(parent.context).inflate(R.layout.project_recyclerview_item, parent, false))
        repository = Repository.getInstance(context.applicationContext as Application)
        return ProjectHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectHolder, position: Int) {
        val current = projects?.get(position)?.projects?.get(position)
        val textView = holder.projectView.findViewById<TextView>(R.id.projectName)
        val buttonDeleteView = holder.projectView.findViewById<Button>(R.id.projectDeleteButton)
        val buttonEditView = holder.projectView.findViewById<Button>(R.id.projectEditButton)
        if (current != null) {
            textView.text = current.name
            buttonDeleteView.setOnClickListener{
                Log.i("test:", "button clicked projectId = " + current.projectId.toString())
                deleteProject(current.projectId)
            }
            buttonEditView.setOnClickListener { v: View ->
                v.findNavController().navigate(MainMenuFragmentDirections.actionMainMenuFragmentToTrackEditorFragment(current.projectId!!))
            }
        }
    }

    override fun getItemCount(): Int {
        return projects?.size?:0
    }
    private fun deleteProject(id: Long?) {
        if (id != null && repository != null) {
            repository.deleteProject(id)
        }

    }

}