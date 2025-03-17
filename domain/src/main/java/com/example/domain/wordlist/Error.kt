package com.example.domain.wordlist

open class BaseException(
    var title: String? = null,
    var errorType: String? = null,
    var errorCode: String? = null
) : Exception()

open class BaseCustomException(override val message: String?) : BaseException()
class NoInternetException : BaseException()
