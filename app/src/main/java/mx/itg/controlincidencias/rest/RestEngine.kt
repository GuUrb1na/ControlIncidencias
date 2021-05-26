package mx.itg.controlincidencias.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestEngine {
    companion object{

        fun getRestEngine(): Retrofit {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.10/ITG/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }
    }
}