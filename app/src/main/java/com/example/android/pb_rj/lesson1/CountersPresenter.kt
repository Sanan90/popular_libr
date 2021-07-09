package com.example.android.pb_rj

import moxy.MvpPresenter

//class Presenter( val view: View, val model: Model) {
class CountersPresenter(private val countersModel: CountersModel) : MvpPresenter<MainView>() {

    /**
     * Задаем начальное значение состояние счетчиков
     * */
    override fun onFirstViewAttach() {
        viewState.showCounter1(CountersMapper.map(0))
        viewState.showCounter2(CountersMapper.map(0))
        viewState.showCounter3(CountersMapper.map(0))

        viewState.showOnBoarding()
    }

    fun incrementCounter1() =
        countersModel.incrementCounter(counterId = 0)
            .map ( CountersMapper::map )
            .subscribe(::onIncrementCounter1Success, ::onIncrementCounter1Error)

    fun onIncrementCounter1Success (counter :String) =
        viewState::showCounter1

    fun onIncrementCounter1Error(error : Throwable) {

    }



    fun incrementCounter2() =
        countersModel.incrementCounter(counterId = 0)
            .map ( CountersMapper::map )
            .subscribe(::onIncrementCounter2Success, ::onIncrementCounter2Error)

    fun onIncrementCounter2Success (counter :String) =
        viewState::showCounter2

    fun onIncrementCounter2Error(error : Throwable) {

    }

    fun incrementCounter3() =
        countersModel.incrementCounter(counterId = 0)
            .map ( CountersMapper::map )
            .subscribe(::onIncrementCounter3Success, ::onIncrementCounter3Error)

    fun onIncrementCounter3Success (counter :String) =
        viewState::showCounter3

    fun onIncrementCounter3Error(error : Throwable) {

    }

//    fun counterClick(id : Int) {
//        when(id) {
//            0 -> {
//                val nextValue = model.next(0)
//                view.setButtonText(0, nextValue.toString())
//            }
//            1 -> {
//                val nextValue = model.next(1)
//                view.setButtonText(1, nextValue.toString())
//            }
//            2 -> {
//                val nextValue = model.next(2)
//                view.setButtonText(2, nextValue.toString())
//            }
//        }
//    }

}