package com.pkj.learn.newsbyjus.news

import androidx.lifecycle.ViewModel
import com.pkj.learn.newsbyjus.data.NewsRepository
import javax.inject.Inject

/**
 * @author Pankaj Jangid
 */
class HeadlinesViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
}