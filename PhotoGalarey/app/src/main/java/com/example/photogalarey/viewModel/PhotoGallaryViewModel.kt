package com.example.photogalarey.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.photogalarey.API.FlickrFetchr
import com.example.photogalarey.model.GalleryItem

class PhotoGallaryViewModel: ViewModel() {


    val gallaItemLiveData: LiveData<List<GalleryItem>>

    init {
        gallaItemLiveData = FlickrFetchr().fetchPhotos()
    }

}