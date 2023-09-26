package com.sohail.gittracker.domain.use_case

import com.sohail.gittracker.data.local.GithubRepoEntity
import com.sohail.gittracker.domain.repository.GitRepository

//This DeleteRepoUseCase used to delete repo use case
class DeleteRepoUseCase(
    private val repository: GitRepository
) {

    //This invoke is called when the class is initiated
    suspend operator fun invoke(githubRepoEntity: GithubRepoEntity){
        try {
            //deletes repo from DB
            repository.deleteRepo(githubRepoEntity)
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}