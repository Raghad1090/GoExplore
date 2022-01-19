package com.raghad.goexplore.model

import com.squareup.moshi.Json

data class FavouritesData (

    @Json(name="title")
    val title: String? = null,

    @Json(name="id")
    val id: String? = null,

    @Json(name="image")
    val imageUrl: String ?= null,

    )



