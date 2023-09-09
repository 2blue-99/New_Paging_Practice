package com.example.paging_practice.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging_practice.data.repository.MyRepository
import com.example.paging_practice.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MyViewModel by viewModels{
        ViewModelFactory(MyRepository())
    }
    val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val myAdapter : MyAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        binding.myRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myAdapter
        }


        binding.newLayout.setOnRefreshListener {
            binding.newLayout.isRefreshing = false
        }

        lifecycleScope.launch {
            viewModel.getList().collectLatest{
                myAdapter.submitData(it)
            }
        }
    }
}