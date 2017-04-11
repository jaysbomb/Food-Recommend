

package com.example.zc_ch.login;
/**
 * Created by ZOU Zijie on 2017/4/9.
 */
        import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Donghua extends Fragment
         {
    private Button button;
    private ImageView mImageView;
    private ImageView mImageView2;
    private ImageView mImageView3;
    private ImageView mImageView4;
    private ImageView mImageView5;
    private TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =inflater.inflate(R.layout.activity_donghua,container,false);

//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        view.setSupportActionBar(toolbar);


        mImageView =(ImageView)view.findViewById(R.id.imageView4);
        mImageView2 =(ImageView)view.findViewById(R.id.imageView5);
        mImageView3 =(ImageView)view.findViewById(R.id.imageView6);
        mImageView4 =(ImageView)view.findViewById(R.id.imageView8);
        mImageView5 =(ImageView)view.findViewById(R.id.imageView9);
        mTextView = (TextView)view.findViewById(R.id.textView);
        mImageView.setAlpha(0.0f);
        mImageView2.setAlpha(0.0f);
        mImageView3.setAlpha(0.0f);
        mImageView4.setAlpha(0.0f);
        mImageView5.setAlpha(0.0f);
        mTextView.setAlpha(0.0f);
        fadeIn.run();
        return view;
    }
    public void onClick(View v) {
        Intent con = new Intent( getActivity(),MainActivity.class);
        startActivity(con);
    }
    Runnable fadeIn = new Runnable() {
        @Override
        public void run() {
            mImageView.animate().setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f);
            mImageView2.animate().setStartDelay(1000).setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f);
            mImageView3.animate().setStartDelay(3000).setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f);
            mImageView4.animate().setStartDelay(5000).setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f);
            mTextView.animate().setStartDelay(5000).setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f);
            mImageView5.animate().setStartDelay(5000).setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f).withEndAction(fadeOut);
        }
    };
    Runnable fadeOut = new Runnable() {
        @Override
        public void run() {
            mImageView.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f);
            mImageView2.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f);
            mImageView3.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f);
            mImageView4.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f);
            mTextView.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f);
            mImageView5.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f).withEndAction(fadeIn);
        }
    };







}

