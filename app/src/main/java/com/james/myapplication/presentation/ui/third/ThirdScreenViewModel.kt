package com.james.myapplication.presentation.ui.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.james.myapplication.data.paging.UserPagingSource
import com.james.myapplication.model.User
import com.james.myapplication.util.State
import com.james.myapplication.util.toUser
import kotlinx.coroutines.flow.*

class ThirdScreenViewModel(
    private val pagingSource: UserPagingSource
): ViewModel() {
    
    private val _state = MutableStateFlow(State.LOADING)
    val state = _state.asStateFlow()
    
    private val _user = MutableStateFlow<PagingData<User>?>(null)
    val user = _user.asStateFlow()
    
    suspend fun getListData() = run {
        _state.value = State.LOADING
        Pager(
            config = PagingConfig(pageSize = 6, maxSize = 18),
            pagingSourceFactory = { pagingSource })
            .flow
            .map {it.map { it.toUser() } }
            .cachedIn(viewModelScope)
            .catch {
                _state.value = State.ERROR
            }
            .collect {
                _user.value = it
                _state.value = State.SUCCESS
            }
    }
}
