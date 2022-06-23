package br.com.zup.movieflix.moviedetail.datasource

import android.os.Parcelable
import br.com.zup.movieflix.MARTIN_SCORSESE
import br.com.zup.movieflix.MARTIN_SCORSESE_INFO
import br.com.zup.movieflix.TARANTINO
import br.com.zup.movieflix.TARANTINO_INFO
import br.com.zup.movieflix.moviedetail.model.DirectorModel

class DirectorLocalDataSource {
    val directorList = mutableListOf<DirectorModel>(
        DirectorModel(
            TARANTINO,
            TARANTINO_INFO
        ),
        DirectorModel(
            MARTIN_SCORSESE,
            MARTIN_SCORSESE_INFO
        )
    )
}