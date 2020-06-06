package com.pkj.learn.newsbyjus.data.source.remote

import com.pkj.learn.newsbyjus.data.Result
import retrofit2.Response

/**
 * @author Pankaj Jangid
 */
interface NewsRemoteDataSource {

    suspend fun getTopHeadlines(): Response<HeadlineResponse>

}