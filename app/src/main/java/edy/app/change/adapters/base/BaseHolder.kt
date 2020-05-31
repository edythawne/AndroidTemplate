package edy.app.change.adapters.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import edy.app.change.BR

class BaseHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    private val handler = HandlerAdapter()

    fun bind(obj: Any?) {
        binding.setVariable(BR.m, obj)
        binding.setVariable(BR.h, handler)
        binding.executePendingBindings()
    }

}