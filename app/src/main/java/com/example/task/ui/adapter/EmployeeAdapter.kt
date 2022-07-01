package com.example.task.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task.R
import com.example.task.databinding.EmployeeItemBinding
import com.example.task.model.datatransfer.EmployeeResponse

class EmployeeAdapter:ListAdapter<EmployeeResponse, EmployeeAdapter.EmployeeViewHolder>(EmployeeDiffUtil){
    inner class EmployeeViewHolder(private val binding:EmployeeItemBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(position:Int){
            val item=currentList[position]
            binding.emailTv.text = "Email: ${item.email}"
            binding.nameTv.text = "Email: ${item.email}"
            Glide.with(binding.root)
                .load(item.avatar)
                .placeholder(R.drawable.animated_loading_img)
                .into(binding.employeeIv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = EmployeeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(position)
    }
}
object EmployeeDiffUtil: DiffUtil.ItemCallback<EmployeeResponse>() {
    override fun areItemsTheSame(oldItem: EmployeeResponse, newItem: EmployeeResponse): Boolean {
        return oldItem.id==oldItem.id
    }

    override fun areContentsTheSame(oldItem: EmployeeResponse, newItem: EmployeeResponse): Boolean {
        return oldItem.id==oldItem.id
    }

}