package com.example.presenter;

public interface ComposePresenter<I, O> {

  O success(I res);
}
