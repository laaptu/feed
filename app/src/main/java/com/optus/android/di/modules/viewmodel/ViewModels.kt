package com.optus.android.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import com.optus.android.ui.photos.UserPhotosViewModel
import com.optus.android.ui.users.UsersListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModels {
    @Binds
    @IntoMap
    @ViewModelKey(UsersListViewModel::class)
    abstract fun bindUsersListViewModel(usersListViewModel: UsersListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserPhotosViewModel::class)
    abstract fun bindUserPhotosViewModel(userPhotosViewModel: UserPhotosViewModel): ViewModel
}