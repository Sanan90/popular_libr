package com.example.android.pb_rj.lesson2.mvp.presenter

import com.example.android.pb_rj.lesson2.Scheduler
import com.example.android.pb_rj.lesson2.mvp.model.GithubUsersRepo
import com.example.android.pb_rj.lesson2.mvp.model.entity.GithubUser
import com.example.android.pb_rj.lesson2.mvp.navigation.IScreens
import com.example.android.pb_rj.lesson2.mvp.presenter.list.IUsersListPresenter
import com.example.android.pb_rj.lesson2.mvp.repo.IGithubUsersRepo
import com.example.android.pb_rj.lesson2.mvp.repo.UserRepository
import com.example.android.pb_rj.lesson2.mvp.view.UsersView
import com.example.android.pb_rj.lesson2.mvp.view.list.UserItemView
import com.example.android.pb_rj.lesson2.ui.navigation.AndroidScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter


class UsersPresenter(
    val userRepository: UserRepository,
    val router: Router,
    private val schedulers : Scheduler
) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUsersListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    private var disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { view ->
            val user = userRepository.fetchUserById(view.pos.toString())
                .subscribe { user ->
            router.navigateTo(AndroidScreens.user(user))}
        }
    }

    fun loadData() {

        disposable +=
            userRepository
                .fetchUsers()
                .observeOn(schedulers.background())
                .map { it.map (UserMapper::map) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(schedulers.main())
                .subscribe(viewState::showUsers)

    }



//    fun loadData() {
//        usersRepo.getUsers()
//            .observeOn(uiScheduler)
//            .subscribe({ users ->
//                usersListPresenter.users.clear()
//                usersListPresenter.users.addAll(users)
//                viewState.updateList()
//            }, {
//                println("Error: ${it.message}")
//            })
//    }




    fun backPress(): Boolean {
        router.exit()
        return true
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
 *
 * Для обработки нажатия клавиши «Назад» добавляем функцию backPressed().
 * Она возвращает Boolean, где мы передаём обработку выхода с экрана роутеру.
 * */




//class UsersPresenter(
//    private val userRepository : UserRepository, // Источник данных (Модель)
//    private val router : Router
//): MvpPresenter<UsersView>() {
//
//    override fun onFirstViewAttach() {
//        viewState.showUsers(userRepository.fetchUsers())
//    }
//
//    fun displayUser(user : User) =
//        router.navigateTo(UserScreen(user.id))
//}