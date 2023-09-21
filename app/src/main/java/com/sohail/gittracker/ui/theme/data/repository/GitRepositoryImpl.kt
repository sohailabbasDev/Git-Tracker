package com.sohail.gittracker.ui.theme.data.repository

import com.sohail.gittracker.ui.theme.data.local.GithubRepoDatabase
import com.sohail.gittracker.ui.theme.data.local.GithubRepoEntity
import com.sohail.gittracker.ui.theme.data.remote.GitApi
import com.sohail.gittracker.ui.theme.data.remote.dto.GitRepoDto
import com.sohail.gittracker.ui.theme.domain.repository.GitRepository
import javax.inject.Inject

class GitRepositoryImpl @Inject constructor(
    private val api: GitApi,
    db : GithubRepoDatabase
) :GitRepository{

    private val dao = db.githubRepoDao

    override suspend fun getGitRepo(owner: String, repoName: String): GitRepoDto {
        //        dao.insertGithubRepo(repo.toGithubRepoEntity())
        return api.getRepo(owner = owner, repo = repoName)
    }

    override suspend fun addToDb(githubRepoEntity: GithubRepoEntity) {
        dao.insertGithubRepo(githubRepoEntity)
    }

    override suspend fun getAllSavedRepos(): List<GithubRepoEntity> {
        return dao.getAllRepos()
    }

    override suspend fun deleteRepo(githubRepoEntity: GithubRepoEntity) {
        dao.deleteRep(githubRepoEntity)
    }


}