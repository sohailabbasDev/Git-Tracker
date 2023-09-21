package com.sohail.gittracker.domain.use_case

import com.sohail.gittracker.data.local.GithubRepoEntity
import com.sohail.gittracker.domain.repository.GitRepository
import com.sohail.gittracker.util.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddRepoToDbUseCase @Inject constructor(
    private val repository: GitRepository
) {
    operator fun invoke(githubRepoEntity: GithubRepoEntity): Flow<Status> = flow{
        try {
            emit(Status.Loading())
            repository.addToDb(githubRepoEntity)
            emit(Status.Success())
        }catch (e : Exception){
            emit(Status.Error(message = e.localizedMessage ?: "Something went wrong, Please try again"))
            e.printStackTrace()
        }
    }
}