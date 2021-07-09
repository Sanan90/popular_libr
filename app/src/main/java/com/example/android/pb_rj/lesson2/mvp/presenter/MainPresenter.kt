package com.example.android.pb_rj.lesson2.mvp.presenter


import com.example.android.pb_rj.lesson2.mvp.navigation.IScreens
import com.example.android.pb_rj.lesson2.mvp.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backPressed() {
        router.exit()
    }

}

/**
 * Мы реализовали интерфейс IUserListPresenter классом UsersListPresenter,
 * где и содержатся данные и логика по наполнению View.
 * Сюда же делегируется получение количества элементов списка через getCount().
 * В остальном всё просто:
 *
 * 1. При первом присоединении View вызываем метод init(),
 *    в котором напишем все операции по инициализации View;
 *
 * 2. Затем получаем данные из репозитория;
 *
 * 3. Отдаём их презентеру списка;
 *
 * 4. Командуем View обновить список.
 *
 * Далее оставляем заготовку слушателя клика.
 * */

