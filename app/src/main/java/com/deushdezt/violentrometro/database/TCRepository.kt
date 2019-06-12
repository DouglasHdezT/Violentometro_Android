package com.deushdezt.violentrometro.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.deushdezt.violentrometro.database.daos.TestCaseDao
import com.deushdezt.violentrometro.database.entities.TestCase

class TCRepository(private val TCDao: TestCaseDao) {
    @WorkerThread
    suspend fun insertTC(testCase: TestCase) = TCDao.insertTestCase(testCase)

    fun getAllTCs(): LiveData<List<TestCase>> = TCDao.getAllTestCases()

    fun getFirstTCs(): LiveData<TestCase> = TCDao.getFirstTestCases()

    fun nukeTCTable() = TCDao.nukeTesCasesTable()
}