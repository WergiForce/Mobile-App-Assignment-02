package org.wit.geosite.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class GeositeMemStore : GeositeStore {

    val geosites = ArrayList<GeositeModel>()

    override fun findAll(): List<GeositeModel> {
        return geosites
    }

    override fun create(geosite: GeositeModel) {
        geosite.id = getId()
        geosites.add(geosite)
        logAll()
    }

    override fun update(geosite: GeositeModel) {
        val foundGeosite: GeositeModel? = geosites.find { p -> p.id == geosite.id }
        if (foundGeosite != null) {
            foundGeosite.title = geosite.title
            foundGeosite.description = geosite.description
            foundGeosite.image = geosite.image
            foundGeosite.lat = geosite.lat
            foundGeosite.lng = geosite.lng
            foundGeosite.zoom = geosite.zoom
            logAll()
        }
    }
    override fun delete(geosite: GeositeModel) {
        geosites.remove(geosite)
        logAll()
    }

    private fun logAll() {
        geosites.forEach { i("$it") }
    }
    override fun findById(id:Long) : GeositeModel? {
        val foundGeosite: GeositeModel? = geosites.find { it.id == id }
        return foundGeosite
    }
}