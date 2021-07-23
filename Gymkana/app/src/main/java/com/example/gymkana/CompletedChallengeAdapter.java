package com.example.gymkana;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class CompletedChallengeAdapter extends RecyclerView.Adapter<CompletedChallengeViewHolder>{

    private ArrayList<Challenge> challenges;
    private MainActivity activity;

    public CompletedChallengeAdapter(MainActivity activity){
        this.challenges = new ArrayList<>();
        challenges.addAll(MainActivity.getCompletedChallenges());
        this.activity = activity;
    }

    @NonNull
    @Override
    public CompletedChallengeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.completed_challenge_item, parent, false);

        return new CompletedChallengeViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedChallengeViewHolder holder, final int position) {
        ArrayList<Challenge> completedChallenges = MainActivity.getCompletedChallenges();
        final Challenge item = completedChallenges.get(position);
        holder.getChallengeTitle().setText(item.getTitle());
        holder.getChallengeDesc().setText(item.getDescription());
        holder.getChallengeIcon().setImageResource(item.getIcon());
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                ChallengeDialog dialog = ChallengeDialog.newInstance(item);
                dialog.show(ft, null);

            }
        });
    }

    @Override
    public int getItemCount() {
        return challenges.size();
    }
}
