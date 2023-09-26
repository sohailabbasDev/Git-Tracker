package com.sohail.gittracker.data.repository

import com.sohail.gittracker.data.local.GithubRepoDatabase
import com.sohail.gittracker.data.local.GithubRepoEntity
import com.sohail.gittracker.data.remote.GitApi
import com.sohail.gittracker.data.remote.dto.GitRepoDto
import com.sohail.gittracker.domain.repository.GitRepository
import javax.inject.Inject

//Github Repo Implementation
class GitRepositoryImpl @Inject constructor(
    private val api: GitApi,
    db : GithubRepoDatabase
) : GitRepository {

    //dao of github DB
    private val dao = db.githubRepoDao

    //Function to get repo from api
    override suspend fun getGitRepo(owner: String, repoName: String): GitRepoDto {
        //        dao.insertGithubRepo(repo.toGithubRepoEntity())
        return api.getRepo(owner = owner, repo = repoName)
    }

    //Function to add repo to db
    override suspend fun addToDb(githubRepoEntity: GithubRepoEntity) {
        dao.insertGithubRepo(githubRepoEntity)
    }

    //Function to get all saved repos from DB
    override suspend fun getAllSavedRepos(): List<GithubRepoEntity> {
        return dao.getAllRepos()
    }

    //Function to delete repo from DB
    override suspend fun deleteRepo(githubRepoEntity: GithubRepoEntity) {
        dao.deleteRep(githubRepoEntity)
    }


}