package com.deushdezt.violentrometro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.deushdezt.violentrometro.animators.ProgressBarAnimator
import com.deushdezt.violentrometro.database.entities.TestCase
import com.deushdezt.violentrometro.viewmodels.TCViewModel
import kotlinx.android.synthetic.main.activity_test_case.*

class TestCaseActivity : AppCompatActivity() {

    lateinit var tcViewModel: TCViewModel

    private lateinit var animPB1: ProgressBarAnimator
    private lateinit var animPB2: ProgressBarAnimator
    private lateinit var animPB3: ProgressBarAnimator

    private lateinit var question_array : Array<String>
    private lateinit var question_levels : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_case)

        tcViewModel = ViewModelProviders.of(this).get(TCViewModel::class.java)
        question_array = resources.getStringArray(R.array.questions)
        question_levels = resources.getStringArray(R.array.questions_levels)

        if(intent != null){
            tcViewModel.testCase.value = TestCase()
            tcViewModel.currentQuestion.value = 0
        }

        bindAnimators()
        bindClickListeners()

        tcViewModel.testCase.observe(this, Observer {
            //Log.d("MINE", "TCModified "+it.totalLevel1+" "+it.totalLevel2+" "+it.totalLevel3)


            when(question_levels[tcViewModel.currentQuestion.value?:0]){
                "1"->{
                    animPB1.apply {
                        this.from = ((it.totalLevel1-1)/11F)*100
                        this.to = ((it.totalLevel1)/11F)*100
                        this.duration = 250L
                    }

                    test_case_bar1.startAnimation(animPB1)
                }
                "2"->{
                    animPB2.apply {
                        this.from = ((it.totalLevel2-1)/9F)*100
                        this.to = ((it.totalLevel2)/9F)*100
                        this.duration = 250L
                    }

                    test_case_bar2.startAnimation(animPB2)
                }
                "3"->{
                    animPB3.apply {
                        this.from = ((it.totalLevel3-1)/7F)*100
                        this.to = ((it.totalLevel3)/7F)*100
                        this.duration = 250L
                    }

                    test_case_bar3.startAnimation(animPB3)
                }
            }

            /*test_case_bar1.progress = (((it.totalLevel1)/11F)*100).toInt()
            test_case_bar2.progress = (((it.totalLevel2)/9F)*100).toInt()
            test_case_bar3.progress = (((it.totalLevel3)/7F)*100).toInt()*/

        })

        tcViewModel.currentQuestion.observe(this, Observer {
            //Log.d("MINE", "contadorModified " + it)
            if(it<question_levels.size){
                val auxString = "Pregunta #"+( it+1 )
                question_title_tv.text = auxString

                question_level_tv.text = translateLevel(question_levels[it])

                question_body_tv.text = question_array[it]
            }else{
                tcViewModel.insertTC(tcViewModel.testCase.value?:TestCase())
                startActivity(Intent(this@TestCaseActivity, MainActivity::class.java))
            }
        })

    }

    fun bindAnimators(){
        animPB1 = ProgressBarAnimator(test_case_bar1)
        animPB2 = ProgressBarAnimator(test_case_bar2)
        animPB3 = ProgressBarAnimator(test_case_bar3)

        animPB1.duration = 1000L
        animPB2.duration = 1000L
        animPB3.duration = 1000L

    }

    fun bindClickListeners(){
        answer_affirmative_action.setOnClickListener {
            incContValues()
            incCurrentQuestion()
        }

        answer_negative_action.setOnClickListener {
            incCurrentQuestion()
        }
    }

    fun incCurrentQuestion(){
        val value = tcViewModel.currentQuestion.value?.plus(1)?:0
        tcViewModel.currentQuestion.value = value
    }

    fun incContValues(){
        val curr = tcViewModel.currentQuestion.value?:0
        val testCase = tcViewModel.testCase.value

        when(question_levels[curr]){
            "1"->{
                testCase?.totalLevel1 = testCase?.totalLevel1?.plus(1)?:0
            }
            "2"->{
                testCase?.totalLevel2 = testCase?.totalLevel2?.plus(1)?:0
            }
            "3"->{
                testCase?.totalLevel3 = testCase?.totalLevel3?.plus(1)?:0
            }
        }

        tcViewModel.testCase.value = testCase

    }

    fun translateLevel(level:String):String = when(level){
        "1" -> resources.getString(R.string.level_soft)
        "2" -> resources.getString(R.string.level_serious)
        "3" -> resources.getString(R.string.level_hardcore)
        else -> "Default"
    }
}
