package com.example.quan_ly_cham_cong.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.quan_ly_cham_cong.R;

public class Helper {
    private Context context;
    private ProgressDialog mProgressDialog;

    public Helper(Context context){
        this.context = context;
    }

    public void showProgressLoading(){
        if(mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(context.getString(R.string.lbl_loading));
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
    }

    public void dismisProgess(){
        if(mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }
}
