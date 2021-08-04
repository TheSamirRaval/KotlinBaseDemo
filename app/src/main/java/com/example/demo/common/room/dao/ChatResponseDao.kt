package com.example.demo.common.room.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.demo.common.room.model.ChatResponse
import io.reactivex.Completable
import io.reactivex.Maybe

/**
 * This dao class is used for writing all the local Database query for Chat Table
 */

@Dao
interface ChatResponseDao {

    @Insert(onConflict = REPLACE)
    fun add(chatResponse: ChatResponse): Completable

    @Delete
    fun remove(chatResponse: ChatResponse): Completable

    /**
     * This query is used to clear the local DB
     */
    @Query("Delete FROM chat_response")
    fun removeAll(): Completable

//    @Insert(onConflict = REPLACE)
//    fun updateAll(chatResponse: List<DatabaseTables.ChatResponse>): Completable

    @Update
    fun update(chatResponse: ChatResponse): Completable


    @Query("SELECT * FROM chat_response WHERE chatId like :chatId")
    fun get(chatId: String): Maybe<List<ChatResponse>>


}