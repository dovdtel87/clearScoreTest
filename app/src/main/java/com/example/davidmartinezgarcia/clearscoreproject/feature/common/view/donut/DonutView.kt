package com.example.davidmartinezgarcia.clearscoreproject.feature.common.view.donut

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.support.v4.content.ContextCompat
import com.example.davidmartinezgarcia.clearscoreproject.R
import android.graphics.SweepGradient
import android.support.annotation.ColorRes

/**
 * Created by david.martinezgarcia on 14/04/2018.
 */
class DonutView(context: Context, private val attributeSet: AttributeSet) : View(context, attributeSet) {

    companion object {
        @ColorRes
        val COLOR_BLACK : Int = R.color.black
        @ColorRes
        val COLOR_ORANGE : Int = R.color.orange
        @ColorRes
        val COLOR_YELLOW : Int = R.color.yellow
        @ColorRes
        val COLOR_LIGHT_GREEN : Int = R.color.light_green
        @ColorRes
        val COLOR_GREEN : Int = R.color.green

        const val MAXIMUM_ANGLE : Int = 360
    }

    private var innerCircleMargin: Float = 0f
    private var outerCircleMargin: Float = 0f
    private var strokeInnerCircle : Float = 0f
    private var strokeOuterCircle : Float = 0f
    private var outerCircleColor: Int = 0
    private var innerCircleColor_0_90: Int = 0
    private var innerCircleColor_90_180: Int = 0
    private var innerCircleColor_180_270: Int = 0
    private var innerCircleColor_270_360: Int = 0
    private var innerArcStartingAngle = 0f
    private var outerCircleX : Float = 0f
    private var outerCircleY : Float = 0f
    private var outerCircleRadius : Float = 0f
    private var innerArcStartingX : Float = 0f
    private var innerArcEndingX : Float = 0f
    private var innerArcStartingY : Float = 0f
    private var innerArcEndingY : Float = 0f
    private var angleToComplete : Float = 0f

    init {
        initialiseAttributes()
    }

    private fun initialiseAttributes() {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.DonutView)

        innerCircleMargin = java.lang.Float.valueOf(typedArray.getDimension(R.styleable.DonutView_innerCircleMargin, 30f))
        outerCircleMargin = java.lang.Float.valueOf(typedArray.getDimension(R.styleable.DonutView_outerCircleMargin, 0f))
        strokeInnerCircle = java.lang.Float.valueOf(typedArray.getDimension(R.styleable.DonutView_strokeInnerCircle, 20f))
        strokeOuterCircle = java.lang.Float.valueOf(typedArray.getDimension(R.styleable.DonutView_strokeOuterCircle, 10f))
        outerCircleColor = typedArray.getColor(R.styleable.DonutView_outerCircleColor, ContextCompat.getColor(context, COLOR_BLACK))
        innerCircleColor_0_90 = typedArray.getColor(R.styleable.DonutView_innerCircleColor_0_90, ContextCompat.getColor(context, COLOR_ORANGE))
        innerCircleColor_90_180 = typedArray.getColor(R.styleable.DonutView_innerCircleColor_90_180, ContextCompat.getColor(context, COLOR_YELLOW))
        innerCircleColor_180_270 = typedArray.getColor(R.styleable.DonutView_innerCircleColor_180_270, ContextCompat.getColor(context, COLOR_LIGHT_GREEN))
        innerCircleColor_270_360 = typedArray.getColor(R.styleable.DonutView_innerCircleColor_270_360, ContextCompat.getColor(context, COLOR_GREEN))
        innerArcStartingAngle = typedArray.getFloat(R.styleable.DonutView_innerArcStartingAngle, 270f)

        typedArray.recycle()
    }

    fun setPercentage(percentage : Float) {
        angleToComplete = transformPercentageToDegrees(percentage)
    }

    private fun transformPercentageToDegrees(percentage : Float) : Float {
       return percentage * MAXIMUM_ANGLE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        initializeParameters()

        drawOuterCircle(canvas)
        drawInnerCircle(canvas, angleToComplete)
    }

    private fun initializeParameters() {
        outerCircleX = width.toFloat() / 2
        outerCircleY = height.toFloat() / 2
        outerCircleRadius = (if (outerCircleX < outerCircleY) outerCircleX else outerCircleY) - outerCircleMargin
        innerArcStartingX = outerCircleX - outerCircleRadius + innerCircleMargin
        innerArcEndingX = outerCircleX + outerCircleRadius - innerCircleMargin
        innerArcStartingY = outerCircleY - outerCircleRadius + innerCircleMargin
        innerArcEndingY = outerCircleY + outerCircleRadius - innerCircleMargin
    }

    private fun drawOuterCircle(canvas : Canvas) {
        val paint = Paint()
        paint.color = outerCircleColor
        paint.setStrokeWidth(strokeOuterCircle);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(outerCircleX, outerCircleY, outerCircleRadius, paint)
        //invalidate()
    }

    private fun drawInnerCircle(canvas : Canvas, angleToComplete : Float) {
        val paint = Paint()
        paint.setStyle(Paint.Style.STROKE);
        paint.shader = getInnerCircleShader()
        paint.setStrokeWidth(strokeInnerCircle)
        val oval = RectF()
        oval.set(innerArcStartingX, innerArcStartingY, innerArcEndingX, innerArcEndingY)
        canvas.drawArc(oval, innerArcStartingAngle, angleToComplete, false, paint)
        //invalidate()
    }

    private fun getInnerCircleShader() : Shader {
        val stopsGradient = floatArrayOf(0f, 0.25f, 0.50f, 0.75f)
        val colorsGradient = intArrayOf(
                innerCircleColor_0_90,
                innerCircleColor_90_180,
                innerCircleColor_180_270,
                innerCircleColor_270_360)

        val shader = SweepGradient(outerCircleX - innerCircleMargin, outerCircleY - innerCircleMargin, colorsGradient, stopsGradient)
        val matrix = Matrix()
        matrix.setRotate(270f, outerCircleX - innerCircleMargin, outerCircleY - innerCircleMargin)
        shader.setLocalMatrix(matrix)

        return shader
    }
}