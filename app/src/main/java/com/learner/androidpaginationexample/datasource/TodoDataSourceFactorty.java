package com.learner.androidpaginationexample.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.learner.androidpaginationexample.model.TodoResponse;

public class TodoDataSourceFactorty extends DataSource.Factory {
    private MutableLiveData<PageKeyedDataSource<Integer, TodoResponse.Todo>> mutableLiveData = new MutableLiveData<>();

    @Override
    public DataSource create() {
        TodoDataSource todoDataSource = new TodoDataSource();
        mutableLiveData.postValue(todoDataSource);
        return todoDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, TodoResponse.Todo>> getMutableLiveData() {
        return mutableLiveData;
    }
}
