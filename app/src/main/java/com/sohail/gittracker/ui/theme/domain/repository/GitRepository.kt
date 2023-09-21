package com.sohail.gittracker.ui.theme.domain.repository

import com.sohail.gittracker.ui.theme.data.local.GithubRepoEntity
import com.sohail.gittracker.ui.theme.data.remote.dto.GitRepoDto

interface GitRepository {

    suspend fun getGitRepo(owner : String, repoName : String): GitRepoDto

    suspend fun addToDb(githubRepoEntity: GithubRepoEntity)

    suspend fun getAllSavedRepos() : List<GithubRepoEntity>

    suspend fun deleteRepo(githubRepoEntity: GithubRepoEntity)
}