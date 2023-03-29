package com.example.bininfo.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bininfo.data.localdb.model.BinInfoDbModel

@Database(entities = [BinInfoDbModel::class], version = 1)
abstract class BinDB : RoomDatabase() {
    abstract val binDao: BinDao

}