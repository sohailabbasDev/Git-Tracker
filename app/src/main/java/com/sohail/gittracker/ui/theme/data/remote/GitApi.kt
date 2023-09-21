package com.sohail.gittracker.ui.theme.data.remote

import com.sohail.gittracker.ui.theme.data.remote.dto.GitRepoDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GitApi {
//    /repos/{owner}/{repo}
    @GET("repos/{owner}/{repo}")
    suspend fun getRepo(
        @Header("Authorization") token :String = API_KEY,
        @Path("owner") owner : String,
        @Path("repo") repo : String
    ) : GitRepoDto

    companion object{

        //Change this with your own token, This token may expire
        const val API_KEY = "Bearer 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"
        const val BASE_URL = "https://api.github.com/"
    }
}