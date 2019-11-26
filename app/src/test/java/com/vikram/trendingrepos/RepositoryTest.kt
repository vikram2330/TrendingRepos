package com.vikram.trendingrepos

import com.vikram.trendingrepos.data.repository.Repository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

class RepositoryTest {
    @Inject
    lateinit var repository: Repository

    @Before
    fun setup() {
        val testComponent = DaggerTestComponent.builder()
            .applicationModule(TestApplicationModule(getTestContext()))
            .networkModule(TestNetworkModule())
            .build()
        testComponent.inject(this)
    }

    private fun getTestContext(): TrendingReposApp {
        val application: TrendingReposApp = Mockito.mock(TrendingReposApp::class.java)
        Mockito.`when`(application.applicationContext).thenReturn(application)
        return application
    }

    @Test
    fun testRepository() {
        repository.getTrendingRepositories().test().assertComplete().dispose()
    }
}