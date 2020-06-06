package com.pkj.learn.newsbyjus.data.source.remote

import com.pkj.learn.newsbyjus.BuildConfig
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Pankaj Jangid
 */
interface NewsApi {

    @GET("top-headlines?country=us&category=business&apiKey=${BuildConfig.NEWS_API_KEY}")
    suspend fun getTopHeadlines(): Response<HeadlineResponse>
}