package com.example.e_commerce.view.identity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.model.identity.RegisterBody
import com.example.e_commerce.repostries.ApiSeviceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.lang.Exception

private const val TAG = "RegisterViewModel"
class RegisterViewModel: ViewModel() {
    private val apiRep = ApiSeviceRepository.get()
    val registerLiveData = MutableLiveData<String>()
    val registerErrorLiveData = MutableLiveData<String>()

    fun register(firstName: String, lastName: String, email: String, password: String) {

        try {

            viewModelScope.launch(Dispatchers.IO) {
                val response =
                    apiRep.register(RegisterBody(email, firstName, lastName, password, 1))
                if (response.isSuccessful) {
                    Log.d(TAG, response.body().toString())
                    registerLiveData.postValue("Successful")
                } else {
                    Log.d(TAG, response.message())
                    registerErrorLiveData.postValue(response.message())

                }
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            registerErrorLiveData.postValue(e.message.toString())
        }
    }



}