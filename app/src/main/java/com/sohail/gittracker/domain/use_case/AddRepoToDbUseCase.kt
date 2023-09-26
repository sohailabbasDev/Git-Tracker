package com.sohail.gittracker.domain.use_case

import com.sohail.gittracker.data.local.GithubRepoEntity
import com.sohail.gittracker.domain.repository.GitRepository
import com.sohail.gittracker.util.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//This AddRepoToDbUseCase to add a repository to DB
class AddRepoToDbUseCase @Inject constructor(
    private val repository: GitRepository
) {

    //This invoke is called when the class is initiated and it returns a flow of Status which can contain data
    operator fun invoke(githubRepoEntity: GithubRepoEntity): Flow<Status> = flow{
        try {
            //Initial loading stage
            emit(Status.Loading())
            repository.addToDb(githubRepoEntity)

            //After success
            emit(Status.Success())
        }catch (e : Exception){

            //When Exception occurs it will emit
            emit(Status.Error(message = e.localizedMessage ?: "Something went wrong, Please try again"))
            e.printStackTrace()
        }
    }
}