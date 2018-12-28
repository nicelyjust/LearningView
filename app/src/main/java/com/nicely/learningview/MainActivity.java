package com.nicely.learningview;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.nicely.learningview.bean.PieVo;
import com.nicely.learningview.location.GPSLocationManager;
import com.nicely.learningview.view.PieView;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks;

public class MainActivity extends AppCompatActivity implements PermissionCallbacks, LocationListener {
    private static final int RC_LOCATION_PERM = 0x03;
    private List<PieVo> mPieVos = new ArrayList<>(6);
    private GPSLocationManager mGpsLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PieView view = (PieView) findViewById(R.id.pv_view);
        PieVo pieVo = new PieVo(3,0.3f,"java");
        pieVo.setColor(0xffff0000);
        PieVo pieVo1 = new PieVo(4,0.4f,"js");
        pieVo1.setColor(0xff00ff00);
        PieVo pieVo2 = new PieVo(3,0.3f,"c");
        pieVo2.setColor(0xff0000ff);
        mPieVos.add(pieVo);
        mPieVos.add(pieVo1);
        mPieVos.add(pieVo2);
        view.setData( new int[]{3,4,3}, new String[]{"JAVA", "JS","C"});
        requestCamera();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        } else {

        }
    }

    @AfterPermissionGranted(RC_LOCATION_PERM)
    public void requestCamera() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_COARSE_LOCATION) || EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            if (mGpsLocationManager == null) {
                mGpsLocationManager = new GPSLocationManager(this);
            }
            Location location = mGpsLocationManager.getLocation(this);
            Log.d("lz", "onCreate: "+location);
        } else {
            EasyPermissions.requestPermissions(this, "", RC_LOCATION_PERM, Manifest.permission.ACCESS_COARSE_LOCATION ,Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode ,permissions ,grantResults,this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (mGpsLocationManager == null) {
            mGpsLocationManager = new GPSLocationManager(this);
        }
        Location location = mGpsLocationManager.getLocation(this);
        Log.d("lz", "onCreate: "+location);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this ,"?????" ,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
