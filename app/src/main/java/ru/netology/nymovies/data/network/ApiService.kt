package ru.netology.nymovies.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.netology.nymovies.BuildConfig
import ru.netology.nymovies.data.datamodel.MoviesAll

private const val BASE_URL = BuildConfig.BASE_URL

fun okhttp(vararg interceptors: Interceptor): OkHttpClient = OkHttpClient.Builder()
    .apply {
        interceptors.forEach {
            this.addInterceptor(it)
        }
    }
    .build()

fun retrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface ApiService {
    @GET("search.json?query=godfather&api-key=HM2EdnOvAminpWBEBbcLp3wGaKPNrshz")
    suspend fun getAll(): Response<MoviesAll>

}