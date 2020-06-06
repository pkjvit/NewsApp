package com.pkj.learn.newsbyjus.di

import android.content.Context
import androidx.room.Room
import com.pkj.learn.newsbyjus.data.source.local.NewsDao
import com.pkj.learn.newsbyjus.data.source.local.NewsDatabase
import com.pkj.learn.newsbyjus.data.source.remote.NewsApi
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val API_TIMEOUT: Long = 30
private const val BASE_URL = "http://newsapi.org/v2/"

/**
 * @author Pankaj Jangid
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun providePupilApi() : NewsApi {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(context: Context): NewsDatabase {
        return Room
            .databaseBuilder(context, NewsDatabase::class.java, "NewsDb")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDatabase): NewsDao {
        return db.newsDao()
    }

    @Singleton
    @Provides
    fun provideDispatcher(): CoroutineDispatcher{
        return Dispatchers.IO
    }
}