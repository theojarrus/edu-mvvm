package com.example.firebaseapp.domain.model;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.DocumentId;

public class Note {

    @DocumentId
    public String id;
    public String text;

    @NonNull
    @Override
    public String toString() {
        return "Note = {id=" + id + ", text=" + text + "}";
    }
}
