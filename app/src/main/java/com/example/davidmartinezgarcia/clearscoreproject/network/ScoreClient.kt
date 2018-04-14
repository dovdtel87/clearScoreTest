package com.example.davidmartinezgarcia.clearscoreproject.network

import com.example.davidmartinezgarcia.clearscoreproject.BuildConfig.API_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by david.martinezgarcia on 13/04/2018.
 */
class ScoreClient {

    companion object {
        private var sScoreService = initRetrofit().create(ScoreService::class.java)

        private fun initRetrofit(): Retrofit {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(API_URL)
                    .build()
        }

        fun getInstance(): ScoreService {
            return sScoreService
        }
    }
}