package com.example.gymkana;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CompletedChallengeViewHolder extends RecyclerView.ViewHolder{

    private ImageView challengeIcon;
    private TextView challengeTitle;
    private TextView challengeDesc;
    private View itemView;

    public CompletedChallengeViewHolder(@NonNull View itemView) {
        super(itemView);
        challengeIcon = itemView.findViewById(R.id.icon_challenge);
        challengeTitle = itemView.findViewById(R.id.title_text);
        challengeDesc = itemView.findViewById(R.id.desc_text);
        this.itemView = itemView;

    }

    public ImageView getChallengeIcon() {
        return challengeIcon;
    }

    public TextView getChallengeTitle() {
        return challengeTitle;
    }

    public TextView getChallengeDesc() {
        return challengeDesc;
    }

    public View getItemView() {
        return itemView;
    }
}
