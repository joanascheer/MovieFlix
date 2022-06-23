package br.com.zup.movieflix.moviedetail.repository

import br.com.zup.movieflix.moviedetail.datasource.DirectorLocalDataSource
import br.com.zup.movieflix.moviedetail.model.DirectorModel

class MovieDetailRepository {
    private val dataSource = DirectorLocalDataSource()

    fun getDirectorData(director: DirectorModel): List<DirectorModel> = dataSource.directorList

}