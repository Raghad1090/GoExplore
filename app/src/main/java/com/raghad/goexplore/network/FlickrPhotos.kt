package com.raghad.goexplore.network

import com.raghad.goexplore.model.imageUrl
import com.squareup.moshi.Json

data class Response(

    @Json(name="stat")
    val stat: String? = null,

    @Json(name="photos")
    val photos: Photos? = null
)

data class PhotoItem(

    @Json(name="owner")
    val owner: String? = null,

    @Json(name="server")
    val server: String? = null,

    @Json(name="is_primary")
    val isPrimary: Int? = null,

    @Json(name="has_comment")
    val hasComment: Int? = null,

    @Json(name="ispublic")
    val ispublic: Int? = null,

    @Json(name="isfriend")
    val isfriend: Int? = null,

    @Json(name="farm")
    val farm: Int? = null,

    @Json(name="id")
    val id: String? = null,

    @Json(name="secret")
    val secret: String? = null,

    @Json(name="title")
    val title: String= "",

    @Json(name="isfamily")
    val isfamily: Int? = null,

    @Json(name="image")
    val imageUrll: List<imageUrl?>? = null,

    ){

    val imageUrl: String  get() = "https://farm${farm}.static.flickr.com/${server}/${id}_${secret}.jpg"
}


data class Photos(

    @Json(name="perpage")
    val perpage: String? = null,

    @Json(name="total")
    val total: Int? = null,

    @Json(name="pages")
    val pages: Int? = null,

    @Json(name="photo")
    val photo: List<PhotoItem?>? = null,

    @Json(name="page")
    val page: Int? = null
)

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
