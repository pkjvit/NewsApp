package com.pkj.learn.newsbyjus.newsdetail

import androidx.lifecycle.ViewModel
import com.pkj.learn.newsbyjus.data.NewsRepository
import javax.inject.Inject

/**
 * @author Pankaj Jangid
 */
class NewsDetailViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
}