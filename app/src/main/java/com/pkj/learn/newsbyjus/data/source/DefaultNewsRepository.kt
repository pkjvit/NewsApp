package com.pkj.learn.newsbyjus.data.source

import androidx.lifecycle.LiveData
import com.pkj.learn.newsbyjus.data.Article
import com.pkj.learn.newsbyjus.data.NewsRepository
import com.pkj.learn.newsbyjus.data.Result
import com.pkj.learn.newsbyjus.data.source.local.DefaultNewsLocalDataSource
import com.pkj.learn.newsbyjus.data.source.remote.DefaultNewsRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * @author Pankaj Jangid
 */
class DefaultNewsRepository @Inject constructor(
    private val localDataSource: DefaultNewsLocalDataSource,
    private val remoteDataSource: DefaultNewsRemoteDataSource,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO) : NewsRepository{

    override suspend fun getArticles(): Result<List<Article>> {
        return localDataSource.getArticles()
    }

    override suspend fun getArticle(articleId: String): Result<Article> {
        return localDataSource.getArticle(articleId)
    }

    override fun observeArticles(): LiveData<Result<List<Article>>> {
        return localDataSource.observeArticles()
    }

    override fun observeArticle(articleId: String): LiveData<Result<Article>> {
        return localDataSource.observeArticle(articleId)
    }

    override suspend fun insertArticles(articles: List<Article>): Result<List<Long>> {
        return localDataSource.insertArticles(articles)
    }

    override suspend fun clearAndCacheArticles(articles: List<Article>) {
        return localDataSource.clearAndCacheArticles(articles)
    }
}