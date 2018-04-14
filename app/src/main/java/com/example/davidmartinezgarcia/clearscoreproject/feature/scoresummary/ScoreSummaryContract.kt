package com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary

import com.example.davidmartinezgarcia.clearscoreproject.model.ScoreSummary

/**
 * Created by david.martinezgarcia on 12/04/2018.
 */
class ScoreSummaryContract {
    interface View  :  com.example.davidmartinezgarcia.clearscoreproject.feature.common.view.View {
       fun showScore(value : ScoreSummary)
    }
    interface Presenter : com.example.davidmartinezgarcia.clearscoreproject.feature.common.presenter.Presenter {
        fun retrieveScore()
    }
}