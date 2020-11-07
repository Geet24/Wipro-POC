package com.example.myapplication.presentation.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.BaseViewModel
import com.example.myapplication.data.repository.FeedsRepository
import com.example.myapplication.domain.AppResult
import com.example.myapplication.domain.entities.FeedEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedListViewModel @Inject constructor(val feedsRepository: FeedsRepository):BaseViewModel(){
    private val _feedsLiveData = MutableLiveData<AppResult<List<FeedEntity>>>()
    val feedsLiveData: LiveData<AppResult<List<FeedEntity>>> get() = _feedsLiveData

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