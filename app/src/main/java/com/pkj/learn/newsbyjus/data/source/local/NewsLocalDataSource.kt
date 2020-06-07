package com.pkj.learn.newsbyjus.data.source.local

import androidx.lifecycle.LiveData
import com.pkj.learn.newsbyjus.data.Article
import com.pkj.learn.newsbyjus.data.Result

/**
 * @author Pankaj Jangid
 */
interface NewsLocalDataSource {

    suspend fun getArticles(): Result<List<Article>>

    suspend fun getArticle(articleId: Int): Result<Article>

    fun observeArticles(): LiveData<Result<List<Article>>>

    fun observeArticle(articleId: Int): LiveData<Result<Article>>

    suspend fun insertArticles(articles: List<Article>): Result<List<Long>>

    suspend fun clearAndCacheArticles(articles: List<Article>)
}