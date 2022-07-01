package com.example.task.ui.home

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.task.ui.employee.EmployeesActivity
import com.example.task.databinding.ActivityHomeBinding
import com.example.task.model.entity.Department
import com.example.task.ui.onlineemployee.OnlineEmployeeActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var itemsAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
        initViews()
    }
    private fun initObserver(){
        lifecycleScope.launch {
            viewModel.allDepartment.filterNotNull().collectLatest { employees ->
                itemsAdapter =
                    ArrayAdapter<String>(this@HomeActivity, R.layout.simple_list_item_1, employees.map { it.name })
                binding.departmentRv.adapter = itemsAdapter
            }
        }
    }
    private fun initViews(){
        binding.onlineEmployeeBtn.setOnClickListener {
            startActivity(Intent(this, OnlineEmployeeActivity::class.java))
        }
        binding.addDepartmentBtn.setOnClickListener {
            val departmentName=binding.departmentNameEt.text.toString()
            if(departmentName.isNullOrEmpty()){
                Snackbar.make(binding.root,"Please enter department name",Snackbar.LENGTH_LONG).show()
            }else{
                val department= Department(binding.departmentNameEt.text.toString(),null)
                viewModel.insertDepartment(department)
                binding.departmentNameEt.text.clear()
            }
        }
        binding.departmentRv.setOnItemClickListener { parent, view, position, id ->
            val departmentName:String=itemsAdapter.getItem(position)!!
            Intent(this, EmployeesActivity::class.java).apply {
                putExtra("departmentName",departmentName)
                startActivity(this)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}