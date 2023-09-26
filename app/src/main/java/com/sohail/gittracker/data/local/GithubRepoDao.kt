package com.sohail.gittracker.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//This is DAO, data access object to access ROOM database
@Dao
interface GithubRepoDao {

    //This function inserts GithubRepoEntity to ROOM DB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubRepo(
        githubRepoEntity : GithubRepoEntity
    )

    //This function gets all the Repos
    @Query("SELECT * FROM githubRepoEntity")
    suspend fun getAllRepos() : List<GithubRepoEntity>

    //This function deletes a repo in ROOM DB
    @Delete
    suspend fun deleteRep(githubRepoEntity: GithubRepoEntity)
}