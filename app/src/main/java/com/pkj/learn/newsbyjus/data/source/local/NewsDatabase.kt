package com.pkj.learn.newsbyjus.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pkj.learn.newsbyjus.data.Article

/**
 * @author Pankaj Jangid
 */
@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase(){

    abstract fun newsDao(): NewsDao

}