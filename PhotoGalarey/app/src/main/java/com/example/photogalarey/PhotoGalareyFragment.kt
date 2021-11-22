package com.example.photogalarey

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photogalarey.adapter.PhotoAdapter
import com.example.photogalarey.viewModel.PhotoGallaryViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PhotoGalleryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "PhotoGalleryFragment"
class PhotoGalleryFragment : Fragment() {

    private lateinit var photoGalleryViewModel: PhotoGallaryViewModel
    private lateinit var photoRecyclerView: RecyclerView
    private lateinit var thumbnailDownloader: ThumbnailDownloader<PhotoAdapter.PhotoHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoGalleryViewModel = ViewModelProvider(this).get(PhotoGallaryViewModel::class.java)
        val responseHandler = Handler()
        thumbnailDownloader = ThumbnailDownloader(responseHandler) {
                photoHolder, bitmap ->
            val drawable = BitmapDrawable(resources, bitmap)
            photoHolder.bindDrawable(drawable)
        }
        lifecycle.addObserver(thumbnailDownloader.fragmentLifeCyclerObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_photo_galarey, container, false)
        photoRecyclerView = view.findViewById(R.id.photo_recycler_View)
        photoRecyclerView.layoutManager = GridLayoutManager(context, 3)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoGalleryViewModel.gallaItemLiveData.observe(
            viewLifecycleOwner,
            Observer { galleryItem ->
                Log.d(TAG, "Gallery Item")
                photoRecyclerView.adapter = PhotoAdapter(galleryItem, thumbnailDownloader)
            }
        )

    }

    companion object{
        fun newinstance() = PhotoGalleryFragment()
    }

}