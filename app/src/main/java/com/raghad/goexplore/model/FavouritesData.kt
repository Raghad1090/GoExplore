package com.raghad.goexplore.model

import com.squareup.moshi.Json

data class FavouritesData (

    @Json(name="title")
    val title: String? = null,



    @Json(name="id")
    val id: String? = null,

    @Json(name="image")
    val imageUrl: String ?= null,

    ) {}



    data class imageUrl (

        @Json(name="farm")
        val farm: Int? = null,

        @Json(name="id")
        val id: String? = null,

        @Json(name="secret")
        val secret: String? = null,

        @Json(name="server")
        val server: String? = null) {

        val imageUrl: String get() = "https://farm${farm}.static.flickr.com/${server}/${id}_${secret}.jpg"
    }


