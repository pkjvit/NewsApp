package com.pkj.learn.newsbyjus.data.source

import androidx.lifecycle.LiveData
import com.pkj.learn.newsbyjus.data.Article
import com.pkj.learn.newsbyjus.data.NewsRepository
import com.pkj.learn.newsbyjus.data.Result
import com.pkj.learn.newsbyjus.data.source.local.DefaultNewsLocalDataSource
import com.pkj.learn.newsbyjus.data.source.remote.DefaultNewsRemoteDataSource
import com.pkj.learn.newsbyjus.data.source.remote.HeadlineResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.pkj.learn.newsbyjus.data.source.NetworkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

/**
 * @author Pankaj Jangid
 */
class DefaultNewsRepository @Inject constructor(
    private val localDataSource: DefaultNewsLocalDataSource,
    private val remoteDataSource: DefaultNewsRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : NewsRepository{


    override suspend fun refreshArticles() {
        try {
            updateArticlesFromRemoteDataSource()
        } catch (ex: Exception) {
            Timber.e(ex)
        }
    }

    override suspend fun getArticles(forceUpdate: Boolean): Result<List<Article>> {
        if (forceUpdate) {
            try {
                updateArticlesFromRemoteDataSource()
            } catch (ex: Exception) {
                return Result.Error(ex.localizedMessage)
            }
        }
        return localDataSource.getArticles()
    }

    private suspend fun updateArticlesFromRemoteDataSource()  = withContext(ioDispatcher){
        try {
            val apiResponse = remoteDataSource.getTopHeadlines()

            when {
                apiResponse.isSuccessful && apiResponse.body() != null -> {
                    apiResponse.body()?.let {
                        val articles: List<Article> =
                            (it as HeadlineResponse).articles.map { article ->
                                Article.convertRemoteArticleToLocalArticle(article)
                            }
                        localDataSource.clearAndCacheArticles(articles)
                    }
                }
                else -> {
                    throw Exception("no response")
                }
            }
        }catch (e: HttpException) {
            throw e
        }catch (e: IOException) {
            throw e
        }

    }

//    suspend fun getTopHeadlinesFlow(): Flow<Result<List<Article>>> {
//        return object : NetworkBoundResource<List<Article>, Response<HeadlineResponse>>() {
//            override suspend fun saveNetworkResult(response: Response<HeadlineResponse>) = localDataSource.clearAndCacheArticles((response.body() as HeadlineResponse).articles )
//            // Always try to fetch new articles
//            override fun shouldFetch(data: List<Article>?): Boolean = true
//            override fun loadFromDb(): Flow<List<Article>> = localDataSource.getArticles()
//            override suspend fun fetchFromNetwork(): Response<HeadlineResponse> = remoteDataSource.getTopHeadlines()
//        }
//            .asFlow()
//            .flowOn(Dispatchers.IO)
//    }

    override suspend fun getArticle(articleId: String): Result<Article> {
        return localDataSource.getArticle(articleId)
    }

    override fun observeArticles(): LiveData<Result<List<Article>>> {
        return localDataSource.observeArticles()
    }

    override fun observeArticle(articleId: Int): LiveData<Result<Article>> {
        return localDataSource.observeArticle(articleId)
    }

    override suspend fun insertArticles(articles: List<Article>): Result<List<Long>> {
        return localDataSource.insertArticles(articles)
    }

    override suspend fun clearAndCacheArticles(articles: List<Article>) {
        return localDataSource.clearAndCacheArticles(articles)
    }
}