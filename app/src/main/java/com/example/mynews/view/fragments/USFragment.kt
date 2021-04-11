package com.example.mynews.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mynews.R
import com.example.mynews.adapters.NewsAdapter
import com.example.mynews.databinding.FragmentTeslaBinding
import com.example.mynews.databinding.FragmentUsBinding
import com.example.mynews.model.News
import com.example.mynews.retrofit.APIService
import com.example.mynews.retrofit.ServiceBuilder
import com.example.mynews.viewModel.TechCrunchViewModel
import com.example.mynews.viewModel.USViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class USFragment : Fragment() {
    private lateinit var viewModel: USViewModel
    private lateinit var dataBinding: FragmentUsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(USViewModel::class.java)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_us, container, false)
        getUSNewsList()
        return dataBinding.root
    }

    private fun getUSNewsList() {
        dataBinding.progressBar.visibility = View.VISIBLE
        val request = ServiceBuilder.buildService(APIService::class.java)
        val call = request.getBusinessHeadlineUsaNewsList(
            "us",
            "business",
            getString(R.string.news_api_key)
        )

        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    dataBinding.progressBar.visibility = View.GONE
                    dataBinding.rvUsList.apply {
                        setHasFixedSize(true)
                        layoutManager =
                            GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
                        adapter = NewsAdapter(response.body()!!.articles)
                    }
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                dataBinding.progressBar.visibility = View.GONE
            }
        })
    }
}