package br.com.zup.movieflix.moviedetail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zup.movieflix.moviedetail.model.DirectorModel
import br.com.zup.movieflix.moviedetail.repository.MovieDetailRepository

class MovieDetailViewModel {
    private val repository = MovieDetailRepository()
    private val _response: MutableLiveData<List<DirectorModel>> = MutableLiveData()
    val response: LiveData<List<DirectorModel>> = _response

    fun getData(director: DirectorModel) {
        try {
            _response.value = repository.getDirectorData(director)
        } catch (e:Exception) {
            Log.i("Error", "------> ${e.message}")
        }
    }

    //ponte entre view e model
}