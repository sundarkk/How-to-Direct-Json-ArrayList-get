package com.sundar.assignola.classes;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.sundar.assignola.R;

public class HelperClass {


    public static void snackbar(Context context, ViewGroup layout, String message) {
        Snackbar.make(layout, message, Snackbar.LENGTH_LONG)
                .setAction(context.getString(R.string.hide), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setActionTextColor(context.getResources().getColor(R.color.white))
                .show();
    }



    public static boolean getNetworkInfo(Context activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected() == true;
    }

    public static void setBannerImage(String url, ImageView imageView, Activity activity) {
        if (url == null) {
            Picasso.with(activity).load(R.drawable.banner_placeholder).into(imageView);
        } else if (url.isEmpty()) {
            Picasso.with(activity).load(R.drawable.banner_placeholder).into(imageView);
        } else {
            Picasso.with(activity).load(url).placeholder(R.drawable.banner_placeholder).into(imageView);
        }
    }
}
