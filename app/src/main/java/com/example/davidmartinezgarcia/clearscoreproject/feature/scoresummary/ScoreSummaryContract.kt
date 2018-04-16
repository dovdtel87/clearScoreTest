package com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary

import com.example.davidmartinezgarcia.clearscoreproject.model.ScoreSummary

/**
 * Created by david.martinezgarcia on 12/04/2018.
 */
interface ScoreSummaryContract {
    interface View : com.example.davidmartinezgarcia.clearscoreproject.feature.common.view.View {
        fun showScore(value: ScoreSummary)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter : com.example.davidmartinezgarcia.clearscoreproject.feature.common.presenter.Presenter {
        fun retrieveScore()
    }
}