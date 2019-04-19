package com.app.androidkt.geofencing;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by brijesh on 15/4/17.
 */

public class Constants {


    public static final String GEOFENCE_ID_STAN_UNI = "STAN_UNI";
    public static final float GEOFENCE_RADIUS_IN_METERS = 1000;

    /**
     * Map for storing information about stanford university in the Stanford.
     */
    public static final HashMap<String, LatLng> AREA_LANDMARKS = new HashMap<String, LatLng>();

    static {
        // stanford university.
//        AREA_LANDMARKS.put(GEOFENCE_ID_STAN_UNI, new LatLng(37.427476, -122.170262));

        // Add your lat long here...
        AREA_LANDMARKS.put(GEOFENCE_ID_STAN_UNI, new LatLng(28.6337874, 77.3554873));
    }
}
