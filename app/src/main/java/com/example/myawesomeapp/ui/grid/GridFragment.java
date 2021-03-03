package com.example.myawesomeapp.ui.grid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myawesomeapp.R;

public class GridFragment extends Fragment {

    private GridViewModel gridViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gridViewModel =
                ViewModelProviders.of(this).get(GridViewModel.class);
        View root = inflater.inflate(R.layout.fragment_grid, container, false);
        final TextView textView = root.findViewById(R.id.text_grid);
        gridViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
