package com.sohail.gittracker.di

import android.app.Application
import androidx.room.Room
import com.sohail.gittracker.data.local.GithubRepoDatabase
import com.sohail.gittracker.data.remote.GitApi
import com.sohail.gittracker.data.repository.GitRepositoryImpl
import com.sohail.gittracker.domain.repository.GitRepository
import com.sohail.gittracker.domain.use_case.AddRepoToDbUseCase
import com.sohail.gittracker.domain.use_case.DeleteRepoUseCase
import com.sohail.gittracker.domain.use_case.GetAllReposFromDbUseCase
import com.sohail.gittracker.domain.use_case.GetGitRepoUseCase
import com.sohail.gittracker.domain.use_case.GitRepoUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGitApi(): GitApi {
        return Retrofit.Builder()
            .baseUrl(GitApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): GithubRepoDatabase {
        return Room.databaseBuilder(
            app,
            GithubRepoDatabase::class.java,
            GithubRepoDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideGitRepository(api: GitApi, db : GithubRepoDatabase) : GitRepository {
        return GitRepositoryImpl(api = api, db = db)
    }


    @Provides
    @Singleton
    fun provideGitRepoUseCases(
        repository: GitRepository
    ) : GitRepoUseCases {
        return GitRepoUseCases(
            getGitRepoUseCase = GetGitRepoUseCase(repository = repository),
            addRepoToDbUseCase = AddRepoToDbUseCase(repository = repository),
            getAllReposFromDbUseCase = GetAllReposFromDbUseCase(repository = repository),
            deleteRepoUseCase = DeleteRepoUseCase(repository = repository)
        )
    }
}