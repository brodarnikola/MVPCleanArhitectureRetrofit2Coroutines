package com.vjezba.mvpcleanarhitecturegithub.presentation

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vjezba.mvpcleanarhitecturegithub.core.coreModule
import com.vjezba.mvpcleanarhitecturegithub.data.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(presentationModule, coreModule, dataModule)
        }
    }
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun ViewGroup.inflateLayout(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}
