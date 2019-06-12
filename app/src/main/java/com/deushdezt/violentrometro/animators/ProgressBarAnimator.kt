package com.deushdezt.violentrometro.animators

import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar

class ProgressBarAnimator(
    val progressBar: ProgressBar
) : Animation() {

    var from: Float = 0F
    var to: Float = 0F

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)

        from = if (from<0) 0F else from
        to = if (to<0) 0F else to

        val value = from + (to - from) * interpolatedTime
        progressBar.progress = value.toInt()
    }

}