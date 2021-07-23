package com.example.gymkana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Fragmento correspondiente a la lista de retos completados
 * */
public class CompletedChallenge extends Fragment {

    private CompletedChallengeAdapter adapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.completed_challenges_layout, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.challenge_list);
        adapter = new CompletedChallengeAdapter((MainActivity) requireActivity());

        assert container != null;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView info = view.findViewById(R.id.textView);
        if(adapter.getItemCount() <= 0){
            info.setVisibility(View.VISIBLE);
        }
        else info.setVisibility(View.INVISIBLE);
    }
}
