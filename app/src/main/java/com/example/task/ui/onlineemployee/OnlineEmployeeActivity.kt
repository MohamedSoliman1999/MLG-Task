package com.example.task.ui.onlineemployee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import com.example.task.databinding.ActivityOnlineEmployeeBinding
import com.example.task.network.Resource
import com.example.task.ui.adapter.EmployeeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
@AndroidEntryPoint
class OnlineEmployeeActivity : AppCompatActivity() {
    private var _binding:ActivityOnlineEmployeeBinding?=null
    private val binding get() = _binding!!
    private val viewModel by viewModels<OnlineEmployeeViewModel>()
    private val employeeAdapter = EmployeeAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnlineEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
        viewModel.getEmployeeData()
        initViews()
    }
    private fun initViews(){
        binding.employeeRv.apply {
            layoutManager = LinearLayoutManager(this@OnlineEmployeeActivity)
            adapter = employeeAdapter
        }

        binding.employeeRv.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) &&
                    newState == RecyclerView.SCROLL_STATE_IDLE &&
                    viewModel.canLoad
                ) {
                    viewModel.getEmployeeData()
                }
            }
        })
    }
    private fun initObserver(){
        lifecycleScope.launch {
            viewModel.employeeResponse.collectLatest {
                when(it){
                    is Resource.Idle->{
                        binding.progressBar.visibility = View.GONE
                    }
                    is Resource.Loading->{
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Error->{
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@OnlineEmployeeActivity, "${it.throwable?.localizedMessage}", Toast.LENGTH_SHORT).show()}
                    is Resource.Success->{
                        binding.progressBar.visibility = View.GONE
                        if(it.data!=null&&!it.data.data.isNullOrEmpty()){
                            employeeAdapter.submitList(it.data.data)
                        }else{
                            viewModel.canLoad=false
                        }
                    }
                }
            }
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}