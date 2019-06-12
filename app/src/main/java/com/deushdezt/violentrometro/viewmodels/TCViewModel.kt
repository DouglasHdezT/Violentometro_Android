package com.deushdezt.violentrometro.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.deushdezt.violentrometro.database.RoomDB
import com.deushdezt.violentrometro.database.TCRepository
import com.deushdezt.violentrometro.database.entities.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TCViewModel(private val app: Application): AndroidViewModel(app) {

    private val tcRepository:TCRepository = TCRepository(RoomDB.getInstance(app).testCaseDao())

    fun insertTC(testCase: TestCase) = viewModelScope.launch(Dispatchers.IO) {
        tcRepository.insertTC(testCase)
    }

    fun getAllTC()= tcRepository.getAllTCs()

    fun getLastTC()= tcRepository.getFirstTCs()

}