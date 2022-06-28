package br.com.zup.movieflix.moviedetail.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ToggleButton
import androidx.lifecycle.ViewModelProvider
import br.com.zup.movieflix.CHAVE_MOVIE
import br.com.zup.movieflix.R
import br.com.zup.movieflix.databinding.ActivityMovieDetailBinding
import br.com.zup.movieflix.home.model.Movie
import br.com.zup.movieflix.moviedetail.viewmodel.MovieDetailViewModel

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel: MovieDetailViewModel by lazy {
        ViewModelProvider(this)[MovieDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observable()
        getPassedData()

    }

    private fun getPassedData() {
        val movie = intent.getParcelableExtra<Movie>(CHAVE_MOVIE)
        movie?.let { viewModel.getMovieWithDirector(it) }
        val toggle: ToggleButton = findViewById(R.id.btnFavorite)
        toggle.setOnCheckedChangeListener { _, isChecked ->
            movie?.favorite = isChecked
        }
    }

    private fun observable() {
        viewModel.response.observe(this) {
            binding.tvMovieTitle.text = it.movie.title
            binding.tvDirectorName.text = it.director.name

        }
    }
}