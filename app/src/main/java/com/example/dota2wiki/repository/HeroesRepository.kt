package com.example.dota2wiki.repository

import com.example.dota2wiki.model.HeroData

interface HeroesRepository {

    fun readFromJson(response: String): List<HeroData>

    fun apiRequest(sUrl: String): String?

}