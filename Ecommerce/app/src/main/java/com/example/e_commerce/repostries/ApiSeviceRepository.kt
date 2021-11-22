package com.example.e_commerce.repostries

import android.annotation.SuppressLint
import android.content.Context
import com.example.e_commerce.api.CommerceApi
import com.example.e_commerce.model.identity.LoginBody
import com.example.e_commerce.model.identity.RegisterBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.Exception
private val constanceBaseUrl = "http://18.196.156.64"
 const val SHARED_PREF_FILE = "Auth"
 const val TOKEN_KEY ="token"

class ApiSeviceRepository(val context:Context) {
    private val retrofitService = Retrofit.Builder()
        .baseUrl(constanceBaseUrl)
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val retrofitApi = retrofitService.create(CommerceApi::class.java)
    //// Below we created a shared preference to get the token
    private val sharedPref = context.getSharedPreferences(SHARED_PREF_FILE,Context.MODE_PRIVATE)
    // the line below causes error
   // private val accessToken = sharedPref.getString(TOKEN_KEY,"")
    suspend fun getUserInfo() = retrofitApi.getUserInfo("Bearer ${ sharedPref.getString(TOKEN_KEY,"")}")

    suspend fun  getProducts() = retrofitApi.getProducts("Bearer ${ sharedPref.getString(TOKEN_KEY,"")}")

    suspend fun addFavoriteProduct(productId:Int) = retrofitApi.
    addFavoriteProduct("Bearer ${ sharedPref.getString(TOKEN_KEY,"")}",productId)
    suspend fun removeFavoriteProduct(productId:Int) = retrofitApi.
    removeFavoriteProducts("Bearer ${ sharedPref.getString(TOKEN_KEY,"")}",productId)

    suspend fun register(registerBody: RegisterBody) =
        retrofitApi.userRegister(registerBody)

    suspend fun login(loginBody: LoginBody) =  retrofitApi.userLogin(loginBody)


    suspend fun uploadUserImage(file:File):Response<ResponseBody>{
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("imageFile",file.name,requestFile)
        return retrofitApi.uploadUserImage("Bearer ${ sharedPref.getString(TOKEN_KEY,"")}",body)
    }

    suspend fun getFavoriteProduct() = retrofitApi.getFavoriteProducts("Bearer ${ sharedPref.getString(TOKEN_KEY,"")}")

    companion object{
        @SuppressLint("StaticFieldLeak")
        private var instance:ApiSeviceRepository?=null

        fun init(context: Context){
            if(instance==null){
                instance = ApiSeviceRepository(context)
            }

        }

        fun get():ApiSeviceRepository{
            return instance?:throw Exception("Api must be initialized")

        }
    }



}