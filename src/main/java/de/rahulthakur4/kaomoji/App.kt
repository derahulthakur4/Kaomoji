package de.rahulthakur4.kaomoji

import android.app.Application
import de.rahulthakur4.kaomoji.extensions.DelegatesExt

class App : Application() {

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}