package com.example.android_collage_fragments_l5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import java.util.HashMap;
import java.util.HashSet;

public class MyFragmentManagerItZero {
    private HashMap<String,Bundle> bundleResults;
    private HashMap<String,MyFragmentResultListenerZero> fragmentListeners;
    private HashSet<String> fragments;

    public MyFragmentManagerItZero() {
        bundleResults=new HashMap<>();
        fragmentListeners=new HashMap<>();
        fragments=new HashSet<>();
    }

    public void setMyFragmentResultListener(Fragment fragmentThatListen, MyFragmentResultListenerZero myFragmentResultListenerZero){
        fragmentListeners.put(fragmentThatListen.getClass().getName(),myFragmentResultListenerZero);
    }

    //send fragment result
    public<T extends Fragment> void  sendFragmentResult (String bundleKey,Bundle resultBundle,T sender){

        if(!fragments.contains(sender)){
            fragments.add(sender.getClass().getName());
        }
        bundleResults.put(bundleKey,resultBundle);
        //send result event for all fragments
        for (String fragmentInstanceKey:fragmentListeners.keySet()) {
            if(fragmentListeners.get(fragmentInstanceKey)!=null){
                fragmentListeners.get(fragmentInstanceKey).OnResultPassed(bundleKey,resultBundle);
            }
        }
    }


    public interface MyFragmentResultListenerZero{
        void OnResultPassed(String resultCode, Bundle bundleResult);
    }

    public interface BundleResultListener{
        void OnBundleResult(String bundleKey,Bundle resultBundle);
    }
}
