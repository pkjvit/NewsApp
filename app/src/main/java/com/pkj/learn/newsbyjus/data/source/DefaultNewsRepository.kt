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
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

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