package com.pkj.learn.newsbyjus.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.pkj.learn.newsbyjus.data.Article

/**
 * @author Pankaj Jangid
 */
@Dao
interface ArticleDao {


    /**
     * Select all articles from the article table.
     *
     * @return all article.
     */
    @Query("SELECT * FROM Article")
    suspend fun getArticles(): List<Article>


    /**
     * Select a article by id.
     *
     * @param articleId the article id.
     * @return the article with articleId.
     */
    @Query("SELECT * FROM Article WHERE id = :articleId")
    suspend fun getArticleById(articleId: String): Article?


    /**
     * Observes a single article.
     *
     * @param articleId the article id.
     * @return the article with id.
     */
    @Query("SELECT * FROM Article WHERE id = :articleId")
    fun observeArticleById(articleId: String): LiveData<Article>


    /**
     * Observes list of Articles.
     *
     * @return all articles.
     */
    @Query("SELECT * FROM Article")
    fun observeArticles(): LiveData<List<Article>>


    /**
     * Insert articles into the article table
     */
    @Insert
    suspend fun insertArticles(articles: List<Article>): List<Long>


    /**
     * Delete all articles.
     */
    @Query("DELETE FROM Article")
    suspend fun deleteArticles()


    @Transaction
    suspend fun clearAndCacheArticles(articles: List<Article>) {
        deleteArticles()
        insertArticles(articles)
    }

}