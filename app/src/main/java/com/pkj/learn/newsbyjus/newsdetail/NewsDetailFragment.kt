package com.pkj.learn.newsbyjus.newsdetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.google.android.material.snackbar.Snackbar
import com.pkj.learn.newsbyjus.NewsApplication
import com.pkj.learn.newsbyjus.R
import com.pkj.learn.newsbyjus.databinding.NewsDetailsFragmentBinding
import com.pkj.learn.newsbyjus.util.setupSnackbar
import javax.inject.Inject

/**
 * @author Pankaj Jangid
 */
class NewsDetailFragment : Fragment(){

    @Inject
    lateinit var viewModel: NewsDetailViewModel

    private val args: NewsDetailFragmentArgs by navArgs()

    private lateinit var viewDataBinding: NewsDetailsFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as NewsApplication).appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = NewsDetailsFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        viewModel.start(args.articleId)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.setupSnackbar(this, viewModel.snackbarText, Snackbar.LENGTH_SHORT)

        viewModel.article.observe(viewLifecycleOwner, Observer {
                it?.let { viewDataBinding.imageView.load(it.urlToImage){
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
                }
            }
        })

        viewModel.back.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })
    }



}