package com.deushdezt.violentrometro.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test_case")
data class TestCase(
    @ColumnInfo(name="timestamp")
    val timestamp: Long,

    @ColumnInfo(name="total_level1")
    val totalLevel1: Int,

    @ColumnInfo(name="total_level2")
    val totalLevel2: Int,

    @ColumnInfo(name="total_level3")
    val totalLevel3: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id:Long = 0
}