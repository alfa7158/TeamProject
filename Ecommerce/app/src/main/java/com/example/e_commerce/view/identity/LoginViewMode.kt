package com.example.e_commerce.view.identity

import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.model.identity.LoginBody
import com.example.e_commerce.model.identity.LoginModel
import com.example.e_commerce.repostries.ApiSeviceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "LoginViewModel"
class LoginViewMode:ViewModel() {
    private val apiRep = ApiSeviceRepository.get()

    val loginLiveData = MutableLiveData<LoginModel>()
    val loginErrorLiveData = MutableLiveData<String>()

    fun login(email: String,password: String) {

        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = apiRep.login(LoginBody(email,password,true))

                if (response.isSuccessful) {
                    Log.d(TAG,response.body().toString())
                    response.body()?.run {
                        loginLiveData.postValue(this)
                    }
                } else
                {
                    Log.d(TAG, response.message())
                    loginErrorLiveData.postValue(response.message())
                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                loginErrorLiveData.postValue(e.message.toString())
            }

        }

    }

    }

