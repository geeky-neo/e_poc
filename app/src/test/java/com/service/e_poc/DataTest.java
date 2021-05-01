package com.service.e_poc;

import android.graphics.Bitmap;
import android.net.Network;

import com.service.e_poc.utils.Data;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DataTest {
    private Data oData;

    public void setUp(){
        oData = new Data();
    }

    @Test
    public void checkDataList(){
        List<String> imageList = oData.getPetImageList();
        assertFalse(imageList.isEmpty());
    }
}