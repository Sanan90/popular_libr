package com.example.android.pb_rj.lesson2.mvp.presenter.list

import com.example.android.pb_rj.lesson2.mvp.view.list.IItemView

interface IListPresenter<V> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

/**
 * Здесь V представляет собой тип View для строки списка,
 * а itemClickListener — функция, принимающая на вход эту самую View.
 * Таким образом, при обработке клика мы получаем от View позицию
 * и находим требуемый элемент.
 * По аналогии с интерфейсом IItemView мы создали интерфейс IListPresenter,
 * куда вынесли общие для всех списков вещи:
 * .слушатель клика;
 * .функция получения количества элементов;
 * .функция наполнения View.
 * */