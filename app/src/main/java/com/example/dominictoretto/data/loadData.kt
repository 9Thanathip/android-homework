package com.example.dominictoretto.data

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun loadData() {
    CoroutineScope(Dispatchers.Main).launch {
        try {
            val movie = withContext(Dispatchers.IO) {
                Api().apiService.getData2()
            }
            val dataMovie = withContext(Dispatchers.IO) {
                Api().apiService.getMovie2()
            }
            val dataInfo = withContext(Dispatchers.IO){
                Api().apiService.getDataInfo2()
            }
        } catch (e: Exception) {
            Log.d("ddd", e.toString())
        }
    }
}
