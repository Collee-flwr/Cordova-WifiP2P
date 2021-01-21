package com.wifiP2P;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.os.Looper.getMainLooper;

/**
 * This class echoes a string called from JavaScript.
 */
public class WifiP2P extends CordovaPlugin {

    Context context;
    CordovaActivity activity;
    WifiP2pManager manager;
    WifiP2pManager.Channel channel;
    WiFiDirectBroadcastReceiver receiver;
    private final IntentFilter intentFilter = new IntentFilter();

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        context = cordova.getContext();
        activity = (CordovaActivity) cordova.getActivity();
        manager = (WifiP2pManager) cordova.getActivity().getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(context, getMainLooper(), null);

        // Indicates a change in the Wi-Fi P2P status.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);

        // Indicates a change in the list of available peers.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);

        // Indicates the state of Wi-Fi P2P connectivity has changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        // Indicates this device's details have changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

    }




    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        else if (action.equals("discover")){
            this.discover(callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void discover(CallbackContext callbackContext){

        receiver = new WiFiDirectBroadcastReceiver(callbackContext);
        context.registerReceiver(receiver,intentFilter);
    }

    private class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

        private CallbackContext callbackContext;

        public WiFiDirectBroadcastReceiver(CallbackContext callbackContext) {
            this.callbackContext = callbackContext;

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
                // Determine if Wifi P2P mode is enabled or not, alert
                // the Activity.
                int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
                callbackContext.success(state);
            }

        }


    }

}
