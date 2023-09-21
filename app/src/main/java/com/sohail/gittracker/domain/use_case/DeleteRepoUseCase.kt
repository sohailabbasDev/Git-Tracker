package com.sohail.gittracker.domain.use_case

import com.sohail.gittracker.data.local.GithubRepoEntity
import com.sohail.gittracker.domain.repository.GitRepository

class DeleteRepoUseCase(
    private val repository: GitRepository
) {
    suspend operator fun invoke(githubRepoEntity: GithubRepoEntity){
        try {
            repository.deleteRepo(githubRepoEntity)
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}