package com.example.bore_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class showActivityFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val testToBeShown:TextView = view.findViewById(R.id.activityTextView)
        // Original link : https://www.boredapi.com/api/activity

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            testToBeShown.text = activity.toString()
            swipeRefreshLayout.isRefreshing = false
        }
        val retrofit = Retrofit.Builder().baseUrl("https://www.boredapi.com").addConverterFactory(
            GsonConverterFactory.create()).build()
        val activityAPI = retrofit.create(ActivityAPI::class.java)
        activityAPI.getActivity().enqueue(object : Callback<ToActivityModel> {
            override fun onResponse(
                call: Call<ToActivityModel>,
                response: Response<ToActivityModel>
            ) {
                testToBeShown.text = response.body()!!.activity


            }





            override fun onFailure(call: Call<ToActivityModel>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })

    }
    }


