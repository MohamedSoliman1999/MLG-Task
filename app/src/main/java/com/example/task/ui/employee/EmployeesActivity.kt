package com.example.task.ui.employee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.task.databinding.ActivityEmployeesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import android.R
import android.util.Log
import android.widget.Toast
import com.example.task.model.entity.Department
import com.example.task.model.entity.Employee

@AndroidEntryPoint
class EmployeesActivity : AppCompatActivity() {
    private var _binding: ActivityEmployeesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<EmployeeViewModel>()
    private val itemsAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(this, R.layout.simple_list_item_1)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEmployeesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initObserver()
    }

    private fun initViews() {
        binding.employeesRv.adapter = itemsAdapter
        binding.departmentNameEt.setText(intent.getStringExtra("departmentName") ?: "")
        binding.departmentNameEt.isEnabled = false
        binding.newEmployeeBtn.setOnClickListener {
            if (binding.employeeNameEt.text.toString().isNotEmpty() &&
                binding.emailEt.text.toString().isNotEmpty() &&
                binding.hireDateEt.text.toString().isNotEmpty()
            ) {
                Log.e("currentDepartment","${currentDepartment!=null}")
                if(currentDepartment!=null){
                    var employees= currentDepartment?.employees?.toMutableList()
                    if (employees==null) employees= mutableListOf()
                    val employeeId=viewModel.getLastEmployID()
                    val employee=Employee(
                        employeeId,
                        binding.employeeNameEt.text.toString(),
                        binding.emailEt.text.toString(),
                        binding.hireDateEt.text.toString()
                    )
                    employees.add(
                        employee
                    )
                    viewModel.saveNewEmployID(employeeId+1)
                    currentDepartment=Department(currentDepartment!!.name,employees.toList())
                    viewModel.insertDepartment(currentDepartment!!)
                    itemsAdapter.add( "name: ${employee.name}\nEmail: ${employee.email}\nDate: ${employee.hireDate}")
                    itemsAdapter.notifyDataSetChanged()
                }
            }else{
                Toast.makeText(this, "Please fill the required data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    var currentDepartment: Department? = null
    private fun initObserver() {
        viewModel.getDepartmentByName(intent.getStringExtra("departmentName") ?: "")
        lifecycleScope.launch {
            viewModel.department.filterNotNull().collectLatest { department ->
                currentDepartment = department
                val items =
                    department.employees?.map { "name: ${it.name}\nEmail: ${it.email}\nDate: ${it.hireDate}" }
                if (items != null) {
                    itemsAdapter.clear()
                    itemsAdapter.addAll(items)
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}