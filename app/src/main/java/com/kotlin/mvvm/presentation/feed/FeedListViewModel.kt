package com.kotlin.mvvm.presentation.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.mvvm.common.BaseViewModel
import com.kotlin.mvvm.data.repository.FeedsRepository
import com.kotlin.mvvm.data.sources.api.model.response.FeedResponse
import com.kotlin.mvvm.domain.AppResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedListViewModel @Inject constructor(val feedsRepository: FeedsRepository):BaseViewModel(){
    private val _feedsLiveData = MutableLiveData<AppResult<FeedResponse>>()
    val feedsLiveData: LiveData<AppResult<FeedResponse>> get() = _feedsLiveData

    init {
       getFeeds()
    }
     fun getFeeds() {
         _feedsLiveData.postValue(AppResult.Loading(null))
          viewModelScope.launch {
            _feedsLiveData.postValue(feedsRepository.getFeeds())
        }
    }
}