package com.optus.android.ui.photos

import com.optus.android.R
import com.optus.android.network.data.Photo
import com.optus.android.test.commons.BaseViewModelTest
import com.optus.android.test.commons.getOrAwaitValue
import com.optus.android.ui.common.ErrorEmptyState
import com.optus.android.ui.common.ListFetchedState
import com.optus.android.ui.common.ListViewState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserPhotosViewModelTest : BaseViewModelTest() {
    @Mock
    lateinit var userPhotosRepository: UserPhotosRepository

    @Mock
    lateinit var photo: Photo
    lateinit var userPhotosViewModel: UserPhotosViewModel
    val albumId = 0L

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        userPhotosViewModel = UserPhotosViewModel(userPhotosRepository)
    }

    @DisplayName(
        """
        GIVEN Error Fetching the List,
        WHEN fetchUserPhotos(albumId) is called,
        THEN viewState must be ErrorEmptyState with correct message
    """
    )
    @Test
    fun testingForException() {
        runBlockingTest {
            Mockito.`when`(userPhotosRepository.getUserPhotos(albumId))
                .thenAnswer { throw Exception() }
        }
        userPhotosViewModel.fetchUserPhotos(albumId)

        val viewState: ListViewState = userPhotosViewModel.viewState.getOrAwaitValue()
        assertTrue(viewState is ErrorEmptyState)
        assertEquals(
            R.string.error_fetching_list,
            (viewState as ErrorEmptyState).stringResId
        )
    }

    @DisplayName(
        """
        GIVEN Empty Users List,
        WHEN fetchUserPhotos(albumId) is called,
        THEN viewState must be ErrorEmptyState with correct message
    """
    )
    @Test
    fun testingForEmptyPhotosList() {
        runBlockingTest {
            Mockito.`when`(userPhotosRepository.getUserPhotos(albumId))
                .thenAnswer { emptyList<Photo>() }
        }
        userPhotosViewModel.fetchUserPhotos(albumId)

        val viewState: ListViewState = userPhotosViewModel.viewState.getOrAwaitValue()
        assertTrue(viewState is ErrorEmptyState)
        assertEquals(
            R.string.empty_list,
            (viewState as ErrorEmptyState).stringResId
        )
    }

    @DisplayName(
        """
        GIVEN  valid user photos List,
        WHEN fetchUserPhotos(albumId) is called,
        THEN viewState must be ListFetchedState
    """
    )
    @Test
    fun testingForViewStateForValidPhotosList() {
        runBlockingTest {
            Mockito.`when`(userPhotosRepository.getUserPhotos(albumId)).thenAnswer { listOf(photo) }
        }
        userPhotosViewModel.fetchUserPhotos(albumId)

        val viewState: ListViewState = userPhotosViewModel.viewState.getOrAwaitValue()
        assertTrue(viewState is ListFetchedState)
    }

    @DisplayName(
        """
        GIVEN  valid user photos list,
        WHEN fetchUserPhotos(albumId) is called,
        THEN valid Photos List must be sent to UI
    """
    )
    @Test
    fun testingForValidPhotosList() {
        val photos = listOf(photo)
        runBlockingTest {
            Mockito.`when`(userPhotosRepository.getUserPhotos(albumId)).thenAnswer { photos }
        }
        userPhotosViewModel.fetchUserPhotos(albumId)

        val photoList = userPhotosViewModel.photoLists.getOrAwaitValue()
        assertEquals(photos, photoList)
    }
}