package com.qinyaoz.hongya.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qinyaoz.hongya.R;
import com.qinyaoz.hongya.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    @BindView(R2.id.tv_name)
    TextView tvName;
    @BindView(R2.id.hy_imageview)
    ImageView hyImageView;

    private String name;
    private int imgResId;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.hy_fragment_blank, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvName.setText(name);
        hyImageView.setImageResource(imgResId);
    }

    public void setName(String n) {
        name = n;
    }

    public void setImgResId(int id) {
        imgResId = id;
    }
}
