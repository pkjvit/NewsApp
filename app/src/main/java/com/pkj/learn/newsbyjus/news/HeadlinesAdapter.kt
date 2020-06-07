package com.pkj.learn.newsbyjus.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.pkj.learn.newsbyjus.R
import com.pkj.learn.newsbyjus.data.Article
import com.pkj.learn.newsbyjus.databinding.HeadlineItemLayoutBinding
import com.pkj.learn.newsbyjus.util.loadUrl

/**
 * @author Pankaj Jangid
 */
class HeadlinesAdapter(private val viewModel: HeadlinesViewModel) :
    ListAdapter<Article, HeadlinesAdapter.ViewHolder>(ArticlesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }


    class ViewHolder private constructor(val binding: HeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: HeadlinesViewModel, item: Article) {

            binding.viewmodel = viewModel
            binding.article = item
            binding.executePendingBindings()
            binding.imageViewUrl.loadUrl(item.urlToImage)

        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HeadlineItemLayoutBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }


}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class ArticlesDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}