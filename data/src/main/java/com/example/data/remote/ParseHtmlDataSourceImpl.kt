package com.example.data.remote

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Named

class ParseHtmlDataSourceImpl @Inject constructor(
    private val httpClient: OkHttpClient,
    @Named("baseUrl") val baseUrl: String
) :
    ParseHtmlDataSource {

    override suspend fun getParsedHtmlResponse(): Response {
        return httpClient.newCall(Request.Builder().url(baseUrl).build()).execute()
    }
}

