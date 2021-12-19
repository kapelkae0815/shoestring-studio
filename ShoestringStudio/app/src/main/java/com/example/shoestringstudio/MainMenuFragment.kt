package com.example.shoestringstudio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoestringstudio.database.Repository
import com.example.shoestringstudio.database.ViewModel
import com.example.shoestringstudio.database.entities.Project
import com.example.shoestringstudio.database.relationships.UserWithProjects
import com.example.shoestringstudio.databinding.FragmentMainMenuBinding
import java.util.*

class MainMenuFragment : Fragment() {

    private lateinit var binding: FragmentMainMenuBinding
    private lateinit var recyclerAdapter: MainMenuAdapter
    private lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentMainMenuBinding>(
            inflater, R.layout.fragment_main_menu, container, false)

        binding.buttonAddProject.setOnClickListener() { v: View ->
            val project = Project(null, "ProjectName", Date(), 0, 0)
            Repository(activity?.application!!).insertProject(project)
        }

        getProjects()
        return binding.root
    }


    private fun getProjects() {
        recyclerAdapter = MainMenuAdapter()
        binding.recyclerView.apply {
            setHasFixedSize(true)
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = recyclerAdapter
        }
        val viewModelProvider = ViewModelProvider(this)
        viewModel = viewModelProvider.get(ViewModel::class.java)
        // Pass in the user id signed into the app
        viewModel.getAllProjects(0).observe(viewLifecycleOwner, object:
            Observer<List<UserWithProjects>> {
            private val adapter = recyclerAdapter
            override fun onChanged(t: List<UserWithProjects>?) {
                if(t != null) {
                    adapter.setProjects(t)
                }
            }
        })
    }
}