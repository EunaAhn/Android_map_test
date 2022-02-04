package com.digipine.maptest01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private AFragment fragmentA;
    private BFragment fragmentB;
    private FragmentTransaction transaction;
    private  MapView mapView;

//    private void getHashKey(){
//        PackageInfo packageInfo = null;
//        try {
//            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (packageInfo == null)
//            Log.e("KeyHash", "KeyHash:null");
//
//        for (Signature signature : packageInfo.signatures) {
//            try {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
//                Log.d("KeyHash", keyHash);
//
//            } catch (NoSuchAlgorithmException e) {
//                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
//            }
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 2oVb4r3mSbwi3IkA3dg46j0MU3U=
        //getHashKey();

        mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        fragmentManager = getSupportFragmentManager();

        fragmentA = new AFragment();
        fragmentB = new BFragment();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentA).commitAllowingStateLoss();
    }

    public void clickHandler(View view)
    {
        transaction = fragmentManager.beginTransaction();

        switch(view.getId())
        {
            case R.id.btn_fragmentA:
                transaction.replace(R.id.frameLayout, fragmentA).commitAllowingStateLoss();
                break;
            case R.id.btn_fragmentB:
                transaction.replace(R.id.frameLayout, fragmentB).commitAllowingStateLoss();
                break;
            case R.id.btn_fragmentMap:
                transaction.remove(fragmentA);
                transaction.remove(fragmentB).commitAllowingStateLoss();
                break;
        }
    }
}