package com.nicely.learningview.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import java.util.List;

/*
 *  @项目名：  LearningView
 *  @包名：    com.nicely.learningview.location
 *  @创建者:   lz
 *  @创建时间:  2018/12/21 22:40
 *  @修改时间:  nicely 2018/12/21 22:40
 *  @描述：
 */
public class GPSLocationManager {


    private final LocationManager mLocationManager;

    public GPSLocationManager(Context context) {
        mLocationManager = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
    }

    public LocationManager getLocationManager() {
        return mLocationManager;
    }

    @SuppressLint("MissingPermission")
    public Location getLocation(LocationListener listener) {
        List<String> providers = mLocationManager.getProviders(true);
        Location location;
        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 100, listener);
            location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                return location;
            }
        }
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100, listener);
            location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                return location;
            }
        }
        return null;
    }

    /**
     * getlocation
     * @param listener
     */
    public void removeListener(LocationListener listener){
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(listener);
        }
    }
}
