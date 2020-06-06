package com.pkj.learn.newsbyjus.newsdetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pkj.learn.newsbyjus.NewsApplication
import com.pkj.learn.newsbyjus.R
import javax.inject.Inject

/**
 * @author Pankaj Jangid
 */
class NewsDetailFragment : Fragment(){

    @Inject
    lateinit var viewModel: NewsDetailViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as NewsApplication).appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_details_fragment, container, false)
    }

}