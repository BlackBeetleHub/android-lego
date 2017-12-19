package com.example.kirill.myapplication

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs


// TODO: helper class for easy work with api-server (abstract on router)
class Router {

}

/**
 * Helper for work with http
 *
 * Simple impl for easy work with http
 *
 * @param apiUrl is IP-address api-server
 */


val info = "This is factory"

fun getMoreInfo(): String {
    return "This is factory fun"
}


class NetworkManager(apiUrl: String) {

    companion object Factory {
        var BaseURL: String = "http://192.168.1.221:4000"

        /**
         * Send request to create new user
         * @return account_id
         */
        suspend fun testNetworkApi(email: String, username: String, password: String, method: (String) -> Unit) {
            BaseURL.httpGet().responseString { request, response, result ->
                //do something with response
                when (result) {
                    is Result.Failure -> {
                        method(response.statusCode.toString())
                    }
                    is Result.Success -> {
                        method(result.toString())
                    }
                }
            }
        }

        /**
         * Send request to create new user
         * @return account_id
         */
        suspend fun createAccount(email: String, password: String, method: (String) -> Unit) {
            Fuel.post(BaseURL + "/login", listOf("email" to email, "password" to password)).responseString { request, response, result ->
                //do something with response
                when (result) {
                    is Result.Failure -> {
                        method(response.statusCode.toString())
                    }
                    is Result.Success -> {
                        method(result.value)
                    }
                }
            }
        }

        /**
         * Send request to create new user
         * @return account_id
         */
        suspend fun getAllCustomKnownWords(user_id: String, method: (String) -> Unit) {
            Fuel.get(BaseURL + "/get_all_custom_words", listOf("id_user" to user_id)).responseString { request, response, result ->
                //do something with response
                when (result) {
                    is Result.Failure -> {
                        method(response.statusCode.toString())
                    }
                    is Result.Success -> {
                        method(result.value)
                    }
                }
            }
        }
    }
}

private operator fun Any.invoke(function: () -> Unit) {}
