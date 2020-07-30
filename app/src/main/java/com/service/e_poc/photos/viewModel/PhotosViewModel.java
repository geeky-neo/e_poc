package com.service.e_poc.photos.viewModel;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.service.e_poc.R;
import com.service.e_poc.utils.Data;

import java.util.List;
import java.util.Observable;


public class PhotosViewModel extends Observable implements View.OnClickListener {
    private final Activity context;
    private final List<String> petImageList;
    private ImageView imgPhoto;
    private Button btnChangeImage;
    private int imageIndex =0;

    SharedPreferences sharedpreferences;

    public PhotosViewModel(Activity context, View root) {
        this.context = context;
        /*
        * get List of images url data
        * */
        petImageList=Data.getPetImageList();
        /*
        * manage shared preferences and get list index value
        * */
        sharedpreferences = context.getSharedPreferences(Data.MyPREFERENCES, Context.MODE_PRIVATE);
        imageIndex=sharedpreferences.getInt(Data.INDEX, 0);
        findView(root);
        setImage();
    }

    /*
    * set Image on imageView using Glide and manage
    * */
    private void setImage() {
        if(null!=petImageList && petImageList.size()!=0){
            Glide.with(context)
                    .load(petImageList.get(imageIndex))
                    .placeholder(R.drawable.placeholder)
                    .into(imgPhoto);
        }
    }

    /*
    * update Index to change image on Click to button
    * */
    private void updateLastIndex(int value)
    {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(Data.INDEX, value);
        editor.commit();

    }


    /*
    * find view by ids of all this fragment
    * we can also use butter Knife and other Annoutation type of but now i use this just 2 value
    * */
    private void findView(View root) {
        btnChangeImage=(Button)root.findViewById(R.id.btnChangeImage);
        btnChangeImage.setOnClickListener(this);
        imgPhoto=(ImageView)root.findViewById(R.id.imgPhoto);

    }

    @Override
    public void onClick(View view) {
        if(view==btnChangeImage){

            changeImage();
        }
    }

    /*
    * Change Image on Click of button
    * update value in SharedPreferences
    * update image to image View
    * */
    private void changeImage() {
        imageIndex = imageIndex +1;
        if(imageIndex==petImageList.size()){
            imageIndex=0;
        }
        updateLastIndex(imageIndex);
        setImage();

    }

}
