package com.example.doan.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.doan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    ViewFlipper vfHome;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        addControls();
    }

    private void addControls() {
        vfHome=getActivity().findViewById(R.id.vfHome);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false);
    }
    private void ActionViewFlipper() {
        List<Integer> quangcao = new ArrayList<>();
//        quangcao.add(R.drawable.vivo_quangcao);
//        quangcao.add(R.drawable.samsung_quangcao);
//        quangcao.add(R.drawable.iphone_quangcao);
//        quangcao.add(R.drawable.redmi_quangcao);
//        quangcao.add(R.drawable.oppo_quangcao);
        for(int i= 0;i<quangcao.size();i++) {
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            Glide.with(getActivity().getApplicationContext()).load(quangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            vfHome.addView(imageView);
        }
        vfHome.setFlipInterval(3000);
        vfHome.setAutoStart(true);
        Animation slide_in= AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.slide);
        Animation slide_out= AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.slide_out);
        vfHome.setInAnimation(slide_in);
        vfHome.setOutAnimation(slide_out);
    }
}