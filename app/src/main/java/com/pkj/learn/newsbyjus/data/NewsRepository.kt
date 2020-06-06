package com.pkj.learn.newsbyjus.data

import androidx.lifecycle.LiveData

/**
 * @author Pankaj Jangid
 */
interface NewsRepository {


    suspend fun getArticles(): Result<List<Article>>

    suspend fun getArticle(articleId: String): Result<Article>

    fun observeArticles(): LiveData<Result<List<Article>>>

    fun observeArticle(articleId: String): LiveData<Result<Article>>

    suspend fun insertArticles(articles: List<Article>): Result<List<Long>>

    suspend fun clearAndCacheArticles(articles: List<Article>)

}