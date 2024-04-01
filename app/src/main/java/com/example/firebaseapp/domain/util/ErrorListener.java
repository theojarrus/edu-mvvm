package com.example.firebaseapp.domain.util;

import com.example.firebaseapp.domain.model.Error;

public interface ErrorListener {

    void onError(Error error);
}
