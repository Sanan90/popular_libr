package com.example.android.pb_rj.final_project.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.pb_rj.databinding.FragmentRepository2Binding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import com.example.android.pb_rj.final_project.mvp.model.entity.GithubRepository
import com.example.android.pb_rj.final_project.mvp.presenter.RepositoryPresenter
import com.example.android.pb_rj.final_project.mvp.view.RepositoryView
import com.example.android.pb_rj.final_project.ui.App
import com.example.android.pb_rj.final_project.ui.BackButtonListener


class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    companion object {
        private const val REPOSITORY_ARG = "repository"

        fun newInstance(repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }
        }
    }

    private var vb: FragmentRepository2Binding? = null

    val presenter: RepositoryPresenter by moxyPresenter {
        val repository = arguments?.getParcelable<GithubRepository>(REPOSITORY_ARG) as GithubRepository
        RepositoryPresenter(repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentRepository2Binding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun init() {}

    override fun setId(text: String) {
       vb?.tvId?.text = text
    }

    override fun setTitle(text: String) {
        vb?.tvTitle?.text = text
    }

    override fun setForksCount(text: String) {
        vb?.tvForksCount?.text = text
    }

    override fun backPressed() = presenter.backPressed()
}