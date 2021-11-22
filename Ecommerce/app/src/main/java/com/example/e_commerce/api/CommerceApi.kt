package com.example.e_commerce.api

import com.example.e_commerce.model.identity.LoginBody
import com.example.e_commerce.model.identity.LoginModel
import com.example.e_commerce.model.identity.RegisterBody
import com.example.e_commerce.model.identity.UserInfoModel
import com.example.e_commerce.model.products.ProductModel
import com.example.e_commerce.model.products.Product
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface CommerceApi {

    @GET("/Common/Product/GetProductsForSell?page=0")
    ////@Header is to give an authorization
    suspend fun getProducts(@Header("Authorization") token:String
    ):Response<ProductModel>
    //-------------------------------------------------------------------------------

    @GET("/Ecommerce/User/GetFavorites")
    suspend fun getFavoriteProducts(@Header("Authorization") token:String):
            Response<List<Product>>
    ///------------------------------------------------------------------------------
    @POST("/Ecommerce/User/AddProductToFavorites")
    suspend fun addFavoriteProduct(@Header("Authorization") token:String,
                                   @Query("productId") productId:Int

    ):Response<ResponseBody>
    ///------------------------------------------------------------------------------------
    @POST("/Ecommerce/User/RemoveProductFromFavorties")
    suspend fun removeFavoriteProducts(@Header("Authorization") token:String,
                                       @Query("productId") productId:Int
    ):Response<ResponseBody>
    //------------------------------------------------------------------------
    /// here below we are taking the body because we want to post the user Registration
    @POST("/Common/Identity/Register")
    suspend fun userRegister(@Body registerBody: RegisterBody):Response<ResponseBody>
    //-----------------------------------------------------------------------------

    @POST("/Common/Identity/Login")
    suspend fun userLogin(@Body loginBody: LoginBody):Response<LoginModel>
    ///---------------------------------------------------------------------------
    @GET("/Common/Identity/GetUserData")
    suspend fun getUserInfo(
        @Header("Authorization") token:String,

    ): Response<UserInfoModel>

    //---------------------------------------------------------------------------
    /// the annotation below is for uploading anytime of files
    @Multipart
    @POST("/Common/Identity/UploadImage")
    suspend fun uploadUserImage(@Header("Authorization") token:String,
                                @Part file:MultipartBody.Part):Response<ResponseBody>

}