package com.example.mynews.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynews.R
import com.example.mynews.adapters.NewsAdapter
import com.example.mynews.databinding.FragmentAppleBinding
import com.example.mynews.model.News
import com.example.mynews.retrofit.APIService
import com.example.mynews.retrofit.ServiceBuilder
import com.example.mynews.viewModel.AppleNewsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class AppleNewFragment : Fragment() {
    private lateinit var appleNewsViewModel: AppleNewsViewModel
    private lateinit var dataBinding: FragmentAppleBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appleNewsViewModel = ViewModelProvider(this).get(AppleNewsViewModel::class.java)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_apple, container, false)

        getAppleNewsList();
        return dataBinding.root
    }

    private fun getAppleNewsList() {
        val yesterday = LocalDate.now().minusDays(1)
        dataBinding.progressBar.visibility = View.VISIBLE
        val request = ServiceBuilder.buildService(APIService::class.java)
        val call = request.getAppleNewsList(
            "apple",
            yesterday.toString(),
            yesterday.toString(),
            "popularity",
            getString(R.string.news_api_key)
        )

        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    dataBinding.progressBar.visibility = View.GONE
                    dataBinding.rvAppleList.apply {
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