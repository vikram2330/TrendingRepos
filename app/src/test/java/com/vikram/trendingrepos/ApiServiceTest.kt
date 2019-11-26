package com.vikram.trendingrepos

import com.vikram.trendingrepos.data.services.ApiService
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class ApiServiceTest {
    private lateinit var apiService: ApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(ApiService::class.java)
    }

    @Test
    fun getRepositoryData() {
        mockWebServer.enqueue(MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(getJson("trending_repositories_response_success.json")))
        val response = apiService.getTrendingRepositories().blockingGet()
        Assert.assertThat(response[0].author, CoreMatchers.`is`("google"))
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    private fun getJson(path: String): String {
        val uri = this::class.java.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())

    }
}