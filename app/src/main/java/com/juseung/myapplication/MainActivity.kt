package com.juseung.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.juseung.myapplication.BuildConfig.API_KEY
import com.juseung.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val CookingAdapter by lazy {
        CookingAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CookingAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        binding.btnGet.setOnClickListener {
            retrofitWork()
        }
    }


    private fun retrofitWork() {
        val service = RetrofitApi.cookingService

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getDataCoroutine(BuildConfig.API_KEY,"COOKRCP01","json","1","20" )

            withContext(Dispatchers.Main) {
                if( response.isSuccessful) {
                    val result = response.body()?.cOOKRCP01?.row
                    result?.let {
                        CookingAdapter.submitList(it)
                    }
                } else {
                    Log.d("TAG", response.code().toString())
                }
            }

        }
    }
}