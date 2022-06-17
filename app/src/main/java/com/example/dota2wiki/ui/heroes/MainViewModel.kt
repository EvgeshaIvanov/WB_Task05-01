package com.example.dota2wiki.ui.heroes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dota2wiki.model.HeroData
import com.example.dota2wiki.repository.HeroesRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val heroesRepositoryImpl = HeroesRepositoryImpl()

    val heroDataList = MutableLiveData<List<HeroData>>()

    fun getHeroes(sUrl: String): List<HeroData>? {
        var heroData: List<HeroData>? = null
        viewModelScope.launch(Dispatchers.IO) {
            val response = heroesRepositoryImpl.apiRequest(sUrl)
            val result = response?.let { heroesRepositoryImpl.readFromJson(it) }
            if (result != null) {
                withContext(Dispatchers.Main) {
                    heroData = result
                    heroDataList.value = heroData!!
                }
            }
        }
        return heroData
    }
}