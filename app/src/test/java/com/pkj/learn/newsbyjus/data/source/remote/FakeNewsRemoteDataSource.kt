package com.pkj.learn.newsbyjus.data.source.remote

import com.pkj.learn.newsbyjus.data.Article
import org.junit.Assert.*
import retrofit2.Response
import retrofit2.Response.error
import retrofit2.Response.success

/**
 * @author Pankaj Jangid
 */
class FakeNewsRemoteDataSource(val headlineResponse : HeadlineResponse?) : NewsRemoteDataSource{

    override suspend fun getTopHeadlines(): Response<HeadlineResponse> {
        headlineResponse?.let {
            return success(it)
        }
        return error("no response")
    }

}