package com.example.android.pb_rj.final_project.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.pb_rj.databinding.ItemUser2Binding
import com.example.android.pb_rj.final_project.mvp.model.image.IImageLoader
import com.example.android.pb_rj.final_project.mvp.presenter.list.IUserListPresenter
import com.example.android.pb_rj.final_project.mvp.view.list.UserItemView

class UsersRVAdapter(val presenter: IUserListPresenter, val imageLoader: IImageLoader<ImageView>) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemUser2Binding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    override fun onViewRecycled(holder: ViewHolder) = holder.unbind()

    inner class ViewHolder(val vb: ItemUser2Binding) : RecyclerView.ViewHolder(vb.root), UserItemView {
        override var pos = -1

        override fun setLogin(text: String) = with(vb) {
            tvLogin.text = text
        }

        override fun loadAvatar(url: String) = with(vb) {
            imageLoader.loadInto(url, ivAvatar)
        }

        fun unbind() = with(vb) { ivAvatar.setImageDrawable(null) }
    }
}