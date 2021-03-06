package com.leodeleon.popmovies.feature.view

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView
import com.leodeleon.popmovies.R
import com.leodeleon.popmovies.feature.adapters.MoviesAdapter
import com.leodeleon.popmovies.feature.common.AdapterConstants
import com.leodeleon.popmovies.feature.common.BaseFragment
import com.leodeleon.popmovies.feature.common.ScrollListener
import com.leodeleon.popmovies.feature.viewModel.PopMoviesViewModel
import com.leodeleon.popmovies.model.Movie
import com.leodeleon.popmovies.util.Constants.EXTRA_MOVIE
import com.leodeleon.popmovies.util.Constants.LAYOUT_MANAGER_STATE
import com.leodeleon.popmovies.util.inflate
import com.leodeleon.popmovies.util.observe
import io.reactivex.disposables.Disposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_movies.progress_bar
import kotlinx.android.synthetic.main.fragment_movies.recycler_view
import kotlinx.android.synthetic.main.fragment_movies.text_placeholder
import org.koin.android.architecture.ext.viewModel

class PopMoviesFragment : BaseFragment() {

  private val viewModel: PopMoviesViewModel by viewModel()
  private lateinit var layoutManager: GridLayoutManager
  //private lateinit var scrollListener: ScrollListener
  private val adapter = MoviesAdapter { view, movie ->
    Navigation.findNavController(view).navigate(R.id.details, bundleOf(EXTRA_MOVIE to movie))
  }

  //private val paginator = PublishProcessor.create<Int>()

  //private var pageNumber = 1

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = container?.inflate(R.layout.fragment_movies, false)
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    layoutManager = recycler_view.layoutManager as GridLayoutManager
    progress_bar.visibility = View.VISIBLE
    viewModel.loadPopularMovies()
    setMoviesData()
    setRecyclerView()
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    subscribe()

//    if (savedInstanceState == null) {
//      paginator.onNext(pageNumber)
//    }
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    savedInstanceState?.let {
      val state = it.getParcelable<Parcelable>(LAYOUT_MANAGER_STATE)
      layoutManager.onRestoreInstanceState(state)
    }
  }

  override fun onSaveInstanceState(outState: Bundle) {
    outState.putParcelable(LAYOUT_MANAGER_STATE, layoutManager.onSaveInstanceState())
    super.onSaveInstanceState(outState)
  }

  private fun setData(movieList: PagedList<Movie>) {
//    adapter.addMovies(movieList)
    adapter.submitList(movieList)
    progress_bar.visibility = View.GONE
  }

  private fun subscribe() {
//    RxRecyclerView
//        .scrollEvents(recycler_view)
//        .subscribe {
//          scrollListener.loadMore()
//        }.addTo(subscriptions)
  }

  private fun setRecyclerView() {


//    scrollListener = ScrollListener(layoutManager) { paginator.onNext(pageNumber++) }

    recycler_view.itemAnimator = DefaultItemAnimator()
    layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
      override fun getSpanSize(position: Int): Int {
        return if (adapter.getItemViewType(position) == AdapterConstants.LOADING) 3 else 1
      }
    }
    recycler_view.adapter = adapter

  }

  fun setMoviesData() {

    observe(viewModel.popMoviesLiveData){
      setData(it)
    }

//    paginator.onBackpressureDrop()
//        .subscribe { page -> viewModel.loadPopularMovies(page) }
//        .addTo(subscriptions)
  }
}
