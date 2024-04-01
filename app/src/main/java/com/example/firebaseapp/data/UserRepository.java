package com.example.firebaseapp.data;

import com.example.firebaseapp.domain.util.ErrorListener;
import com.example.firebaseapp.domain.util.SuccessListener;
import com.example.firebaseapp.domain.model.Error;
import com.example.firebaseapp.domain.model.User;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserRepository {

    private static final String COLLECTION_USERS = "users";

    public static void getUserById(String uid, SuccessListener<User> successListener, ErrorListener errorListener) {
        getUserDocument(uid).get()
                .addOnSuccessListener(documentSnapshot -> {
                    try {
                        User user = documentSnapshot.toObject(User.class);
                        successListener.onSuccess(user);
                    } catch (ClassCastException exception) {
                        errorListener.onError(Error.CAST);
                    } catch (AssertionError exception) {
                        errorListener.onError(Error.NOT_FOUND);
                    }
                })
                .addOnFailureListener(exception -> {
                    errorListener.onError(getError(exception));
                });
    }

    private static DocumentReference getUserDocument(String id) {
        return FirebaseFirestore.getInstance()
                .collection(COLLECTION_USERS)
                .document(id);
    }

    private static Error getError(Exception exception) {
        if (exception instanceof FirebaseNetworkException) {
            return Error.NETWORK;
        } else {
            return Error.OTHER;
        }
    }
}
