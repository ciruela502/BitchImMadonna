package com.droidsonroids.materialmusicfacts.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.droidsonroids.materialmusicfacts.R;

/**
 * Created by marta on 01.08.2016.
 */
public class MyFloatingActionButton extends FloatingActionButton {
    private static final String TAG = "MyFloatingActionButton";

    public MyFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.FabBehavior);
        final String option = ta.getString(R.styleable.FabBehavior_fabOption);
        //hideWithDirection(option);
    }

    public void hideWithDirection(String option){
        //if right / down wyciagam z atrybutu
        if(option.contains(getContext().getString(R.string.right))){
            animateToRight();
        }
        else if(option.contains(getContext().getString(R.string.bottom))){
            animateToBottom();
        }
    }

    private void animateToRight(){
        Log.d(TAG, "animateToRight: ");



    }
    private void animateToBottom(){

    }
}
