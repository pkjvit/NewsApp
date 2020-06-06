package com.pkj.learn.newsbyjus.di

import android.content.Context
import com.pkj.learn.newsbyjus.news.HeadlinesFragment
import com.pkj.learn.newsbyjus.newsdetail.NewsDetailFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * @author Pankaj Jangid
 */
@Singleton
@Component(modules = [AppModule::class, SourceModule::class])
interface AppComponent{

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context):AppComponent
    }

    fun inject(fragment: HeadlinesFragment)
    fun inject(fragment: NewsDetailFragment)

}