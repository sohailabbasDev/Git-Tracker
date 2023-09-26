package com.sohail.gittracker.domain.use_case

import com.sohail.gittracker.data.mapper.toGithubRepo
import com.sohail.gittracker.domain.model.GithubRepo
import com.sohail.gittracker.domain.repository.GitRepository
import com.sohail.gittracker.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

//This GetGitRepoUseCase used to get repo from API
class GetGitRepoUseCase @Inject constructor(
    private val repository: GitRepository
) {

    //Get a Repo by hitting the API
    operator fun invoke(owner : String, repoName: String): Flow<Resource<GithubRepo>> = flow {
        try {

            //Emits Loading in initial
            emit(Resource.Loading(true))
            val repo = repository.getGitRepo(owner = owner, repoName = repoName).toGithubRepo()

            //After Success
            emit(Resource.Success(repo))
        }catch (e : Exception){

            //Emits exception
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
//            Log.d("tagged", "invoke: from use Case ${e.message}, ${e.localizedMessage}")
            e.printStackTrace()
        }catch (e : IOException){
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server, Check your internet connection."))
//            Log.d("tagged", "invoke: IO from use Case ${e.message}, ${e.localizedMessage}")
            e.printStackTrace()
        }
    }

}