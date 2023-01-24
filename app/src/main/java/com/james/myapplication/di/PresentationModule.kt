package com.james.myapplication.di

import com.james.myapplication.presentation.ui.first.FirstScreenViewModel
import com.james.myapplication.presentation.ui.second.SecondScreenViewModel
import com.james.myapplication.presentation.ui.third.ThirdScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FirstScreenViewModel() }
    viewModel { SecondScreenViewModel() }
    viewModel { ThirdScreenViewModel(get()) }
}

val adapterModule = module {
    factory { com.james.myapplication.adapter.UserPagingAdapter() }
}