package com.learner.androidpaginationexample.datasource;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;


import com.learner.androidpaginationexample.model.TodoResponse;
import com.learner.androidpaginationexample.api.Api;
import com.learner.androidpaginationexample.api.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoDataSource extends PageKeyedDataSource<Integer, TodoResponse.Todo> {
    private static final String TAG = "TodoDataSource";
    private static final int PAGE = 1;
    public static final int PAGE_SIZE = 10;
    private static Api api;

    public TodoDataSource() {
        api = ApiClient.getInstance().getApi();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, TodoResponse.Todo> callback) {
        api.getTodos(PAGE, PAGE_SIZE).enqueue(new Callback<TodoResponse>() {
            @Override
            public void onResponse(@NonNull Call<TodoResponse> call, @NonNull Response<TodoResponse> response) {
                if (response.body() != null) {
                    Log.e(TAG, "Initial load size: " + response.body().getTodos().size());
                    callback.onResult(response.body().getTodos(), null, PAGE + 1);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TodoResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "initial load: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, TodoResponse.Todo> callback) {
        api.getTodos(params.key, PAGE_SIZE).enqueue(new Callback<TodoResponse>() {
            @Override
            public void onResponse(@NonNull Call<TodoResponse> call, @NonNull Response<TodoResponse> response) {
                if (response.body() != null) {
                    Integer key = params.key > 1 ? params.key - 1 : null;
                    Log.e(TAG, "Load before key " + key);
                    Log.e(TAG, "Load before size: " + response.body().getTodos().size());
                    callback.onResult(response.body().getTodos(), key);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TodoResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "load Before: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, TodoResponse.Todo> callback) {
        api.getTodos(params.key, PAGE_SIZE).enqueue(new Callback<TodoResponse>() {
            @Override
            public void onResponse(@NonNull Call<TodoResponse> call, @NonNull Response<TodoResponse> response) {
                if (response.body() != null) {
                    Integer key = response.body().isMore() ? params.key + 1 : null;
                    Log.e(TAG, "Load after key " + key);
                    Log.e(TAG, "Load after size: " + response.body().getTodos().size());
                    callback.onResult(response.body().getTodos(), key);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TodoResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "load After: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
