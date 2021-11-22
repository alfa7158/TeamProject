package com.example.photogalarey.API

import com.example.photogalarey.model.GalleryItem
import com.google.gson.annotations.SerializedName

class PhotoResponse {


        @SerializedName("photo")
        lateinit var galleryItems: List<GalleryItem>

}