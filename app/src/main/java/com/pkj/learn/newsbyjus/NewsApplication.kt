package com.pkj.learn.newsbyjus

import android.app.Application
import com.pkj.learn.newsbyjus.di.AppComponent
import com.pkj.learn.newsbyjus.di.DaggerAppComponent

/**
 * @author Pankaj Jangid
 */
class NewsApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

}