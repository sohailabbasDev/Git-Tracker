package com.sohail.gittracker.domain.use_case

import com.sohail.gittracker.data.local.GithubRepoEntity
import com.sohail.gittracker.domain.repository.GitRepository
import com.sohail.gittracker.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//This GetAllReposFromDbUseCase used to get all repos from DB
class GetAllReposFromDbUseCase(
    private val repository: GitRepository
) {

    //This function returns a list of flow of Resource which will have list of GithubRepoEntity
    operator fun invoke() : Flow<Resource<List<GithubRepoEntity>>> = flow {
        try {

            //This emits loading at start
            emit(Resource.Loading(true))
            val list = repository.getAllSavedRepos()

            //Success after list is taken
            emit(Resource.Success(list))
        }catch (e : Exception){

            //Emits error when something is wrong
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
//            Log.d("tagged", "invoke: from use Case ${e.message}, ${e.localizedMessage}")
            e.printStackTrace()
        }
    }
}