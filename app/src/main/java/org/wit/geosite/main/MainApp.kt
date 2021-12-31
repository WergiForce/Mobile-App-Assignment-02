package org.wit.geosite.main

import android.app.Application
import org.wit.geosite.models.GeositeJSONStore
import org.wit.geosite.models.GeositeMemStore
import org.wit.geosite.models.GeositeModel
import org.wit.geosite.models.GeositeStore
import org.wit.geosite.room.GeositeStoreRoom
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var geosites: GeositeStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        geosites = GeositeStoreRoom(applicationContext)

        i("Geosite started")

    }
}