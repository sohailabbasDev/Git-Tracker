package com.sohail.gittracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

//My ROOM database
@Database(entities = [GithubRepoEntity::class], version = 1, exportSchema = false)
abstract class GithubRepoDatabase : RoomDatabase() {

    //ROOM DAO instance
    abstract val githubRepoDao : GithubRepoDao

    companion object{
        //Room DB name
        const val DATABASE_NAME = "repos_db"
    }
}