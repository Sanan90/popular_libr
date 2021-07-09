package com.example.android.pb_rj

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.android.pb_rj.R.layout.activity_counters
import com.example.android.pb_rj.databinding.ActivityCountersBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class CountersActivity : MvpAppCompatActivity(activity_counters), MainView {


    var vb: ActivityCountersBinding? = null

    private val presenter by moxyPresenter {
        CountersPresenter(countersModel = CountersModel(CountersData()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityCountersBinding.inflate(layoutInflater)
            .also { vb -> setContentView(vb.root) }
            .apply {
                buttonOne.setOnClickListener{presenter.incrementCounter1() }
                buttonTwo.setOnClickListener{presenter.incrementCounter2() }
                buttonThree.setOnClickListener{presenter.incrementCounter3() }
            }

//        val listener = View.OnClickListener {
//            when(it.id) {
//                vb?.buttonOne?.id -> presenter.counterClick(0)
//                vb?.buttonTwo?.id -> presenter.counterClick(1)
//                vb?.buttonThree?.id -> presenter.counterClick(2)
//            }
//        }
//
//        vb?.buttonOne?.setOnClickListener(listener)
//        vb?.buttonTwo?.setOnClickListener(listener)
//        vb?.buttonThree?.setOnClickListener(listener)
    }

//    override fun setButtonText(index: Int, text: String) {
//        when (index) {
//            0 -> vb?.buttonOne?.text = text
//            1 -> vb?.buttonTwo?.text = text
//            2 -> vb?.buttonThree?.text = text
//        }
//    }

    override fun showOnBoarding() {
        AlertDialog
            .Builder(this)
            .setMessage("Нажимайте на кнопки")
            .create()
            .show()
    }

    override fun showCounter1(counter: String) {
        vb?.buttonOne?.text = counter
    }

    override fun showCounter2(counter: String) {
        vb?.buttonTwo?.text = counter
    }

    override fun showCounter3(counter: String) {
        vb?.buttonThree?.text = counter
    }

    override fun showCounterMessage() {
        Toast.makeText(this, "Счетчик увеличен", Toast.LENGTH_SHORT).show()
    }
}