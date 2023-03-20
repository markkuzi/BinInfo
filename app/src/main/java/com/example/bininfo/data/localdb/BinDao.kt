package com.example.bininfo.data.localdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bininfo.data.localdb.model.BinInfoDbModel
import com.example.bininfo.data.localdb.model.BinListDb

@Dao
interface BinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(binInfoDbModel: BinInfoDbModel)

    @Query("SELECT * FROM bin_info_list WHERE binId == :binId")
    fun getBinInfo(binId: String): LiveData<BinInfoDbModel>

    @Query("SELECT binId, date, status FROM bin_info_list ORDER BY date DESC")
    fun getBinInfoList(): LiveData<List<BinListDb>>

    @Query("DELETE FROM bin_info_list WHERE binId == :binId")
    suspend fun deleteBinById(binId: String)

}