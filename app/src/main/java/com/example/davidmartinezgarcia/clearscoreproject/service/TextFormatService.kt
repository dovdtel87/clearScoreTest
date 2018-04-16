package com.example.davidmartinezgarcia.clearscoreproject.service

import android.content.Context
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.text.style.ForegroundColorSpan
import android.text.style.TextAppearanceSpan
import com.example.davidmartinezgarcia.clearscoreproject.R

/**
 * Created by david.martinezgarcia on 14/04/2018.
 */
class TextFormatService {
    companion object {

        @ColorRes
        val COLOR_ORANGE: Int = R.color.orange
        @ColorRes
        val COLOR_YELLOW: Int = R.color.yellow
        @ColorRes
        val COLOR_LIGHT_GREEN: Int = R.color.light_green
        @ColorRes
        val COLOR_GREEN: Int = R.color.green

        fun formatScore(context: Context, score: Int, maxScore: Int): CharSequence {
            return Truss()
                    .append(context.getString(R.string.your_credit_score_is))
                    .append("\n")
                    .pushSpan(ForegroundColorSpan(ContextCompat.getColor(context, getColor(score.toFloat() / maxScore.toFloat()))))
                    .pushSpan(TextAppearanceSpan(context, R.style.TextAppearance_score))
                    .append(score)
                    .append("\n")
                    .popSpan()
                    .popSpan()
                    .append(String.format(context.getString(R.string.out_off), maxScore))
                    .build()
        }

        private fun getColor(percentage: Float): Int {
            val color: Int
            when (percentage) {
                in (0f..0.25f) -> color = COLOR_ORANGE
                in (0.25f..0.50f) -> color = COLOR_YELLOW
                in (0.50f..0.75f) -> color = COLOR_LIGHT_GREEN
                in (0.75f..1f) -> color = COLOR_GREEN
                else -> color = COLOR_ORANGE
            }

            return color
        }
    }
}