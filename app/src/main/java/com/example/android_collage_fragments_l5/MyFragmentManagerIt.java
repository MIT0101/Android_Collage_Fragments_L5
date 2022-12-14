package com.example.android_collage_fragments_l5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleEventObserver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.MissingResourceException;

public class MyFragmentManagerIt {
    private final String ERROR_CODE="99";
    private HashSet<String> bundlesKeys;
    private FragmentManager basiFragmentManager;
//    private Fragment currentFragment;
//    private MyFragmentResultListener myFragmentResultListener;


    public MyFragmentManagerIt(ArrayList<String> bundlesKeys,FragmentManager basiFragmentManager) {
        this.bundlesKeys = new HashSet<>(bundlesKeys);
        this.basiFragmentManager=basiFragmentManager;
//        this.currentFragment=inislalFragment;
//        setupKeysListener();
    }
    public MyFragmentManagerIt getManagerInstance(){
        return this;
    }
    public void addKey(String key){
        bundlesKeys.add(key);
    }

    //set listener
    public void setMyFragmentResultListener(Fragment fragmentThatListen,MyFragmentResultListener myFragmentResultListener){
//        this.myFragmentResultListener=myFragmentResultListener;
        setupKeysListener(fragmentThatListen,myFragmentResultListener);
    }

//send fragment result
    public void sendFragmentResult(String bundleKey,Bundle resultBundle){
        if(!bundlesKeys.contains(bundleKey)){
            throw new MissingResourceException("Key You Use Not Exist In Key List",this.getClass().getName(),ERROR_CODE);
        }
        //here must toggle my listener
//        if(myFragmentResultListener!=null) {
//            myFragmentResultListener.OnResultPassed(bundleKey, resultBundle);
//        }
        basiFragmentManager.setFragmentResult(bundleKey,resultBundle);


    }

    //set result listner for all keys
    private void setupKeysListener(Fragment fragmentThatListen,MyFragmentResultListener myFragmentResultListener){
        for (String key:bundlesKeys) {
            basiFragmentManager.setFragmentResultListener(key,fragmentThatListen,(requestKey, result) -> {
                if(myFragmentResultListener!=null) {
                    myFragmentResultListener.OnResultPassed(requestKey, result);
                }
            });
        }
    }

    public interface MyFragmentResultListener{
        void OnResultPassed(String resultCode,Bundle bundleResult);
    }
}
