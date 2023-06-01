package com.example.imdbrecyclerlesson.ui.movies

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbrecyclerlesson.Creator
import com.example.imdbrecyclerlesson.MoviesAdapter
import com.example.imdbrecyclerlesson.MoviesApplication
import com.example.imdbrecyclerlesson.R
import com.example.imdbrecyclerlesson.domain.models.Movie
import com.example.imdbrecyclerlesson.domain.models.MoviesState
import com.example.imdbrecyclerlesson.presentation.movies.MoviesSearchPresenter
import com.example.imdbrecyclerlesson.presentation.movies.MoviesView
import com.example.imdbrecyclerlesson.ui.poster.Poster
import moxy.MvpActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class MoviesActivity : MvpActivity(), MoviesView {


    @InjectPresenter
    lateinit var moviesSearchPresenter: MoviesSearchPresenter

    private var textWatcher: TextWatcher? = null





    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }
    private lateinit var queryInput: EditText
    private lateinit var placeholderMessage: TextView
    private lateinit var moviesList: RecyclerView
    private lateinit var progressBar: ProgressBar





    private var isClickAllowed = true

    private val handler = Handler(Looper.getMainLooper())

    private val adapter = MoviesAdapter {
        if (clickDebounce()) {
            val intent = Intent(this, Poster::class.java)
            intent.putExtra("poster", it)
            startActivity(intent)
        }
    }

   // private val moviesSearchPresenter = Creator.provideMoviesSearchPresenter(this,this,)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        placeholderMessage = findViewById(R.id.placeholderMessage)
        queryInput = findViewById(R.id.queryInput)
        moviesList = findViewById(R.id.locations)
        progressBar = findViewById(R.id.progressBar)


        /*if (moviesSearchPresenter == null) {
            moviesSearchPresenter = Creator.provideMoviesSearchPresenter(
                context = this.applicationContext,
            )
            (this.applicationContext as? MoviesApplication)?.moviesSearchPresenter = moviesSearchPresenter
        }*/


       /* moviesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        moviesList.adapter = adapter*/

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                moviesSearchPresenter.searchDebounce(s?.toString() ?: "")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        textWatcher?.let { queryInput.addTextChangedListener(it) }


    }

    @ProvidePresenter
    fun providePresenter(): MoviesSearchPresenter {
        return Creator.provideMoviesSearchPresenter(
            context = this.applicationContext,
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        textWatcher?.let { queryInput.removeTextChangedListener(it) }


        if (isFinishing()) {
            // Очищаем ссылку на Presenter в Application
            (this.application as? MoviesApplication)?.moviesSearchPresenter = null
        }
    }



    private fun clickDebounce() : Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }


    fun showLoading() {
        moviesList.visibility = View.GONE
        placeholderMessage.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    fun showError(errorMessage: String) {
        moviesList.visibility = View.GONE
        placeholderMessage.visibility = View.VISIBLE
        progressBar.visibility = View.GONE

        placeholderMessage.text = errorMessage

    }

    fun showEmpty(emptyMessage: String) {
        showError(emptyMessage)
    }

    fun showContent(movies: List<Movie>) {
        moviesList.visibility = View.VISIBLE
        placeholderMessage.visibility = View.GONE
        progressBar.visibility = View.GONE

        adapter.movies.clear()
        adapter.movies.addAll(movies)
        adapter.notifyDataSetChanged()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun render(state: MoviesState) {
        when {
            state.isLoading -> showLoading()
            state.errorMessage != null -> showEmpty(state.errorMessage)
            else -> showContent(state.movies)
        }
    }


}

