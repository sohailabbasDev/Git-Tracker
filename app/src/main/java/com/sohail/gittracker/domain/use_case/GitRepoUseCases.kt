package com.sohail.gittracker.domain.use_case

//Wrapper class that contains all the use cases
data class GitRepoUseCases(
    val getGitRepoUseCase: GetGitRepoUseCase,
    val addRepoToDbUseCase: AddRepoToDbUseCase,
    val getAllReposFromDbUseCase: GetAllReposFromDbUseCase,
    val deleteRepoUseCase: DeleteRepoUseCase
)
