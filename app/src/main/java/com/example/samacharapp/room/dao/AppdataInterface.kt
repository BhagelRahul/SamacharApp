//package com.example.samacharapp.room.dao
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.example.samacharapp.room.entity.User
//
//@Dao
//interface AppdataInterface {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(user:User)
//
//    @Query("SELECT * FROM users")
//  suspend  fun getAllUsers(): List<User>
//
//    @Query("SELECT * FROM users WHERE id=:id")
//   suspend fun getUserById(id: String): User
//
//}