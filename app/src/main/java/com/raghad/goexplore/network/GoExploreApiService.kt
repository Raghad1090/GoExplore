package com.raghad.goexplore.network

import com.raghad.goexplore.PhotoItem
import com.raghad.goexplore.Response
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.flickr.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GoExploreApiService{

    @GET("/services/rest/?method=flickr.galleries.getPhotos&api_key=297612143bcb8e51e4defe734d1269ac&gallery_id=72157720302051615&format=json&nojsoncallback=1")
    suspend fun getPhotos(): Response

}

  object GoExploreApi{
      val retrofitService : GoExploreApiService by lazy {
          retrofit.create(GoExploreApiService::class.java) }

}