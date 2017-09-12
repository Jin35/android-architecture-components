package com.android.example.github.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.android.example.github.api.ApiResponse;
import com.android.example.github.vo.Resource;

/**
 * Like {@link NetworkBoundResource} but does not save the data to db
 */

public abstract class NetworkResource<ResultType> {

    private final MutableLiveData<Void> retryTrigger = new MutableLiveData<>();

    private final MediatorLiveData<Resource<ResultType>> internal = new MediatorLiveData<>();

    private final LiveData<Resource<ResultType>> result;

    @MainThread
    public NetworkResource() {
        result = Transformations.switchMap(retryTrigger, value -> {
            LiveData<ApiResponse<ResultType>> apiCall = createCall();
            internal.setValue(Resource.loading(null));
            internal.addSource(apiCall, response -> {
                internal.removeSource(apiCall);
                if (response.isSuccessful()) {
                    internal.setValue(Resource.success(response.body));
                } else {
                    internal.setValue(Resource.error(response.errorMessage, response.body));
                }
            });
            return internal;
        });
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }

    @MainThread
    public void retry() {
        retryTrigger.setValue(null);
    }


    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<ResultType>> createCall();
}
