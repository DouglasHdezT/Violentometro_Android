package com.deushdezt.violentrometro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.deushdezt.violentrometro.adapters.TestCaseAdapter
import com.deushdezt.violentrometro.database.entities.TestCase
import com.deushdezt.violentrometro.viewmodels.TCViewModel
import kotlinx.android.synthetic.main.activity_last_results.*

class LastResults : AppCompatActivity() {

    val tcAdapter = TestCaseAdapter(ArrayList<TestCase>())

    lateinit var tcViewModel: TCViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last_results)

        initRecyclerView()

        tcViewModel = ViewModelProviders.of(this).get(TCViewModel::class.java)

        tcViewModel.getAllTC().observe(this, Observer {
            tcAdapter.changeDataSet(it)
        })
    }

    fun initRecyclerView(){
        result_set_rv.apply {
            layoutManager = LinearLayoutManager(this@LastResults)
            adapter = tcAdapter
        }
    }
}
