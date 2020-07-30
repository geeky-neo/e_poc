package com.service.e_poc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.service.e_poc.photos.fragment.PhotosFragment;
import com.service.e_poc.utils.FragmentNameConstants;
import com.service.e_poc.utils.interfaceComm.FragmentChangeListener;
import com.service.e_poc.utils.receiver.NetworkStateChangeReceiver;

import static com.service.e_poc.utils.receiver.NetworkStateChangeReceiver.IS_NETWORK_AVAILABLE;

public class MainActivity extends AppCompatActivity implements
        FragmentChangeListener {


    private Fragment fragment;
    private int loginFlag;
    private Dialog dialog;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkFilter();

        displayView(FragmentNameConstants.PHOTOS, null);
    }

    /*
    * Network Manage
    * register Receiver
    * show message in Snack bar network connect or disconnect
    * */
    private void networkFilter() {
        IntentFilter intentFilter = new IntentFilter(NetworkStateChangeReceiver.NETWORK_AVAILABLE_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean isNetworkAvailable = intent.getBooleanExtra(IS_NETWORK_AVAILABLE, false);
                String networkStatus = isNetworkAvailable ? "connected" : "disconnected";

                Snackbar.make(findViewById(R.id.activity_main), "Network Status: " + networkStatus, Snackbar.LENGTH_LONG).show();
            }
        }, intentFilter);
    }


    /*
    * call back method to manage fragment change
    * */
    @Override
    public void onFragmentSelected(View view, int position, Bundle bundle) {
        displayView(position, bundle);
    }

    /*
    * manage Fragment in all application
    * switch fragment according to select
    * manage back fragment
    * */
    public void displayView(int position, Bundle bundle) {
        fragment = null;
        loginFlag = -1;
        String title = getString(R.string.app_name);
        switch (position) {
            case FragmentNameConstants.PHOTOS:
                fragment = new PhotosFragment();
                loginFlag = 1;
                break;

            default:
                break;
        }

        if (fragment != null) {
            if (null != bundle) {
                fragment.setArguments(bundle);
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (loginFlag == 1) {
                for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                    fragmentManager.popBackStack();
                }
                fragmentTransaction.replace(R.id.container_body, fragment);
            } else {

                fragmentTransaction.replace(R.id.container_body, fragment).addToBackStack(fragment.getClass().getName());
            }
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }
    }

}