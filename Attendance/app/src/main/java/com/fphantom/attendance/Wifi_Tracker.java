package com.fphantom.attendance;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

/**
 * Created by deep on 4/26/16.
 */
public class Wifi_Tracker {

    public WifiManager wifi;
    private final Context mContext;

    public Wifi_Tracker(Context mContext) {
        this.mContext = mContext;
    }

    public String getMACAddress(){

        String MAC = "";
        wifi  = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        ConnectivityManager connManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mWifi.isConnected()) {
            // Do whatever
            WifiInfo W_info = wifi.getConnectionInfo();
            MAC = W_info.getBSSID();
            return MAC;
        }
        else {
            return "00:00:00:00:00:00";
        }



    }
}
