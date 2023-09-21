package com.sohail.gittracker.domain.use_case

import com.sohail.gittracker.data.local.GithubRepoEntity
import com.sohail.gittracker.domain.repository.GitRepository
import com.sohail.gittracker.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllReposFromDbUseCase(
    private val repository: GitRepository
) {

    operator fun invoke() : Flow<Resource<List<GithubRepoEntity>>> = flow {
        try {
            emit(Resource.Loading(true))
            val list = repository.getAllSavedRepos()
            emit(Resource.Success(list))
        }catch (e : Exception){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
//            Log.d("tagged", "invoke: from use Case ${e.message}, ${e.localizedMessage}")
            e.printStackTrace()
        }
    }
}