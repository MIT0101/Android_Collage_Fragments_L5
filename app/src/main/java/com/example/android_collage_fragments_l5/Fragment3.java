package com.example.android_collage_fragments_l5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment3 extends Fragment {


    public Fragment3() {
        // Required empty public constructor
    }
    private static final String BUNDLE_CODE3=Keys.CODE3;

    private Button send3_btn;
    private EditText contentToSend3_txt;
    private TextView resultPassed3_txt;

    private MyFragmentManagerItZero myFragmentManagerItZero;
    private SharedPreferences preferences;

    public Fragment3(MyFragmentManagerItZero myFragmentManagerItZero){
        this.myFragmentManagerItZero=myFragmentManagerItZero;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView=inflater.inflate(R.layout.fragment_3, container, false);

        send3_btn=fragmentView.findViewById(R.id.send3_btn);
        contentToSend3_txt=fragmentView.findViewById(R.id.contentToSend3_txt);
        resultPassed3_txt=fragmentView.findViewById(R.id.resultPassed3_txt);
        preferences=getActivity().getPreferences(Context.MODE_PRIVATE);

        resultPassed3_txt.setText(preferences.getString(Keys.FR3,""));

        send3_btn.setOnClickListener((view)-> {
            String name = "";
            if (contentToSend3_txt.getText().toString().trim().length() <= 0) {
                Toast.makeText(getActivity().getApplicationContext(), "Must fill Text To Send", Toast.LENGTH_LONG).show();
                return;
            }

            Bundle resultBundle=new Bundle();
            resultBundle.putString("name",contentToSend3_txt.getText().toString().trim());

            //here action
            sendFragmentResult(BUNDLE_CODE3,resultBundle);

            //rest input
            contentToSend3_txt.setText("");
        });

        //my listener 2
        myFragmentManagerItZero.setMyFragmentResultListener(this,((resultCode, bundleResult) ->{
            Log.e("recevebb","receve code "+resultCode+" in fr1");
            if(resultCode.equals(Keys.CODE1)){
                resultPassed3_txt.setText(resultCode+" fr1 result");
            }else if(resultCode.equals(Keys.CODE2)){
                resultPassed3_txt.setText(resultCode+" fr2 result");
            }

        }));

        resultPassed3_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                preferences.edit().remove(Keys.FR3);
                preferences.edit().putString(Keys.FR3,charSequence.toString()).commit();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//       //old listener
//        getParentFragmentManager().setFragmentResultListener(Keys.CODE1,this,(requestKey, result) -> {
//            if(requestKey.equals(Keys.CODE1)){
//                resultPassed3_txt.setText("from frag1");
//                Log.i("toggle22","FROM FR1 &Data FR1 ");
//
//            }
//            else if(requestKey.equals(Keys.CODE2)){
//                resultPassed3_txt.setText("from frag2");
//                Log.i("toggle22","FROM FR1 &Data FR2 ");
//            }
//            else if(requestKey.equals(Keys.CODE3)){
//                resultPassed3_txt.setText("from frag3");
//                Log.i("toggle22","FROM FR1 &Data FR3 ");
//            }
//        });

        return fragmentView;
    }

    private void sendFragmentResult(String bundleKey,Bundle resultBundle){
//        getParentFragmentManager().setFragmentResult(bundleKey,resultBundle);
//        myFragmentManagerIt.sendFragmentResult(bundleKey,resultBundle);
        myFragmentManagerItZero.sendFragmentResult(bundleKey,resultBundle,this);
    }

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Fragment3.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Fragment3 newInstance(String param1, String param2) {
//        Fragment3 fragment = new Fragment3();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
}