package com.example.recycleview.retrofit

import android.app.Application
import com.example.recycleview.retrofit.RetrofitInstance.client

class AppCreator : Application() {
    companion object {

        private var mApiHelper: ApiHelper? = null
        fun getApiHelperInstance(): ApiHelper {
            if(mApiHelper ==null){
                mApiHelper = ApiHelper(client!!.create(UserApi::class.java))
            }
            return mApiHelper!!
        }

    }
}