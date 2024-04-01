package com.example.firebaseapp.feature.main.presentation;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.firebaseapp.data.UserRepository;
import com.example.firebaseapp.domain.business.GetUser;
import com.example.firebaseapp.domain.business.GetUserNotes;
import com.example.firebaseapp.domain.model.Note;
import com.example.firebaseapp.domain.model.User;
import com.example.firebaseapp.feature.main.presentation.model.MainState;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<User> _user = new MutableLiveData<>();
    public LiveData<User> user = _user;

    private MutableLiveData<List<Note>> _notes = new MutableLiveData<>();
    public LiveData<List<Note>> notes = _notes;

    private MutableLiveData<MainState> _state = new MutableLiveData<>(MainState.INITIAL);
    public LiveData<MainState> state = _state;

    public void loadUserById(String uid) {
        _state.setValue(MainState.LOADING);
        GetUser.getUserById(uid, resultUser -> {
            // Use setValue if thread is main otherwise use postValue
            _state.postValue(MainState.SUCCESS);
            _user.postValue(resultUser);
        }, error -> {
            _state.postValue(MainState.ERROR);
        });
    }

    public void loadUserNotes(String uid) {
        _state.setValue(MainState.LOADING);
        GetUserNotes.getUserNotes(uid, resultNotes -> {
            _state.postValue(MainState.SUCCESS);
            _notes.postValue(resultNotes);
        }, error -> {
            _state.postValue(MainState.ERROR);
        });
    }
}
