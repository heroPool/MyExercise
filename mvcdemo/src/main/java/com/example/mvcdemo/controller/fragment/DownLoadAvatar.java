package com.example.mvcdemo.controller.fragment;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mvcdemo.I;
import com.example.mvcdemo.R;
import com.example.mvcdemo.model.net.IModelBaseImage;
import com.example.mvcdemo.model.net.ModelBaseImage;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownLoadAvatar extends Fragment {
    IModelBaseImage iModelBaseImage;
    Button btndownlAvatar;
    ImageView imageViewAvatar;
    EditText editTextusername;
    public DownLoadAvatar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_down_load_avatar, container, false);
        iModelBaseImage = new ModelBaseImage();
        initView(inflate);
        setListener(inflate);
        return inflate;


    }

    private void setListener(View inflate) {
        btndownlAvatar.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                String username = editTextusername.getText().toString();
               iModelBaseImage.showImage(getActivity(), I.DOWNLOAD_AVATAR_URL+username,imageViewAvatar,R.drawable.default2,false);




            }
        });
    }

    private void initView(View inflate) {
        btndownlAvatar = (Button) inflate.findViewById(R.id.btn_downLoadAvatar);
        imageViewAvatar = (ImageView) inflate.findViewById(R.id.imageviewAvatar);
        editTextusername = (EditText) inflate.findViewById(R.id.edittextusername);

    }

}
