package com.example.demo.common.room.dao

import androidx.room.*
import com.example.demo.common.room.model.User
import io.reactivex.Completable
import io.reactivex.Maybe


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(user : User): Completable

    @Delete
    fun remove(user : User): Completable

    @Query("SELECT * FROM user")
    fun getAll(): Maybe<List<User>>

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE email like :email AND password like :password")
    fun getUser(email: String, password: String): Maybe<User>

    @Insert
    fun insertAll(users: User)

    @Delete
    fun delete(user: User)
}