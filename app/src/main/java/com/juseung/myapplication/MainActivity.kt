package com.juseung.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.BuildConfig
import com.juseung.myapplication.BuildConfig.API_KEY
import com.juseung.myapplication.Repository.CookingRepository
import com.juseung.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CookingViewModel
    private lateinit var viewModelFactory: CookingViewModelFactory
    private val cookingAdapter by lazy {
        CookingAdapter()
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = cookingAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }


        viewModelFactory = CookingViewModelFactory(CookingRepository())
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(CookingViewModel::class.java)




        binding.btnGet.setOnClickListener {
            viewModel.requestCookingRepositories(
                "1a12c61ba6ae41a6820f",
                "COOKRCP01",
                "json",
                "1",
                "20"
            )
        }

        viewModel.cookingRepositories.observe(this){
            cookingAdapter.submitList(it)
        }













    }

//    private fun retrofitWork() {
//        val service = RetrofitApi.cookingService
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = service.getDataCoroutine("1a12c61ba6ae41a6820f","COOKRCP01","json","1","20" )
//
//            withContext(Dispatchers.Main) {
//                if( response.isSuccessful) {
//                    val result = response.body()?.cOOKRCP01?.row
//                    result?.let {
//                        adapter.submitList(it)
//                    }
//                } else {
//                    Log.d("TAG", response.code().toString())
//                }
//            }
//
//        }
//    }

}
