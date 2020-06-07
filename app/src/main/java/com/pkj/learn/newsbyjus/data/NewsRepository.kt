package com.pkj.learn.newsbyjus.data

import androidx.lifecycle.LiveData

/**
 * @author Pankaj Jangid
 */
interface NewsRepository {

    suspend fun refreshArticles()

    suspend fun getArticles(forceUpdate: Boolean): Result<List<Article>>

    suspend fun getArticle(articleId: Int): Result<Article>

    fun observeArticles(): LiveData<Result<List<Article>>>

    fun observeArticle(articleId: Int): LiveData<Result<Article>>

    suspend fun insertArticles(articles: List<Article>): Result<List<Long>>

    suspend fun clearAndCacheArticles(articles: List<Article>)

}