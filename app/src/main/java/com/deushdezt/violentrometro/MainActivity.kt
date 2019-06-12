package com.deushdezt.violentrometro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.deushdezt.violentrometro.animators.PercentageAnimator
import com.deushdezt.violentrometro.viewmodels.TCViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var tcViewModel: TCViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tcViewModel = ViewModelProviders.of(this).get(TCViewModel::class.java)

    }

    fun bindLastResult(){

    }
}
