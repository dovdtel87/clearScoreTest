package com.example.davidmartinezgarcia.clearscoreproject.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * Created by david.martinezgarcia on 16/04/2018.
 */
class GsonFactory {
    companion object {
        private val GSON = GsonBuilder().create()

        fun getInstance(): Gson {
            return GSON
        }
    }
}