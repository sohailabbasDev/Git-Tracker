package com.sohail.gittracker.ui.theme.domain.use_case

data class GitRepoUseCases(
    val getGitRepoUseCase: GetGitRepoUseCase,
    val addRepoToDbUseCase: AddRepoToDbUseCase,
    val getAllReposFromDbUseCase: GetAllReposFromDbUseCase,
    val deleteRepoUseCase: DeleteRepoUseCase
)
