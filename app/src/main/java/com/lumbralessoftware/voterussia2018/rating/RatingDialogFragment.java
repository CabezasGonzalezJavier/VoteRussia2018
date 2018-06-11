package com.lumbralessoftware.voterussia2018.rating;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lumbralessoftware.voterussia2018.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lumbralessoftware.voterussia2018.Constants.ID_PLAYER;
import static com.lumbralessoftware.voterussia2018.Constants.IMAGE;
import static com.lumbralessoftware.voterussia2018.Constants.NAME;

/**
 * Created by javiergonzalezcabezas on 9/6/18.
 */

public class RatingDialogFragment extends DialogFragment implements RatingContract.View {
    @BindView(R.id.rating_dialog_imageView)
    ImageView imageView;
    @BindView(R.id.rating_dialog_ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.rating_dialog_name_textView)
    TextView nameTextView;
    @BindView(R.id.rating_dialog_rating_textView)
    TextView ratingTextView;

    View view;
    String namePlayer, imagePlayer;
    int idPlayer;
    RatingContract.Presenter presenter;

    public RatingDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static RatingDialogFragment newInstance(int id, String name, String image) {
        RatingDialogFragment frag = new RatingDialogFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putInt(ID_PLAYER, id);
        args.putString(IMAGE, image);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rating_dialog, container);
        idPlayer = getArguments().getInt(ID_PLAYER);
        imagePlayer = getArguments().getString(IMAGE);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        setTitle();
        presenter.getVotes();
    }

    @Override
    public void setTitle() {
        Glide.with(getContext())
                .load(imagePlayer)
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);
        namePlayer = getArguments().getString(NAME, "Enter Name");
        nameTextView.setText(namePlayer);
    }

    @Override
    public void setMessageRating() {
        presenter.addValueVote(Math.round(ratingBar.getRating()));
    }

    @OnClick(R.id.rating_dialog_submit_button)
    public void sumitRating() {

        if (ratingBar.getRating() > 0) {
            setMessageRating();
        } else {
            ratingTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorForward));
            ratingTextView.setText(getString(R.string.rating_dialog_player_please));
        }
    }

    @Override
    public void showError() {
        Snackbar.make(view, getString(R.string.error), Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void setPresenter(RatingContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void dismissDialog() {
        dismiss();
    }
}
