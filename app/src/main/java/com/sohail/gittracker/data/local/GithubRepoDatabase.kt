package com.sohail.gittracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GithubRepoEntity::class], version = 1, exportSchema = false)
abstract class GithubRepoDatabase : RoomDatabase() {

    abstract val githubRepoDao : GithubRepoDao

    companion object{
        const val DATABASE_NAME = "repos_db"
    }
}