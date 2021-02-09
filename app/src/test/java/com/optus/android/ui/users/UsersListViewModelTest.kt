package com.optus.android.ui.users

import com.optus.android.R
import com.optus.android.network.data.User
import com.optus.android.test.commons.BaseViewModelTest
import com.optus.android.test.commons.getOrAwaitValue
import com.optus.android.ui.common.ErrorEmptyState
import com.optus.android.ui.common.ListFetchedState
import com.optus.android.ui.common.ListViewState
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class UsersListViewModelTest : BaseViewModelTest() {
    @Mock
    lateinit var usersListRepository: UsersListRepository

    @Mock
    lateinit var user: User
    lateinit var usersListViewModel: UsersListViewModel

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        usersListViewModel = UsersListViewModel(usersListRepository)
    }

    @DisplayName(
        """
        GIVEN Error Fetching the List,
        WHEN fetchUsers() is called,
        THEN viewState must be ErrorEmptyState with correct message
    """
    )
    @Test
    fun testingForException() {
        runBlockingTest {
            `when`(usersListRepository.getUsers()).thenAnswer { throw Exception() }
        }
        usersListViewModel.fetchUsers()

        val viewState: ListViewState = usersListViewModel.viewState.getOrAwaitValue()
        assertTrue(viewState is ErrorEmptyState)
        assertEquals(
            R.string.error_fetching_list,
            (viewState as ErrorEmptyState).stringResId
        )
    }

    @DisplayName(
        """
        GIVEN Empty Users List,
        WHEN fetchUsers() is called,
        THEN viewState must be ErrorEmptyState with correct message
    """
    )
    @Test
    fun testingForEmptyUsersList() {
        runBlockingTest {
            `when`(usersListRepository.getUsers()).thenAnswer { emptyList<User>() }
        }
        usersListViewModel.fetchUsers()

        val viewState: ListViewState = usersListViewModel.viewState.getOrAwaitValue()
        assertTrue(viewState is ErrorEmptyState)
        assertEquals(
            R.string.empty_list,
            (viewState as ErrorEmptyState).stringResId
        )
    }

    @DisplayName(
        """
        GIVEN  Users List,
        WHEN fetchUsers() is called,
        THEN viewState must be ListFetchedState
    """
    )
    @Test
    fun testingForViewStateForValidUsersList() {
        runBlockingTest {
            `when`(usersListRepository.getUsers()).thenAnswer { listOf(user) }
        }
        usersListViewModel.fetchUsers()

        val viewState: ListViewState = usersListViewModel.viewState.getOrAwaitValue()
        assertTrue(viewState is ListFetchedState)
    }

    @DisplayName(
        """
        GIVEN  Users List,
        WHEN fetchUsers() is called,
        THEN valid Users List must be sent to UI
    """
    )
    @Test
    fun testingForValidUsersList() {
        val users = listOf(user)
        runBlockingTest {
            `when`(usersListRepository.getUsers()).thenAnswer { users }
        }
        usersListViewModel.fetchUsers()

        val usersList = usersListViewModel.userLists.getOrAwaitValue()
        assertEquals(users, usersList)
    }
}