package com.example.library;

public interface ILoadingView   {
    int STATUS_LOADING = 1;
    int STATUS_LOAD_SUCCESS = 2;
    int STATUS_LOAD_FAILED = 3;
    int STATUS_EMPTY_DATA = 4;

    void showLoading(String msg);

    void showLoadSuccess();

    void showLoadFailed(String msg);

    void showLoadEmpty(String msg);
}

