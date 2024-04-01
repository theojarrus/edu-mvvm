package com.example.firebaseapp.feature.main.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.firebaseapp.databinding.ActivityMainBinding;
import com.example.firebaseapp.feature.main.presentation.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater(), null, false);
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Get user
        // User user = viewModel.user.getValue();

        // Subscribe to User change
        viewModel.user.observe(this, user -> {
            binding.content.setText(user.toString());
        });

        viewModel.notes.observe(this, notes -> {
            binding.content.setText(notes.toString());
        });

        viewModel.state.observe(this, state -> {
            switch (state) {
                case INITIAL:
                    binding.progress.setVisibility(View.GONE);
                    binding.error.setVisibility(View.GONE);
                    binding.loadUserButton.setVisibility(View.VISIBLE);
                    binding.loadNotesButton.setVisibility(View.VISIBLE);
                    binding.content.setVisibility(View.VISIBLE);
                    binding.content.setText("Initial state");
                    break;
                case LOADING:
                    binding.progress.setVisibility(View.VISIBLE);
                    binding.error.setVisibility(View.GONE);
                    binding.loadUserButton.setVisibility(View.GONE);
                    binding.loadNotesButton.setVisibility(View.GONE);
                    binding.content.setVisibility(View.GONE);
                    break;
                case ERROR:
                    binding.progress.setVisibility(View.GONE);
                    binding.error.setVisibility(View.VISIBLE);
                    binding.loadUserButton.setVisibility(View.VISIBLE);
                    binding.loadNotesButton.setVisibility(View.VISIBLE);
                    binding.content.setVisibility(View.GONE);
                    break;
                case SUCCESS:
                    binding.progress.setVisibility(View.GONE);
                    binding.error.setVisibility(View.GONE);
                    binding.loadUserButton.setVisibility(View.VISIBLE);
                    binding.loadNotesButton.setVisibility(View.VISIBLE);
                    binding.content.setVisibility(View.VISIBLE);
                    break;
            }
        });

        binding.loadUserButton.setOnClickListener(v -> viewModel.loadUserById("vcr5Aw9onrDwf4bn2hsH"));
        binding.loadNotesButton.setOnClickListener(v -> viewModel.loadUserNotes("vcr5Aw9onrDwf4bn2hsH"));

        // Load data when activity opened
        // if (savedInstanceState == null) viewModel.loadUserById("A9mkkauBZbZ3e92mys7y");
    }
}
