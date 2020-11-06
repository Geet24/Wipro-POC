package com.example.myapplication.domain.entities

/*When adding new constants do maintain the increasing sequence of error codes*/
object ErrorCodes {

    const val GENERIC_ERROR = "S001"

    //connectivity error
    const val OFFLINE = "S002"

    //error when data not found in cache
    const val DATA_UNAVAILABLE = "S006"


    //http errors
    const val HTTP_BAD_REQUEST = "400"
    const val HTTP_UNAUTHORISED = "401"
    const val HTTP_NOT_FOUND = "404"
    const val HTTP_REQUEST_TIMEOUT = "408"

    const val HTTP_SERVER_INTERNAL_ERROR = "500"
    const val HTTP_BAD_GATEWAY = "502"
    const val HTTP_SERVICE_UNAVAILABLE = "503"
    const val HTTP_GATEWAY_TIMEOUT = "504"
}