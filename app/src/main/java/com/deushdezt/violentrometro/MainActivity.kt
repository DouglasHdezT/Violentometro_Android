package com.deushdezt.violentrometro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.deushdezt.violentrometro.animators.PercentageAnimator
import com.deushdezt.violentrometro.animators.ProgressBarAnimator
import com.deushdezt.violentrometro.viewmodels.TCViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val animDuration = 1000

    lateinit var tcViewModel: TCViewModel

    private lateinit var animTV1: PercentageAnimator
    private lateinit var animTV2: PercentageAnimator
    private lateinit var animTV3: PercentageAnimator

    private lateinit var animPB1: ProgressBarAnimator
    private lateinit var animPB2: ProgressBarAnimator
    private lateinit var animPB3: ProgressBarAnimator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tcViewModel = ViewModelProviders.of(this).get(TCViewModel::class.java)

        bindAnimators()
        bindLastResult()

    }

    fun bindLastResult(){
        tcViewModel.getLastTC().observe(this, Observer {
            val to1 = ((it?.totalLevel1?:0) / 11F) * 100
            val to2 = ((it?.totalLevel2?:0) / 9F) * 100
            val to3 = ((it?.totalLevel3?:0) / 7F) * 100

            setAnimatorsLimits(to1, to2, to3)
            startAnimations()
        })
    }

    fun bindAnimators(){
        animTV1 = PercentageAnimator(progress_bar_tv1)
        animTV2 = PercentageAnimator(progress_bar_tv2)
        animTV3 = PercentageAnimator(progress_bar_tv3)

        animPB1 = ProgressBarAnimator(main_progress_bar1)
        animPB2 = ProgressBarAnimator(main_progress_bar2)
        animPB3 = ProgressBarAnimator(main_progress_bar3)
    }

    fun setAnimatorsLimits(to1:Float, to2:Float, to3:Float){
        animPB1.apply {
            this.from = 0F
            this.to = to1
            this.duration = animDuration.toLong()
        }

        animPB2.apply {
            this.from = 0F
            this.to = to2
            this.duration = animDuration.toLong()
        }

        animPB3.apply {
            this.from = 0F
            this.to = to3
            this.duration = animDuration.toLong()
        }

        animTV1.apply {
            this.from = 0F
            this.to = to1
            this.duration = animDuration.toLong()
        }

        animTV2.apply {
            this.from = 0F
            this.to = to2
            this.duration = animDuration.toLong()
        }

        animTV3.apply {
            this.from = 0F
            this.to = to3
            this.duration = animDuration.toLong()
        }
    }

    fun startAnimations(){
        main_progress_bar1.startAnimation(animPB1)
        main_progress_bar2.startAnimation(animPB2)
        main_progress_bar3.startAnimation(animPB3)

        progress_bar_tv1.startAnimation(animTV1)
        progress_bar_tv2.startAnimation(animTV2)
        progress_bar_tv3.startAnimation(animTV3)
    }
}
