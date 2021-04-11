package com.example.mynews.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mynews.R
import com.example.mynews.adapters.NewsAdapter
import com.example.mynews.databinding.FragmentTeslaBinding
import com.example.mynews.databinding.TechCrunchFragmentBinding
import com.example.mynews.model.News
import com.example.mynews.retrofit.APIService
import com.example.mynews.retrofit.ServiceBuilder
import com.example.mynews.viewModel.TechCrunchViewModel
import com.example.mynews.viewModel.TeslaViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TechCrunchFragment : Fragment() {
    private lateinit var viewModel: TechCrunchViewModel
    private lateinit var dataBinding: TechCrunchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(TechCrunchViewModel::class.java)
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.tech_crunch_fragment, container, false)
        getTechCrunchNewsList()
        return dataBinding.root
    }

    private fun getTechCrunchNewsList() {
        dataBinding.progressBar.visibility = View.VISIBLE
        val request = ServiceBuilder.buildService(APIService::class.java)
        val call = request.getTechCrunchList(
            "techcrunch",
            getString(R.string.news_api_key)
        )

        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    dataBinding.progressBar.visibility = View.GONE
                    dataBinding.rvTextCrunchList.apply {
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