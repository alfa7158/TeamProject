package com.example.photogalarey

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.photogalarey.API.FlickrFetchr
import java.net.URL
import java.util.concurrent.ConcurrentHashMap

private const val TAG = "ThumbnailDownloader"
private const val MESSAGE_DOWNLOAD = 0
class ThumbnailDownloader<T>(
    private val responseHandler:Handler,
    private val onThumbnailDownloaded: (T, Bitmap) -> Unit
):
        HandlerThread(TAG){

            private lateinit var requestHandler:Handler
            private val requestMap = ConcurrentHashMap<T,String>()
            private val FlickrFetchr:FlickrFetchr = FlickrFetchr()


    val fragmentLifeCyclerObserver:LifecycleObserver = object :LifecycleObserver{
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun setUp(){
            Log.d(TAG,"Starting thr background process")
            start()
            looper

        }



    }

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        requestHandler = @SuppressLint("HandlerLeak")
        object : Handler(){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if(msg.what== MESSAGE_DOWNLOAD){
                    val traget = msg.obj as T
                    Log.d(TAG,"Got a request URL: ${requestMap[traget]}")
                    handleRequest(traget)


                }
            }
        }
        }

    private fun handleRequest(traget: T) {
        val url = requestMap[traget] ?:return
        val bitmap = FlickrFetchr.fetchPhoto(url)?:return

        responseHandler.post(Runnable {
            if(requestMap[traget] !=url){

                return@Runnable
            }
            onThumbnailDownloaded(traget,bitmap)
        })

    }

    fun queueThumnail(traget: T,url:String){
        Log.d(TAG,"GOT A URL :$url")
        requestMap[traget] = url
        requestHandler.obtainMessage(MESSAGE_DOWNLOAD,traget).sendToTarget()

    }
}






