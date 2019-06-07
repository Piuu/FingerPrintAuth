package com.example.fingerprintauth;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;

    public FingerprintHandler(Context context) {
        this.context = context;
    }

    public void startAuth (FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){
        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject,cancellationSignal,0,this,null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.update("There was an Auth error! :/ "+errString, false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update ("Authentication Failed ",false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("Error: "+helpString , false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("You can now access the app", true);
    }

    private void update(String s, boolean b) {
        TextView targettxt = (TextView) ((Activity)context).findViewById(R.id.targettxt);
        ImageView imgView = (ImageView)((Activity)context).findViewById(R.id.img);

        targettxt.setText(s);

        if (b==false){
            targettxt.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        }
        else{
            targettxt.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            imgView.setImageResource(R.mipmap.action_done);
        }
    }
}
