package com.dsd.tmsbroadcast.Model

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.*
import com.dsd.tmsbroadcast.Classes.Cat
import com.dsd.tmsbroadcast.Services.CatBroadcastReceiver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CatsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _cat = savedStateHandle.getLiveData<Cat>("cat", createCat("Default"))
    private val catReceiver = CatBroadcastReceiver()
    val cat: MutableLiveData<Cat> = _cat


    private fun createCat(name: String = "Default"): Cat {
        return Cat(name)
    }

    fun addPlusOne(param: String) {
        when (param) {
            "eat" -> if (_cat.value!!.hunger < 100) {
                _cat.value!!.hunger += PLUSVALUE
                _cat.value = _cat.value
            }
            "drink" -> if (_cat.value!!.thirst < 100) {
                _cat.value!!.thirst += PLUSVALUE
                _cat.value = _cat.value
            }
            "care" -> if (_cat.value!!.caress < 100) {
                _cat.value!!.caress += PLUSVALUE
                _cat.value = _cat.value
            }
        }
    }

    private suspend fun decreaseParams(param: String) {
        when (param) {
            "hunger" -> {
                if (_cat.value!!.hunger > 0) {
                    _cat.value!!.hunger -= MINUSVALUE
                    _cat.value = _cat.value
                }
                delay(HUNGERDELAY)
            }
            "thirst" -> {
                if (_cat.value!!.thirst > 0) {
                    _cat.value!!.thirst -= MINUSVALUE
                    _cat.value = _cat.value
                }
                delay(THIRSTDELAY)
            }
            "caress" -> {
                if (_cat.value!!.caress > 0) {
                    _cat.value!!.caress -= MINUSVALUE
                    _cat.value = _cat.value
                }
                delay(CARESSDELAY)
            }
        }
    }

    init {
        if (!savedStateHandle.contains("cat")) {
            savedStateHandle["cat"] = createCat("Default")
        }


        for (param in listOf("hunger", "thirst", "caress")) {
            viewModelScope.launch(Dispatchers.Main) {
                while(true) {
                    decreaseParams(param)
                }
            }
        }
    }

    companion object {
        const val HUNGERDELAY = 6000L
        const val THIRSTDELAY = 10000L
        const val CARESSDELAY = 9000L
        const val PLUSVALUE = 10
        const val MINUSVALUE = 10
    }
}