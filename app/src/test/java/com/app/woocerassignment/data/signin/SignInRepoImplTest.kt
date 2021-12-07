package com.app.woocerassignment.data.signin

import com.app.woocerassignment.fakes.FakeSignInLocalDS
import com.app.woocerassignment.fakes.FakeSignInRemoteDS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Test

class SignInRepoImplTest {


    @Test
    fun `valid input successful signIn`() = runBlockingTest {
        var sut = SignInRepoImpl(
            FakeSignInLocalDS(
                null, null, null
            ), FakeSignInRemoteDS(false), Dispatchers.Unconfined
        )

        assertEquals(sut.isLoggedIn(), false)
        var response = sut.signIn("amir", "amir@a.com", "a.com", "amir", "amir")
        var result = response.toList()
        assertEquals(result.first(), Result.success(Unit))
        assertEquals(sut.isLoggedIn(), true)


        sut = SignInRepoImpl(
            FakeSignInLocalDS(
                null, null, null
            ), FakeSignInRemoteDS(false), Dispatchers.Unconfined
        )

        assertEquals(sut.isLoggedIn(), false)
        response = sut.signIn("", "", "a.com", "amir", "amir")
        result = response.toList()
        assertEquals(result.first(), Result.success(Unit))
        assertEquals(sut.isLoggedIn(), true)
    }

    @Test
    fun `valid input fail signIn`() = runBlockingTest {
        var sut = SignInRepoImpl(
            FakeSignInLocalDS(
                null, null, null
            ), FakeSignInRemoteDS(true), Dispatchers.Unconfined
        )

        assertEquals(sut.isLoggedIn(), false)
        var response = sut.signIn("amir", "amir@a.com", "a.com", "amir", "amir")
        var result = response.toList()
        assertTrue(result.first().isFailure)
        assertEquals(sut.isLoggedIn(), false)


        sut = SignInRepoImpl(
            FakeSignInLocalDS(
                null, null, null
            ), FakeSignInRemoteDS(true), Dispatchers.Unconfined
        )

        assertEquals(sut.isLoggedIn(), false)
        response = sut.signIn("", "", "a.com", "amir", "amir")
        result = response.toList()
        assertTrue(result.first().isFailure)
        assertEquals(sut.isLoggedIn(), false)
    }

    @Test
    fun `invalid input fail signIn`() = runBlockingTest {
        var sut = SignInRepoImpl(
            FakeSignInLocalDS(
                null, null, null
            ), FakeSignInRemoteDS(false), Dispatchers.Unconfined
        )

        assertEquals(sut.isLoggedIn(), false)
        var response = sut.signIn("amir", "amir@a.com", "    ", "", "")
        var result = response.toList()

        assertTrue(result.first().isFailure)
        assertEquals(sut.isLoggedIn(), false)


        sut = SignInRepoImpl(
            FakeSignInLocalDS(
                null, null, null
            ), FakeSignInRemoteDS(true), Dispatchers.Unconfined
        )

        assertEquals(sut.isLoggedIn(), false)
        response = sut.signIn("", "", " ", "amir", "amir")
        result = response.toList()
        assertTrue(result.first().isFailure)
        assertEquals(sut.isLoggedIn(), false)
    }
}