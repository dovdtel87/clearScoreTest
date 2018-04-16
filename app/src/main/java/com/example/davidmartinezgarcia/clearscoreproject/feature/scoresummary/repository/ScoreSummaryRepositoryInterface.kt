package com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.repository

import com.example.davidmartinezgarcia.clearscoreproject.model.ScoreSummary
import io.reactivex.Observable

/**
 * Created by david.martinezgarcia on 13/04/2018.
 */
interface ScoreSummaryRepositoryInterface {
    fun retrieveScores(): Observable<ScoreSummary>
}