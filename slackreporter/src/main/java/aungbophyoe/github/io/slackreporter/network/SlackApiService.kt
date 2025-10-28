package aungbophyoe.github.io.slackreporter.network

import aungbophyoe.github.io.slackreporter.model.SlackRequestBody
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * Created by aungb on 10/28/2025.
 */


interface SlackApiService {
    @POST
    suspend fun postMessage(@Url fullUrl: String, @Body body: SlackRequestBody): Response<ResponseBody>

    companion object {
        fun create(webhookBaseUrl: String): SlackApiService {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://hooks.slack.com/services/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(SlackApiService::class.java)
        }
    }
}