package com.example.zc_ch.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;

import static com.example.zc_ch.login.Main2Activity.fragmentManager;

public class Mainpage extends Fragment{
    private ImageButton turnbutton;

    public AnimatedCircleLoadingView animatedCircleLoadingView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =inflater.inflate(R.layout.activity_mainpage,container,false);
        animatedCircleLoadingView = (AnimatedCircleLoadingView) view.findViewById(R.id.circle_loading_view);
        //imagebutton
//        turnbutton = (ImageButton) view.findViewById(R.id.imageButton5) ;

//        turnbutton.setOnClickListener(this);
        startLoading();
        startPercentMockThread();
        return view;
    }

    public void startLoading() {
        animatedCircleLoadingView.startDeterminate();
    }

    public void startPercentMockThread() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = null;
                Class fragmentClass = null;

                try {
                    Thread.sleep(1500);
                    for (int i = 0; i <= 100; i+=10) {
                        Thread.sleep(65);
                        changePercent(i);
                    }
                    fragmentClass = Donghua.class;
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (java.lang.InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }

    public void changePercent(final int percent) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                animatedCircleLoadingView.setPercent(percent);
            }
        });
    }



    public void resetLoading() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                animatedCircleLoadingView.resetLoading();
            }
        });
    }
}

//    @Override
//    public void onClick(View view) {
//
//
//        switch (view.getId()) {
//            case R.id.imageButton5:
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), MainActivity.class);
//                getActivity().startActivity(intent);
//                break;
//
//        }
//
//    }
//}