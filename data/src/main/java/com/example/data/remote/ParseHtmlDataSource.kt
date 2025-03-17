package com.example.data.remote

import okhttp3.Response

interface ParseHtmlDataSource{
    suspend fun getParsedHtmlResponse(): Response
}