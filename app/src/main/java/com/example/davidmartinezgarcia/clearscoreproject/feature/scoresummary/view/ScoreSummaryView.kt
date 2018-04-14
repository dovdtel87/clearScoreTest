package com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.example.davidmartinezgarcia.clearscoreproject.R
import com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.ScoreSummaryContract
import com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.presenter.ScoreSummaryPresenter
import com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.repository.ScoreSummaryRepository
import com.example.davidmartinezgarcia.clearscoreproject.model.ScoreSummary

class ScoreSummaryView : AppCompatActivity(), ScoreSummaryContract.View {

    lateinit var mPresenter : ScoreSummaryContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        initNetworkCalls();
    }

    private fun initNetworkCalls() {
        mPresenter = ScoreSummaryPresenter(this, ScoreSummaryRepository()) //TODO INTEGRATE WITH DAGGER
        mPresenter.retrieveScore()
    }

    override fun showScore(value: ScoreSummary) {
        Toast.makeText(baseContext, "score value: "+value.creditReportInfo.score, Toast.LENGTH_LONG).show()
    }

    override fun onApiError(exception: Throwable) {
        Toast.makeText(baseContext, "Error retrieving API info", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        mPresenter.onViewDestroyed()
        super.onDestroy()
    }
}
