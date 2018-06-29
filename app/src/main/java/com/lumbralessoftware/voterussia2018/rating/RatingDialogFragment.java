package com.lumbralessoftware.voterussia2018.rating;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.lumbralessoftware.voterussia2018.CropTransformation;
import com.lumbralessoftware.voterussia2018.R;
import com.lumbralessoftware.voterussia2018.RoundedCornersTransformation;
import com.lumbralessoftware.voterussia2018.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lumbralessoftware.voterussia2018.Constants.DEFAULT_IMAGE;
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
        if (imagePlayer.equals("")) {
            Glide.with(getContext())
                    .load(DEFAULT_IMAGE)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView);
        } else {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.transform(new CropTransformation(Utils.INSTANCE.dip2px(getActivity(), 400), Utils.INSTANCE.dip2px(getActivity(), 200),
                    CropTransformation.CropType.TOP));
            Glide.with(getContext())
                    .load(imagePlayer)
                    .apply(requestOptions)
                    .into(imageView);
        }
        namePlayer = getArguments().getString(NAME, "Enter Name");
        nameTextView.setText(namePlayer);
    }

    @Override
    public void setMessageRating() {
        presenter.addValueVote(Math.round(ratingBar.getRating()));
    }

    @OnClick(R.id.rating_dialog_submit_button)
    public void sumitRating() {
        if (Utils.INSTANCE.isOnline(getActivity())) {
            if (ratingBar.getRating() > 0) {
                setMessageRating();
            } else {
                ratingTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorForward));
                ratingTextView.setText(getString(R.string.rating_dialog_player_please));
            }
        } else {
            noInternet();
        }
    }

    @Override
    public void showError() {
        ratingTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorForward));
        ratingTextView.setText(getString(R.string.error));
    }

    @Override
    public void setPresenter(RatingContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void dismissDialog() {
        dismiss();
    }

    @Override
    public void noInternet() {

        ratingTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorForward));
        ratingTextView.setText(getString(R.string.no_internet));
    }

    @Override
    public void successfulVote() {
        Toast.makeText(getActivity(), getString(R.string.rating_thank_you), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void votedMessage() {
        Toast.makeText(getActivity(), getString(R.string.rating_voted), Toast.LENGTH_SHORT).show();
    }
}
