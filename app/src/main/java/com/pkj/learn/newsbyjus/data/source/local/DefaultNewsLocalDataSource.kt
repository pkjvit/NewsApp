package com.pkj.learn.newsbyjus.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.pkj.learn.newsbyjus.data.Article
import com.pkj.learn.newsbyjus.data.Result
import java.lang.Exception

/**
 * @author Pankaj Jangid
 */
class DefaultNewsLocalDataSource(private val dao: ArticleDao) : NewsLocalDataSource{
    override suspend fun getArticles(): Result<List<Article>> {
        return Result.Success(dao.getArticles())
    }

    override suspend fun getArticle(articleId: String): Result<Article> {
        val article = dao.getArticleById(articleId)
        article?.let {
            return Result.Success(article)
        }
        return Result.Error(Exception("Article not found!"))
    }

    override fun observeArticles(): LiveData<Result<List<Article>>> {
        return dao.observeArticles().map { Result.Success(it) }
    }

    override fun observeArticle(articleId: String): LiveData<Result<Article>> {
        return dao.observeArticleById(articleId).map { Result.Success(it) }
    }

    override suspend fun insertArticles(articles: List<Article>): Result<List<Long>> {
        return Result.Success(dao.insertArticles(articles))
    }

    override suspend fun clearAndCacheArticles(articles: List<Article>) {
        dao.clearAndCacheArticles(articles)
    }
}