package com.sohail.gittracker.domain.use_case

data class GitRepoUseCases(
    val getGitRepoUseCase: GetGitRepoUseCase,
    val addRepoToDbUseCase: AddRepoToDbUseCase,
    val getAllReposFromDbUseCase: GetAllReposFromDbUseCase,
    val deleteRepoUseCase: DeleteRepoUseCase
)
