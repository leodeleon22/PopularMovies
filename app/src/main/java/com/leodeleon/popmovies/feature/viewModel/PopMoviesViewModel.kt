package com.leodeleon.popmovies.feature.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.leodeleon.popmovies.data.MovieRepository
import com.leodeleon.popmovies.model.Movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class PopMoviesViewModel
constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {
  val popMoviesLiveData = MutableLiveData<PagedList<Movie>>()

  fun loadPopularMovies() {
     movieRepository.getPopMovies(subscriptions)
        .subscribeBy{
          popMoviesLiveData.value = it
        }
        .addTo(subscriptions)
  }
}
