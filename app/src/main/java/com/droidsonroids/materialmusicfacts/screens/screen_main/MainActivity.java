package com.droidsonroids.materialmusicfacts.screens.screen_main;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.droidsonroids.materialmusicfacts.App;
import com.droidsonroids.materialmusicfacts.R;
import com.droidsonroids.materialmusicfacts.SuperActivity;
import com.droidsonroids.materialmusicfacts.screens.screen_album_details.AlbumDetailsActivity;
import com.droidsonroids.materialmusicfacts.views.SpacesItemDecoration;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends SuperActivity implements OnAlbumClickListener, AppBarLayout.OnOffsetChangedListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    //@BindView(R.id.textview_biography)
    //public TextView mTextViewBiography;
    @BindView(R.id.textview_name)
    public TextView mTextViewName;
    @BindView(R.id.textview_surname)
    public TextView mTextViewSurname;
    @BindView(R.id.textview_nick)
    public TextView mTextViewNick;
    @BindView(R.id.textview_place_birth)
    public TextView mTextViewPlaceBirth;
    @BindView(R.id.app_bar_layout)
    public AppBarLayout mAppBarLayout;
    @BindView(R.id.linearlayout_description)
    public LinearLayout mLinearLayout;
    @BindView(R.id.recyclerView)
    public RecyclerView mRecyclerView;

    public MainRecyclerViewAdapter mAdapter;
    public boolean shown;//popraw to !

    public static void startActivity(final Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        shown = mLinearLayout.isShown();
        //mTextViewBiography.setText(App.getMadonnaProvider().provideMadonnaBiography());
        setTextViewContent();
        mAppBarLayout.addOnOffsetChangedListener(this);
        initializeRecycleView();

    }

    private void initializeRecycleView() {
        mAdapter = new MainRecyclerViewAdapter(Picasso.with(this));
        mAdapter.setOnAlbumClickListener(this);

        mRecyclerView.addItemDecoration(new SpacesItemDecoration(4,5,false));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setAlbumCovers(App.getMadonnaProvider().provideAlbumCovers());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if ((percentage <= 0.5) && shown) {
            shown = false;
            animationShowIn();

        } else if ((percentage > 0.5) && !shown) {
            mLinearLayout.setVisibility(View.VISIBLE);
            shown = true;
            animationShowOut();
        }
    }

    public void animationShowIn() {
        AlphaAnimation anim = new AlphaAnimation(0, 1);
        anim.setDuration(300);
        mLinearLayout.startAnimation(anim);
        mLinearLayout.setVisibility(View.VISIBLE);
    }
    public void animationShowOut(){
        AlphaAnimation anim = new AlphaAnimation(1, 0);
        anim.setDuration(300);
        mLinearLayout.startAnimation(anim);
        mLinearLayout.setVisibility(View.INVISIBLE);
    }

    public void setTextViewContent() {
        mTextViewName.setText(App.getMadonnaProvider().provideMadonnaName());
        mTextViewSurname.setText(App.getMadonnaProvider().provideMadonnaSurname());
        mTextViewNick.setText(App.getMadonnaProvider().provideMadonnaNick());
        mTextViewPlaceBirth.setText(App.getMadonnaProvider().provideMadonnaPlaceBirth());
    }

    @Override
    public void onAlbumClickedListener(View imageViewAlbum, int position) {
        ActivityOptions optionsCompat = ActivityOptions.makeSceneTransitionAnimation(this, new Pair<View, String>(imageViewAlbum,"transitionAlbum"));
        Intent intent = new Intent(this,AlbumDetailsActivity.class);
        intent.putExtra(AlbumDetailsActivity.EXTRA_ALBUM_COVER,position);
        startActivity(intent, optionsCompat.toBundle());
    }
}
