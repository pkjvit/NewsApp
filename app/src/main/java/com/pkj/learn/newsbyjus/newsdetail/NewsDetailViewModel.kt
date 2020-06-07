package com.pkj.learn.newsbyjus.newsdetail

import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.pkj.learn.newsbyjus.R
import com.pkj.learn.newsbyjus.data.Article
import com.pkj.learn.newsbyjus.data.NewsRepository
import com.pkj.learn.newsbyjus.data.Result
import com.pkj.learn.newsbyjus.util.Event
import com.pkj.learn.newsbyjus.util.Format
import javax.inject.Inject

/**
 * @author Pankaj Jangid
 */
class NewsDetailViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val _articleId = MutableLiveData<Int>()

    private val _article = _articleId.switchMap { articleId ->
        repository.observeArticle(articleId).map {
            computeResult(it)
        }
    }

    val article: LiveData<Article?> = _article

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    private val _back = MutableLiveData<Event<Boolean>>()
    val back: LiveData<Event<Boolean>> = _back

    fun start(articleId: Int?) {
        // If we're already loading or already loaded, return (might be a config change)
        if (articleId == _articleId.value) {
            return
        }
        // Trigger the load
        _articleId.value = articleId
    }

    fun back(){
        _back.value = Event(true)
    }

    private fun computeResult(taskResult: Result<Article>): Article? {
        return if (taskResult is Result.Success) {
            taskResult.data
        } else {
            showSnackbarMessage(R.string.loading_article_error)
            null
        }
    }

    private fun showSnackbarMessage(@StringRes message: Int) {
        _snackbarText.value = Event(message)
    }

    // todo redundant : companion function not working with databinding in xml : need to find out solution
    fun dateFormat(dateTime: String?): String{
        return Format.dateFormatFromDateTime(dateTime)
    }
}