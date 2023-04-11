package com.example.imdbrecyclerlesson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbrecyclerlesson.databinding.ActivityMainBinding

import com.example.imdbrecyclerlesson.retrofit.ImdbApi
import com.example.imdbrecyclerlesson.retrofit.ImdbItemResponse
import com.example.imdbrecyclerlesson.retrofit.ImdbItemsResult
import com.google.gson.Gson
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),onClickMovie {

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val retrofit= Retrofit.Builder().baseUrl("https://imdb-api.com/").addConverterFactory(
            GsonConverterFactory.create()).build()

        val movies=retrofit.create(ImdbApi::class.java)

        binding.recycler.layoutManager=LinearLayoutManager(this)

        binding.sv.setOnQueryTextListener(object: OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return  true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                search(p0, movies)
                return true
            }

        })




    }

    private fun search(p0: String?, movies: ImdbApi) {
        p0?.let {
            movies.getImdbExpression(it)
                .enqueue(object : Callback<ImdbItemsResult> {
                    override fun onResponse(
                        call: Call<ImdbItemsResult>,
                        response: Response<ImdbItemsResult>
                    ) {
                        binding.recycler.adapter = response.body()
                            ?.let { it1 -> ImdbAdapter(it1.results,this@MainActivity) }
                    }

                    override fun onFailure(call: Call<ImdbItemsResult>, t: Throwable) {

                    }

                })
        }
    }

    override fun onClick(item: ImdbItemResponse) {
        startActivity(Intent(this@MainActivity,Movie::class.java).apply {
            putExtra("item",item)
        })

        /*val intent = Intent(this, Movie::class.java).apply {
            putExtra("item", Gson().toJson(item))
        }
        startActivity(intent)*/



    }
}