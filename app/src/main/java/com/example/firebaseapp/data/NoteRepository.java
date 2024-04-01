package com.example.firebaseapp.data;

import com.example.firebaseapp.domain.model.Error;
import com.example.firebaseapp.domain.model.Note;
import com.example.firebaseapp.domain.model.User;
import com.example.firebaseapp.domain.util.ErrorListener;
import com.example.firebaseapp.domain.util.SuccessListener;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class NoteRepository {

    private static final String COLLECTION_NOTES = "notes";

    public static void getNotesByIds(List<String> ids, SuccessListener<List<Note>> successListener, ErrorListener errorListener) {
        FirebaseFirestore.getInstance()
                .collection(COLLECTION_NOTES)
                .whereIn(FieldPath.documentId(), ids)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    try {
                        List<Note> note = documentSnapshot.toObjects(Note.class);
                        successListener.onSuccess(note);
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

    private static Error getError(Exception exception) {
        if (exception instanceof FirebaseNetworkException) {
            return Error.NETWORK;
        } else {
            return Error.OTHER;
        }
    }
}
