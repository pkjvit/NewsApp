package com.pkj.learn.newsbyjus.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.pkj.learn.newsbyjus.data.Article
import com.pkj.learn.newsbyjus.data.Result
import javax.inject.Inject

/**
 * @author Pankaj Jangid
 */
class DefaultNewsLocalDataSource @Inject constructor(private val dao: NewsDao) : NewsLocalDataSource{
    override suspend fun getArticles(): Result<List<Article>> {
        return Result.Success(dao.getArticles())
    }

    override suspend fun getArticle(articleId: Int): Result<Article> {
        val article = dao.getArticleById(articleId)
        article?.let {
            return Result.Success(article)
        }
        return Result.Error("Article not found!")
    }

    override fun observeArticles(): LiveData<Result<List<Article>>> {
        return dao.observeArticles().map { Result.Success(it) }
    }

    override fun observeArticle(articleId: Int): LiveData<Result<Article>> {
        return dao.observeArticleById(articleId).map { Result.Success(it) }
    }

    override suspend fun insertArticles(articles: List<Article>): Result<List<Long>> {
        return Result.Success(dao.insertArticles(articles))
    }

    override suspend fun clearAndCacheArticles(articles: List<Article>) {
        dao.clearAndCacheArticles(articles)
    }
}