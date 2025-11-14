package com.example.offlinefirstapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.offlinefirstapp.ui.UserAdapter
import com.example.offlinefirstapp.ui.UserViewModel
import com.example.offlinefirstapp.ui.UserViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as UserApplication).repository)
    }

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        setupSearch()

        lifecycleScope.launch {
            viewModel.users.collect { users ->
                userAdapter.submitList(users)
            }
        }
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_users)
        recyclerView.adapter = userAdapter
    }

    private fun setupSearch() {
        val searchView = findViewById<SearchView>(R.id.search_view)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchUsers(newText.orEmpty())
                return true
            }
        })
    }
}