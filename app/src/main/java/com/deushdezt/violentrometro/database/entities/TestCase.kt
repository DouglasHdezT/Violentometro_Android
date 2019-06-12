package com.deushdezt.violentrometro.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "test_case")
data class TestCase(
        @ColumnInfo(name = "timestamp")
        var timestamp: Long = Date().time,

        @ColumnInfo(name = "total_level1")
        var totalLevel1: Int = 0,

        @ColumnInfo(name = "total_level2")
        var totalLevel2: Int = 0,

        @ColumnInfo(name = "total_level3")
        var totalLevel3: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Long = 0
}