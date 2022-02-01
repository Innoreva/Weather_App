package com.ersubhadip.instantweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import retrofit2.HttpException
import java.io.IOException

const val TAG="#### MainActivity"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //todo -> Call the API and check response
        lifecycleScope.launchWhenCreated {
            val response = try{
                RetrofitInstance.api.getCurrent("1ab25b6036044fc0bf5122216220102","kanpur","no")
            }catch (e: IOException){
                Log.e(TAG,"IO Exception occurred : "+e.printStackTrace())
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(TAG,"Http Exception occurred : "+e.printStackTrace())
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body()!=null){
                Toast.makeText(this@MainActivity,"Badhai ho 😁",Toast.LENGTH_SHORT).show()
                Log.d(TAG,response.toString())
            }
            else
                Log.d(TAG,response.toString())
            Log.d(TAG,response.body().toString())
        }
    }
}