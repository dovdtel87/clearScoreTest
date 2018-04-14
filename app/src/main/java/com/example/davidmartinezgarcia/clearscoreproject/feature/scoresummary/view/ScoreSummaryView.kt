package com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.TextView
import com.example.davidmartinezgarcia.clearscoreproject.R
import com.example.davidmartinezgarcia.clearscoreproject.feature.common.view.donut.DonutView
import com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.ScoreSummaryContract
import com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.presenter.ScoreSummaryPresenter
import com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.repository.ScoreSummaryRepository
import com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.repository.exceptions.MaxScoreZeroException
import com.example.davidmartinezgarcia.clearscoreproject.model.ScoreSummary
import com.example.davidmartinezgarcia.clearscoreproject.service.TextFormatService
import kotlinx.android.synthetic.main.content_main.*

class ScoreSummaryView : AppCompatActivity(), ScoreSummaryContract.View {

    lateinit var mPresenter : ScoreSummaryContract.Presenter
    lateinit var mProgressBar : ProgressBar
    lateinit var mDonutView: DonutView
    lateinit var mScoreText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        mProgressBar = progress_bar
        mDonutView = donut_view
        mScoreText = text_score

        initNetworkCalls();
    }

    private fun initNetworkCalls() {
        mPresenter = ScoreSummaryPresenter(this, ScoreSummaryRepository()) //TODO INTEGRATE WITH DAGGER
        mPresenter.retrieveScore()
    }

    override fun showProgress() {
        mProgressBar.visibility = VISIBLE
    }

    override fun hideProgress() {
        mProgressBar.visibility = GONE
    }

    override fun showScore(value: ScoreSummary) {
        val score : Int = value.creditReportInfo.score
        val maxScore : Int = value.creditReportInfo.maxScoreValue
        mScoreText.text = TextFormatService.formatScore(this, score, maxScore)
        mDonutView.setPercentage(score.toFloat() / maxScore.toFloat())
        mScoreText.visibility = VISIBLE
        mDonutView.visibility = VISIBLE
    }

    override fun onApiError(exception: Throwable) {
        hideProgress()
        if(exception is MaxScoreZeroException) {
            mScoreText.text = getString(R.string.api_error_max_score_0)
        } else {
            mScoreText.text = getString(R.string.api_error)
        }

        mScoreText.visibility = VISIBLE
    }

    override fun onDestroy() {
        mPresenter.onViewDestroyed()
        super.onDestroy()
    }
}
