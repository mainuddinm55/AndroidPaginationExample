package com.learner.androidpaginationexample.adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learner.androidpaginationexample.R;
import com.learner.androidpaginationexample.model.TodoResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TodoAdapter extends PagedListAdapter<TodoResponse.Todo, TodoAdapter.TodoHolder> {

    public TodoAdapter() {
        super(DIFF_UTIL);
    }

    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext().getApplicationContext())
                .inflate(R.layout.todo_row_item, viewGroup, false);
        return new TodoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoHolder todoHolder, int i) {
        TodoResponse.Todo todo = getItem(i);
        if (todo != null) {
            todoHolder.bindTo(todo);
        }
    }

    private static DiffUtil.ItemCallback<TodoResponse.Todo> DIFF_UTIL = new DiffUtil.ItemCallback<TodoResponse.Todo>() {
        @Override
        public boolean areItemsTheSame(@NonNull TodoResponse.Todo oldTodo, @NonNull TodoResponse.Todo newTodo) {
            return oldTodo.getTodoId() == newTodo.getTodoId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull TodoResponse.Todo oldTodo, @NonNull TodoResponse.Todo newTodo) {
            return oldTodo.getTodo().equals(newTodo.getTodo());
        }
    };


    public class TodoHolder extends RecyclerView.ViewHolder {
        final TextView todoTextView;
        final TextView dateTextView;

        public TodoHolder(@NonNull View itemView) {
            super(itemView);
            todoTextView = itemView.findViewById(R.id.todo_text_view);
            dateTextView = itemView.findViewById(R.id.date_text_view);
        }

        void bindTo(TodoResponse.Todo todo) {
            todoTextView.setText(todo.getTodo());
            dateTextView.setText(formatDate(todo.getCreateDate()));
        }

        String formatDate(String dateStr) {
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            try {
                Date date = dateTimeFormat.parse(dateStr);
                SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd", Locale.US);
                return timeFormat.format(date);

            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }


}
