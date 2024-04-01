package com.example.firebaseapp.domain.business;

import com.example.firebaseapp.data.NoteRepository;
import com.example.firebaseapp.data.UserRepository;
import com.example.firebaseapp.domain.model.Note;
import com.example.firebaseapp.domain.model.User;
import com.example.firebaseapp.domain.util.ErrorListener;
import com.example.firebaseapp.domain.util.SuccessListener;

import java.util.List;

public class GetUserNotes {

    public static void getUserNotes(String uid, SuccessListener<List<Note>> successListener, ErrorListener errorListener) {
        UserRepository.getUserById(uid, user -> {
            NoteRepository.getNotesByIds(
                    user.notesIds,
                    successListener,
                    errorListener
            );
        }, errorListener);
    }
}
