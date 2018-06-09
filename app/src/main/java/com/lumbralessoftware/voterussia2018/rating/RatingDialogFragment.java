package com.lumbralessoftware.voterussia2018.rating;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lumbralessoftware.voterussia2018.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lumbralessoftware.voterussia2018.Constants.ID_PLAYER;
import static com.lumbralessoftware.voterussia2018.Constants.NAME;

/**
 * Created by javiergonzalezcabezas on 9/6/18.
 */

public class RatingDialogFragment extends DialogFragment {

    @BindView(R.id.rating_dialog_ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.rating_dialog_textView)
    TextView textView;
    @BindView(R.id.rating_dialog_name_textView)
    TextView nameTextView;

    String namePlayer;

    public RatingDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static RatingDialogFragment newInstance(int id, String name) {
        RatingDialogFragment frag = new RatingDialogFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putInt(ID_PLAYER, id);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rating_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        ButterKnife.bind(this, view);

        setTitle();
    }

    private void setTitle() {

        namePlayer = getArguments().getString(NAME, "Enter Name");
        nameTextView.setText(namePlayer);
    }

    private void setMessageRating() {

        textView.setText(getString(R.string.rating_dialog_rating) + ratingBar.getRating());
    }

    @OnClick(R.id.rating_dialog_submit_button)
    public void sumitRating() {
        setMessageRating();
    }
}
