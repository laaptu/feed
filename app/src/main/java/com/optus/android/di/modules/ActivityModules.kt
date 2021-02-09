package com.optus.android.di.modules

import com.optus.android.ui.detail.PhotoDetailActivity
import com.optus.android.ui.photos.UserPhotosActivity
import com.optus.android.ui.users.UsersListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModules {
    @ContributesAndroidInjector
    abstract fun bindsUsersListActivity(): UsersListActivity

    @ContributesAndroidInjector
    abstract fun bindsUserPhotosActivity(): UserPhotosActivity

    @ContributesAndroidInjector
    abstract fun bindsPhotoDetailActivity(): PhotoDetailActivity
}