package com.example.fragmentitem.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmentitem.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class gouwucheItem extends Fragment {


    public gouwucheItem() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gouwuche_item, container, false);
    }

}