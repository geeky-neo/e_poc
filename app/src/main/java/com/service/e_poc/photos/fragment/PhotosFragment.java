package com.service.e_poc.photos.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.service.e_poc.R;
import com.service.e_poc.photos.viewModel.PhotosViewModel;
import com.service.e_poc.utils.interfaceComm.FragmentChangeListener;


public class PhotosFragment extends Fragment {


    private FragmentChangeListener mFragmentDrawerListener;

    private ViewDataBinding fragmentLoginBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentLoginBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_photos,
                container, false);

        View view = fragmentLoginBinding.getRoot();

        /*
        * manage View Model
        * */
        new PhotosViewModel(getActivity(), view);


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mFragmentDrawerListener = (FragmentChangeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement IFragmentToActivity");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

}
