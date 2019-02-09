package com.learner.androidpaginationexample.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.learner.androidpaginationexample.R;
import com.learner.androidpaginationexample.adapter.TodoAdapter;
import com.learner.androidpaginationexample.model.TodoResponse;
import com.learner.androidpaginationexample.model.TodoViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView todosRecyclerView = findViewById(R.id.todos_recycler_view);
        todosRecyclerView.setHasFixedSize(true);
        todosRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        TodoViewModel todoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);
        final TodoAdapter adapter = new TodoAdapter();

        todoViewModel.toddPagedList.observe(this, new Observer<PagedList<TodoResponse.Todo>>() {
            @Override
            public void onChanged(@Nullable PagedList<TodoResponse.Todo> todos) {
                adapter.submitList(todos);
            }
        });

        todosRecyclerView.setAdapter(adapter);
    }
}
