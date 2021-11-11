package com.sadikahmetozdemir.sadik_fodamy.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.api.RecipesAPI
import com.sadikahmetozdemir.sadik_fodamy.shared.local.LikeResponse
import com.sadikahmetozdemir.sadik_fodamy.shared.local.RecipesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DenemeFragment : Fragment() {
    private val BASE_URL="https://fodamy.mobillium.com/"
    private var arrayList:ArrayList<LikeResponse>?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deneme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service=retrofit.create(RecipesAPI::class.java)
        val call= service.getData()
        call.enqueue(object : Callback<LikeResponse>{
            override fun onResponse(call: Call<LikeResponse>, response: Response<LikeResponse>) {
                if(response.isSuccessful){
                    response.body()?.let {
                    }


                }
            }

            override fun onFailure(call: Call<LikeResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }
}