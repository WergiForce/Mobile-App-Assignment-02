package org.wit.geosite.models

interface GeositeStore {
    fun findAll(): List<GeositeModel>
    fun create(geosite: GeositeModel)
    fun update(geosite: GeositeModel)
    fun findById(id:Long) : GeositeModel?
    fun delete(geosite: GeositeModel)
}