package com.droidsonroids.materialmusicfacts.views.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.droidsonroids.materialmusicfacts.R;

/**
 * Created by marta on 01.08.2016.
 */
public class ShowHideFabBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    private static final String TAG = "ShowHideFab";
    private static String option;
    private static Boolean isShown;
    //private FloatingActionButton mFloatingActionButton;
    Animation hideAnimation = new TranslateAnimation(0, 300, 0, 0);

    ShowHideFabBehavior() {
        super();
    }

    public ShowHideFabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FabBehavior);
        option = ta.getString(R.styleable.FabBehavior_fabOption);

    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof NestedScrollView;

    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        Log.e(TAG, "onNestedScroll called wejdz");
        isShown = child.isShown();
        if(!isShown) {
            Animation showAnimation = new TranslateAnimation(300, 0, 0, 0);
            showAnimation.setDuration(500);
            child.startAnimation(showAnimation);
        }
     //   child.show();

    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

      isShown = child.isShown();
        if (isShown) {
            Log.e(TAG, "onNestedScroll called wyjdz");
            Animation showAnimation = new TranslateAnimation(0, 300, 0, 0);
            showAnimation.setDuration(500);
            child.startAnimation(showAnimation);
            isShown=false;
        }
       else if(!isShown) {

             isShown = true;
        }
        //child.hide();

    }

    // .showAnimation
}
