package com.sohail.gittracker.domain.repository

import com.sohail.gittracker.data.local.GithubRepoEntity
import com.sohail.gittracker.data.remote.dto.GitRepoDto

//GitRepository Interface to make it testable
interface GitRepository {

    suspend fun getGitRepo(owner : String, repoName : String): GitRepoDto

    suspend fun addToDb(githubRepoEntity: GithubRepoEntity)

    suspend fun getAllSavedRepos() : List<GithubRepoEntity>

    suspend fun deleteRepo(githubRepoEntity: GithubRepoEntity)
}