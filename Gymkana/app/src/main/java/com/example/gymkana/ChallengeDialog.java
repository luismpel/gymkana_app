package com.example.gymkana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ChallengeDialog extends DialogFragment {

    public static ChallengeDialog newInstance(Challenge c) {
        ChallengeDialog dialog = new ChallengeDialog();

        Bundle args = new Bundle();
        args.putString("title", c.getTitle());
        args.putString("desc", c.getDescription());
        args.putInt("icon", c.getIcon());
        args.putString("code", c.getCode());
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.expanded_task_item, container, false);
        Bundle args = getArguments();
        TextView title = v.findViewById(R.id.title_text);
        TextView desc = v.findViewById(R.id.desc_text);
        TextView code = v.findViewById(R.id.code_text);
        ImageView icon = v.findViewById(R.id.challenge_icon);
        assert args != null;
        code.setText(args.getString("code"));
        title.setText(args.getString("title"));
        desc.setText(args.getString("desc"));
        icon.setImageResource(args.getInt("icon"));
        return v;
    }
}
