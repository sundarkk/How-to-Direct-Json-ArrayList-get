package com.sundar.assignola.interfaces;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by sundar Chaupal(+919709711150) on 08/09/21.
 */

public interface ApiInterface {


    @FormUrlEncoded
    @POST("repositories")
    Call<JsonObject> getHome(@Field("page_count") int page_count);


}
