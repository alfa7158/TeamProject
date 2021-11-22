package com.example.e_commerce.view.identity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.model.identity.UserInfoModel
import com.example.e_commerce.repostries.ApiSeviceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.lang.Exception

private const val TAG = "ProfileViewMode"
class ProfileViewMode:ViewModel() {
    private val apiRepo = ApiSeviceRepository.get()

    /// note, we need LiveData for each request
    // for example, below we created live data for the userinfo and the image

    val profileLiveData = MutableLiveData<UserInfoModel>()
    val uploadImageLiveData = MutableLiveData<String>()

    val profileErrorLiveData = MutableLiveData<String>()

    fun callUserProfile(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.getUserInfo()
                 if (response.isSuccessful){
                     response.body()?.run {
                         profileLiveData.postValue(this)
                         Log.d(TAG, this.toString())
                     }
                     }else{
                         Log.d(TAG,response.message())
                         profileErrorLiveData.postValue(response.message())
                     }
                 }   catch (e:Exception){
                Log.d(TAG,e.message.toString())
                profileErrorLiveData.postValue(e.message.toString())

            }
            }
        }

    fun uploadUserImage(file:File){
        viewModelScope.launch(Dispatchers.IO ) {
            try {
                val response = apiRepo.uploadUserImage(file)
                if(response.isSuccessful){
                    uploadImageLiveData.postValue("successful")

                }else{
                    Log.d(TAG,response.message())
                    profileErrorLiveData.postValue(response.message())
                }
            }catch (e:Exception){
                Log.d(TAG,e.message.toString())
                profileErrorLiveData.postValue(e.message.toString())
            }
        }
    }

    }

