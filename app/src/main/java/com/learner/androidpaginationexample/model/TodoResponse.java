package com.learner.androidpaginationexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class TodoResponse {

    @SerializedName("more")
    @Expose
    private boolean more;
    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("todos")
    @Expose
    private List<Todo> todos = null;

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }


    public class Todo {

        @SerializedName("todo_id")
        @Expose
        private int todoId;
        @SerializedName("todo")
        @Expose
        private String todo;
        @SerializedName("create_date")
        @Expose
        private String createDate;

        public int getTodoId() {
            return todoId;
        }

        public void setTodoId(int todoId) {
            this.todoId = todoId;
        }

        public String getTodo() {
            return todo;
        }

        public void setTodo(String todo) {
            this.todo = todo;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

    }
}
