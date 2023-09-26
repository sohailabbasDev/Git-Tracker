package com.sohail.gittracker.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sohail.gittracker.data.local.GithubRepoEntity
import com.sohail.gittracker.domain.model.GithubRepo
import com.sohail.gittracker.domain.use_case.GitRepoUseCases
import com.sohail.gittracker.util.Resource
import com.sohail.gittracker.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

//Git repo view model
@HiltViewModel
class GitRepoViewModel @Inject constructor(private val gitRepoUseCases : GitRepoUseCases) : ViewModel() {

    //state of repo inside a dialog
    var state = mutableStateOf<Resource<GithubRepo?>>(Resource.Initial())
        private set

    //state of Db
    var dbState = mutableStateOf<Status>(Status.Initial())
        private set

    //state of repo state which will have all the repos
    private val _getRepoState = MutableStateFlow<List<GithubRepoEntity>>(emptyList())
    val getReposState : StateFlow<List<GithubRepoEntity>> = _getRepoState.asStateFlow()

    //Repo list state to observe the state of the list
    var repoListState = mutableStateOf<Resource<List<GithubRepoEntity>>>(Resource.Initial())
        private set

    //repo names entered in dialog
    var repoName = mutableStateOf("")
        private set

    //repo owner name entered in dialog
    var ownerName = mutableStateOf("")
        private set

    //is dialog showing toggle boolean
    var isDialogShowing by mutableStateOf(false)
        private set

    //all the jobs used
    private var getRepoJob: Job? = null
    private var addRepoJob: Job? = null
    private var getAllReposJob: Job? = null

    init {

        //gets all the repos
        getAllRepos()
    }

    // finds the repo and updates the state
    fun findRepo(owner: String, repoName: String){
        getRepoJob?.cancel()
        getRepoJob = gitRepoUseCases.getGitRepoUseCase(owner, repoName).onEach {result ->
            when(result){
                is Resource.Success -> {
                    state.value = Resource.Success(data = result.data)
                }
                is Resource.Error -> {
                    state.value = Resource.Error(message = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    state.value = Resource.Loading(true)
                }
                is Resource.Initial -> {
                    state.value = Resource.Initial()
                }
            }
        }.launchIn(viewModelScope)
    }

    //adds the repo to view model
    fun addRepoToDb(githubRepoEntity: GithubRepoEntity){
        addRepoJob?.cancel()
        addRepoJob = gitRepoUseCases.addRepoToDbUseCase(githubRepoEntity = githubRepoEntity).onEach { result ->
            when(result){
                is Status.Loading -> {
                    dbState.value = Status.Loading()
//                    state.value = Resource.Loading()
                }
                is Status.Success -> {
                    dbState.value = Status.Success()
                    isDialogShowing = false
                    state.value = Resource.Initial()
                    dbState.value = Status.Initial()
                    getAllRepos()
                }
                is Status.Error -> {
                    dbState.value = Status.Error(message = result.message ?: "Something went wrong, Please try again")
                }
                is Status.Initial -> {
                    dbState.value = Status.Initial()
                }
            }
        }.launchIn(viewModelScope)
    }

    //get all the repos
    private fun getAllRepos(){
        getAllReposJob?.cancel()
        getAllReposJob = gitRepoUseCases.getAllReposFromDbUseCase().onEach {result ->
            when(result){
                is Resource.Success -> {
                    repoListState.value = Resource.Success(data = result.data!!)
                    _getRepoState.value = result.data
                }
                is Resource.Error -> {
                    repoListState.value = Resource.Error(message = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    repoListState.value = Resource.Loading(true)
                    Log.d("tagged", "getAllRepos: loading")
                }
                is Resource.Initial -> {
                    repoListState.value = Resource.Initial()
                }
            }
        }.launchIn(viewModelScope)
    }

    //delete a repo function
    fun deleteRepo(githubRepoEntity: GithubRepoEntity){
        viewModelScope.launch {
            _getRepoState.update {
                val list = it.toMutableList()
                list.remove(githubRepoEntity)
                list
            }
            gitRepoUseCases.deleteRepoUseCase(githubRepoEntity)
        }
//            .invokeOnCompletion {
////                getReposState.value = Resource.Initial()
//                getAllRepos()
//            }
    }

    //Re initializes the state value
    fun reInit(){
        state.value = Resource.Initial()
    }

//    fun reInitDbState(){
//        dbState.value = Status.Initial()
//    }

    //toggle function of dialog boolean
    fun showDialog(dismiss : Boolean){
        isDialogShowing = dismiss
        if (!dismiss){
            state.value = Resource.Initial()
            dbState.value = Status.Initial()
        }
    }

    //repo name function to update repo name variable
    fun repoName(repo : String){
        repoName.value = repo
    }

    //owner name update function
    fun ownerName(owner : String){
        ownerName.value = owner
    }
}