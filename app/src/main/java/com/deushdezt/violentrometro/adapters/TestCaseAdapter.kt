package com.deushdezt.violentrometro.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deushdezt.violentrometro.R
import com.deushdezt.violentrometro.database.entities.TestCase
import kotlinx.android.synthetic.main.result_item_cv.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class TestCaseAdapter(var testCases : List<TestCase>): RecyclerView.Adapter<TestCaseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.result_item_cv, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = testCases.size

    fun changeDataSet(newTestCases: List<TestCase>){
        testCases = newTestCases
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(testCases[position])


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val simpleFormater:DateFormat = SimpleDateFormat("dd/MMM/yy -> HH:mm", Locale.US)
        fun bind(testCase : TestCase) = with(itemView){
            card_view_timestamp.text = simpleFormater.format(Date(testCase.timestamp))

            val to1 = (testCase.totalLevel1 / 11F) * 100
            val to2 = (testCase.totalLevel2 / 9F) * 100
            val to3 = (testCase.totalLevel3 / 7F) * 100

            card_view_bar1.progress = to1.toInt()
            card_view_bar2.progress = to2.toInt()
            card_view_bar3.progress = to3.toInt()

            card_view_percentage1.text = "${to1.toInt()}%"
            card_view_percentage2.text = "${to2.toInt()}%"
            card_view_percentage3.text = "${to3.toInt()}%"


        }
    }
}