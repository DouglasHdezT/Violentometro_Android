package com.deushdezt.violentrometro.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.deushdezt.violentrometro.database.RoomDB
import com.deushdezt.violentrometro.database.TCRepository
import com.deushdezt.violentrometro.database.entities.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TCViewModel(private val app: Application): AndroidViewModel(app) {

    val testCase: MutableLiveData<TestCase> by lazy {
        MutableLiveData<TestCase>()
    }

    val currentQuestion :MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    private val tcRepository:TCRepository = TCRepository(RoomDB.getInstance(app).testCaseDao())

    fun insertTC(testCase: TestCase) = viewModelScope.launch(Dispatchers.IO) {
        tcRepository.insertTC(testCase)
    }

    fun getAllTC()= tcRepository.getAllTCs()

    fun getLastTC()= tcRepository.getFirstTCs()

}