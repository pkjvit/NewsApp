package com.pkj.learn.newsbyjus.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pkj.learn.newsbyjus.data.Article
import com.pkj.learn.newsbyjus.data.NewsRepository
import com.pkj.learn.newsbyjus.data.Result

/**
 * @author Pankaj Jangid
 */
class FakeNewsRepository : NewsRepository{
    var articles: LinkedHashMap<Int, Article> = LinkedHashMap()

    private val observableArticles = MutableLiveData<Result<List<Article>>>()

    override suspend fun refreshArticles() {
        TODO("Not yet implemented")
    }

    override suspend fun getArticles(forceUpdate: Boolean): Result<List<Article>> {
        return Result.Success(articles.values.toList())
    }

    override suspend fun getArticle(articleId: Int): Result<Article> {
        TODO("Not yet implemented")
    }

    override fun observeArticles(): LiveData<Result<List<Article>>> {
        TODO("Not yet implemented")
    }

    override fun observeArticle(articleId: Int): LiveData<Result<Article>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertArticles(articles: List<Article>): Result<List<Long>> {
        TODO("Not yet implemented")
    }

    override suspend fun clearAndCacheArticles(articles: List<Article>) {
        TODO("Not yet implemented")
    }
}