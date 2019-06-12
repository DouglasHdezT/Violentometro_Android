package com.deushdezt.violentrometro.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deushdezt.violentrometro.database.entities.TestCase

@Dao
interface TestCaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTestCase(testCase: TestCase)

    @Query("SELECT * FROM test_case")
    fun getAllTestCases():LiveData<List<TestCase>>

    @Query("SELECT * FROM test_case ORDER BY timestamp DESC LIMIT 1")
    fun getFirstTestCases():LiveData<TestCase>

    @Query("DELETE FROM test_case")
    fun nukeTesCasesTable()

}