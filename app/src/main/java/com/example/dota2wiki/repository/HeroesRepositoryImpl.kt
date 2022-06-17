package com.example.dota2wiki.repository

import com.example.dota2wiki.model.HeroData
import com.example.dota2wiki.network.ApiService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class HeroesRepositoryImpl : HeroesRepository {

    private val getClient = ApiService()

    override fun readFromJson(response: String): List<HeroData> {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(List::class.java, HeroData::class.java)
        val jsonAdapter: JsonAdapter<List<HeroData>> = moshi.adapter(listType)
        return jsonAdapter.fromJson(response)!!
    }

    override fun apiRequest(sUrl: String): String? {
        return getClient.getRequest(sUrl)
    }

}