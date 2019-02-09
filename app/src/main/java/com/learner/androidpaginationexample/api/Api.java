package com.learner.androidpaginationexample.api;

import com.learner.androidpaginationexample.model.TodoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("todopagination")
    Call<TodoResponse> getTodos(
            @Query("page") int page,
            @Query("pagesize") int pageSize
    );
}
