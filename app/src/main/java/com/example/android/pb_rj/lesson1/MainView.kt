package com.example.android.pb_rj

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

interface MainView : MvpView {

    /**
     *  функция для отображения счетчика кнопки
     *
     *  @param counter номер счетчика
     *  @param counterNo счетчик
     */

    /**
     * Показывает приветственное сообщение
     * для пользоватлея
     */
    @OneExecution
    fun showOnBoarding()

    /**
     * Показывает значение счетчика 1
     * @param counter значение
     */
    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = "counter1")
    fun showCounter1(counter : String)

    /**
     * Показывает значение счетчика 3
     * @param counter значение
     */
    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = "counter2")
    fun showCounter2(counter : String)

    /**
     * Показывает значение счетчика 2
     * @param counter значение
     */
    @StateStrategyType(AddToEndSingleTagStrategy::class, tag = "counter3")
    fun showCounter3(counter : String)

    /**
     * Показывает сообщение об изменении счетчика
     */
    @Skip
    fun showCounterMessage()

//    fun setButtonText(index : Int, text : String)


}