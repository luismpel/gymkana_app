package com.example.gymkana;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CurrentChallenge extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.current_challenge_layout, container, false);
        Button tryButton = view.findViewById(R.id.button);

        tryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(getActivity(), SecretCodeActivity.class);
                intent.putExtra("secretCode", MainActivity.getCurrentChallenge().getCode());
                requireActivity().startActivityForResult(intent, 101);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView title = view.findViewById(R.id.title_text);
        TextView desc = view.findViewById(R.id.desc_text);
        Challenge c = MainActivity.getCurrentChallenge();
        title.setText(c.getTitle());
        desc.setText(c.getDescription());
        title.setCompoundDrawablesRelativeWithIntrinsicBounds(c.getIcon(), 0,0, 0);
    }
}
