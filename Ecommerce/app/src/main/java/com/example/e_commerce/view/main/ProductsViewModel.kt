package com.example.e_commerce.view.main

import android.nfc.Tag
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.database.RoomServiceRepository
import com.example.e_commerce.model.products.Product
import com.example.e_commerce.repostries.ApiSeviceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "ProductsViewModel"
class ProductsViewModel:ViewModel() {
    private val apiRepo = ApiSeviceRepository.get()
    private val RoomdatabaseRep = RoomServiceRepository.get()
    val productsLiveData = MutableLiveData<List<Product>>()
    val productsErrorLiveData = MutableLiveData<String?>()

    fun callProducts(){
       viewModelScope.launch(Dispatchers.IO) {

           try {
              val response = apiRepo.getProducts()
               if(response.isSuccessful){
                   response.body()?.run {
                       Log.d(TAG,this.toString())
                       productsLiveData.postValue(products)
                       RoomdatabaseRep.insertProducts(products)
                   }
               }else{
                   productsErrorLiveData.postValue(response.message())
                   productsLiveData.postValue(RoomdatabaseRep.getProducts())
               }

           }catch (e:Exception){
               Log.d(TAG,e.message.toString())
               productsErrorLiveData.postValue(e.message.toString())
               productsLiveData.postValue(RoomdatabaseRep.getProducts())

           }
       }

    }

    fun addFavoriteProduct(productId:Int){
        viewModelScope.launch(Dispatchers.IO) {

            try {

                val response = apiRepo.addFavoriteProduct(productId)
                if(!response.isSuccessful){
                    Log.d(TAG,response.message())
                    productsErrorLiveData.postValue(response.message())


                }else{
                    Log.d(TAG,response.message())
                    productsErrorLiveData.postValue(response.message())
                }

            }catch (e:Exception){
                Log.d(TAG,e.message.toString())
                productsErrorLiveData.postValue(e.message.toString())

            }
        }

    }

    fun removeFavoriteProducts(productId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.removeFavoriteProduct(productId)
                if(!response.isSuccessful){
                    Log.d(TAG,response.message())
                    productsErrorLiveData.postValue(response.message())
                }
            }catch (e:Exception){
                Log.d(TAG,e.message.toString())
                productsErrorLiveData.postValue(e.message.toString())

            }

        }
    }

}