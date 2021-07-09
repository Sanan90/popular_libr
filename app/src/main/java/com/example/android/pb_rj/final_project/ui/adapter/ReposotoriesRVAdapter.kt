package com.example.android.pb_rj.final_project.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.pb_rj.databinding.ItemRepository2Binding
import com.example.android.pb_rj.final_project.mvp.presenter.list.IRepositoryListPresenter
import com.example.android.pb_rj.final_project.mvp.view.list.RepositoryItemView

class ReposotoriesRVAdapter(val presenter: IRepositoryListPresenter) : RecyclerView.Adapter<ReposotoriesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemRepository2Binding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemRepository2Binding) : RecyclerView.ViewHolder(vb.root), RepositoryItemView {
        override var pos = -1
        override fun setName(text: String) = with(vb) {
            tvName.text = text
        }
    }
}