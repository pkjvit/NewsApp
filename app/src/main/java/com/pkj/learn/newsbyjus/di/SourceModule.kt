package com.pkj.learn.newsbyjus.di

import com.pkj.learn.newsbyjus.data.NewsRepository
import com.pkj.learn.newsbyjus.data.source.DefaultNewsRepository
import com.pkj.learn.newsbyjus.data.source.local.DefaultNewsLocalDataSource
import com.pkj.learn.newsbyjus.data.source.local.NewsLocalDataSource
import com.pkj.learn.newsbyjus.data.source.remote.DefaultNewsRemoteDataSource
import com.pkj.learn.newsbyjus.data.source.remote.NewsRemoteDataSource
import dagger.Binds
import dagger.Module

/**
 * @author Pankaj Jangid
 */
@Module
abstract class SourceModule {

    @Binds
    abstract fun provideLocalDataSource(localDataSource: DefaultNewsLocalDataSource): NewsLocalDataSource

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSource: DefaultNewsRemoteDataSource): NewsRemoteDataSource

    @Binds
    abstract fun provideNewsRepository(pupilRepository: DefaultNewsRepository): NewsRepository


}