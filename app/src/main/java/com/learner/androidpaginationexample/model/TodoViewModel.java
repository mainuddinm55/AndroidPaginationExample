package com.learner.androidpaginationexample.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.learner.androidpaginationexample.datasource.TodoDataSource;
import com.learner.androidpaginationexample.datasource.TodoDataSourceFactorty;

public class TodoViewModel extends ViewModel {
    public LiveData<PagedList<TodoResponse.Todo>> toddPagedList;
    LiveData<PageKeyedDataSource<Integer, TodoResponse.Todo>> liveDataSource;

    public TodoViewModel() {
        TodoDataSourceFactorty todoDataSourceFactorty = new TodoDataSourceFactorty();
        liveDataSource = todoDataSourceFactorty.getMutableLiveData();
        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(TodoDataSource.PAGE_SIZE)
                        .build();
        toddPagedList = (new LivePagedListBuilder(todoDataSourceFactorty, config)).build();
    }
}
