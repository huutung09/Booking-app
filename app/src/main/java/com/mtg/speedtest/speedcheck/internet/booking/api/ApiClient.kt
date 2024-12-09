package com.mtg.speedtest.speedcheck.internet.booking.api

import com.google.gson.GsonBuilder
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils.BASE_URL
import com.mtg.speedtest.speedcheck.internet.booking.CommonUtils.REQUEST_TIMEOUT_DURATION
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


object ApiClient {
    var token: String = ""
    val instance: ApiService = Retrofit.Builder().run {
        val gson = GsonBuilder()
            .enableComplexMapKeySerialization()
            .setPrettyPrinting()
            .create()
        baseUrl(BASE_URL)
        addConverterFactory(GsonConverterFactory.create(gson))
        client(createRequestInterceptorClient())
        build()
    }.create(ApiService::class.java)

//    val instanceGG: ApiService = Retrofit.Builder().run {
//        val gson = GsonBuilder()
//            .enableComplexMapKeySerialization()
//            .setPrettyPrinting()
//            .create()
//        baseUrl(BASE_URL_GG)
//        addConverterFactory(GsonConverterFactory.create(gson))
//        client(createRequestInterceptorClient())
//        build()
//    }.create(ApiService::class.java)

    fun getInstance2(url: String): ApiService {
        return Retrofit.Builder().run {
            val gson = GsonBuilder()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .create()
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create(gson))
            client(createRequestInterceptorClient())
            build()
        }.create(ApiService::class.java)
    }


    private fun createRequestInterceptorClient(): OkHttpClient {

        val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )

        // Install the all-trusting trust manager
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory

        val interceptor = Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            if (!"".equals(token)) {
                requestBuilder.addHeader("authorization", "Bearer $token")
            }
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                .hostnameVerifier(HostnameVerifier { hostname, session -> true })
                .connectTimeout(REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT_DURATION.toLong(), TimeUnit.SECONDS)
                .build()
    }

}
