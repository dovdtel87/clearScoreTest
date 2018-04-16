package com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.repository

import com.example.davidmartinezgarcia.clearscoreproject.model.ScoreSummary
import com.example.davidmartinezgarcia.clearscoreproject.network.ScoreClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by david.martinezgarcia on 13/04/2018.
 */
class ScoreSummaryRepository : ScoreSummaryRepositoryInterface {

    override fun retrieveScores(): Observable<ScoreSummary> {
        return ScoreClient.getInstance()
                .getScore()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}