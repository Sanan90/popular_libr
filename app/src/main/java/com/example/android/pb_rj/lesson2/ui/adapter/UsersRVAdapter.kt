package com.example.android.pb_rj.lesson2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.pb_rj.databinding.UserItemBinding
import com.example.android.pb_rj.lesson2.mvp.presenter.list.IUsersListPresenter
import com.example.android.pb_rj.lesson2.mvp.view.list.UserItemView


class UsersRVAdapter(val presenter: IUsersListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: UserItemBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {
        override var pos = -1

        override fun setLogin(text: String) = with(vb) {
            tvLogin.text = text
        }

    }
}

/**
 * Таким образом, адаптер не имеет ссылок на данные
 * и полностью делегирует процесс наполнения View в Presenter,
 * так как ViewHolder реализует интерфейс UserItemView
 * и передаётся в функцию bindView в качестве этого интерфейса.
 * Для вызова itemClickListener используется функция invoke(),
 * так как он может быть равен null.
 * А также это связано с синтаксическими ограничениями, не позволяющими
 * иначе осуществить вызов nullable-значения функционального типа.
 * Эта функция есть у любого значения функционального типа,
 * и её вызов вызывает саму функцию, которая и считается этим значением.
 * Проще говоря, presenter.itemClickListener?.invoke(holder)
 * вызовет itemClickListener, если он не равен null.
 * */