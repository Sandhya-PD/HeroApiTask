package com.example.heroapitask.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



private val BASE_URL="https://thesimplycoder.herokuapp.com"

private val moshi=Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit= Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface HeroApiService{
    @GET("/marvel-heroes")
    suspend fun getHero(): Hero
}

object HeroApi{
    val retrofitService:HeroApiService by lazy { retrofit.create(HeroApiService::class.java) }
}