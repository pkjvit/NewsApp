package com.pkj.learn.newsbyjus.data.source.remote

import retrofit2.Response
import javax.inject.Inject

/**
 * @author Pankaj Jangid
 */
class DefaultNewsRemoteDataSource @Inject constructor(private val api: NewsApi) : NewsRemoteDataSource{

    override suspend fun getTopHeadlines(): Response<HeadlineResponse> {
        return api.getTopHeadlines()
    }
}