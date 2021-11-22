package com.example.e_commerce.view.main

import android.text.Html
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.model.products.Product
import com.example.e_commerce.repostries.ApiSeviceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "FavoriteViewModel"

class FavoriteViewModel:ViewModel() {

    private val apiRepo = ApiSeviceRepository.get()

    val favoriteLiveData = MutableLiveData<List<Product>>()
    val favoriteLiveDataError = MutableLiveData<String>()

    fun callFavorite(){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response= apiRepo.getFavoriteProduct()

                if (response.isSuccessful){
                response.body()?.run {
                    favoriteLiveData.postValue(this)
                    Log.d(TAG, this.toString())
                }
                }else{
                    Log.d(TAG, response.message())
                    favoriteLiveDataError.postValue(response.message())
                }

            }catch (e:Exception){
                Log.d(TAG, e.message.toString())
                favoriteLiveDataError.postValue(e.message.toString())

            }
        }
    }
}