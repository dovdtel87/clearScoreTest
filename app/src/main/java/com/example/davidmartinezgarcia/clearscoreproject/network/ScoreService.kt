package com.example.davidmartinezgarcia.clearscoreproject.network

import com.example.davidmartinezgarcia.clearscoreproject.model.ScoreSummary
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by david.martinezgarcia on 13/04/2018.
 */
interface ScoreService {
    @GET("prod/mockcredit/values")
    fun getScore(): Observable<ScoreSummary>
}