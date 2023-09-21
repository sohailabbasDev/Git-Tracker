package com.sohail.gittracker.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GithubRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubRepo(
        githubRepoEntity : GithubRepoEntity
    )

    @Query("SELECT * FROM githubRepoEntity")
    suspend fun getAllRepos() : List<GithubRepoEntity>

    @Delete
    suspend fun deleteRep(githubRepoEntity: GithubRepoEntity)
}