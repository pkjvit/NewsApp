package com.pkj.learn.newsbyjus.data.source.remote

import com.pkj.learn.newsbyjus.data.Result
import retrofit2.Response

/**
 * @author Pankaj Jangid
 */
class DefaultNewsRemoteDataSource(private val api: NewsApi) : NewsRemoteDataSource{

    override suspend fun getTopHeadlines(): Response<HeadlineResponse> {
        return api.getTopHeadlines()
    }
}