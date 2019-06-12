package com.deushdezt.violentrometro.animators

import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.TextView

class PercentageAnimator(
        val textView: TextView
):Animation() {

    var from: Float = 0F
    var to: Float = 0F

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        val value = from + (to - from) * interpolatedTime
        textView.text ="" + value.toInt() + "%"
    }
}