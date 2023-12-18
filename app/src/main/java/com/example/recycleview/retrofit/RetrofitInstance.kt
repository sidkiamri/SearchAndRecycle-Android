package com.example.recycleview.retrofit

import android.annotation.SuppressLint
import com.bumptech.glide.Glide.init
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitInstance {
        var mHttpLoggingInterceptor = HttpLoggingInterceptor()
           // .setLevel(HttpLoggingInterceptor.Level.BODY)

    val client: Retrofit?
        @SuppressLint("SuspiciousIndentation")
        get()
    {
     val builder = Retrofit.Builder()
        .baseUrl("https://nodeserver-38hw.vercel.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().authenticator(object : Authenticator {
                @Throws(IOException::class)
                override fun authenticate(route: Route?, response: Response): Request? {
                    if (response.request.header("Authorization") != null) {
                        return null // Give up, we've already attempted to authenticate.
                    }

                    println("Authenticating for response: $response")
                    println("Challenges: ${response.challenges()}")
                    val credential = Credentials.basic("jesse", "password1")
                    return response.request.newBuilder()
                        .header("Authorization", credential)
                        .build()
                }
            }).addInterceptor(mHttpLoggingInterceptor)
            .build())
   //     var mRetrofit: Retrofit? = null
     val retrofit = builder.build()

            return retrofit

        }
    init {
        mHttpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

      /*  val client: Retrofit?
            get() {
                if(mRetrofit == null){
                    mRetrofit = Retrofit.Builder()
                        .baseUrl("https://server-node-tahamejdoub22.vercel.app/")
                        .client(mOkHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return mRetrofit
            }*/
      fun <S> createService(
          serviceClass: Class<S>
      ): S {
          return client!!.create(serviceClass)
      }
}


