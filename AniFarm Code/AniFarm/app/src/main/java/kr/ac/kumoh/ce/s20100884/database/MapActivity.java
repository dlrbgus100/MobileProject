package kr.ac.kumoh.ce.s20100884.database;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.LevelListDrawable;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    Geocoder coder;
    LatLng mapPosition;
    String mapInfo;
    String[] mapAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        mapInfo = intent.getStringExtra("map");

        StringTokenizer st = new StringTokenizer(mapInfo,"/");
        mapAddress = new String[st.countTokens()];
        for (int i=0; st.hasMoreElements();i++)
            mapAddress[i] = st.nextToken();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);

        mapFragment.getMapAsync(this);


    }
    class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            showCurrentLocation(location.getLatitude(),location.getLongitude());
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
    private void showCurrentLocation(Double latitude, Double longitude) {
        // 현재 위치를 이용해 LatLon 객체 생성
        LatLng curPoint = new LatLng(latitude, longitude);
//          mMap.addMarker(new MarkerOptions().position(curPoint));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));

        MarkerOptions mypositionmarker = new MarkerOptions();
        mypositionmarker.position(curPoint);
        mypositionmarker.title("나");
        mypositionmarker.draggable(true);

        mMap.addMarker(mypositionmarker);
        // 지도 유형 설정. 지형도인 경우에는 GoogleMap.MAP_TYPE_TERRAIN, 위성 지도인 경우에는 GoogleMap.MAP_TYPE_SATELLITE
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // Add a marker in Sydney and move the camera
        LatLng kumoh = new LatLng(36.144399, 128.393333);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kumoh, 8));
//        mMap.addMarker(new MarkerOptions().position(kumoh).title("Kumoh"));
        coder = new Geocoder(this, Locale.KOREAN);

        for(int i= 0; i<mapAddress.length; i++) {
            try {
                List<android.location.Address> addressList = coder.getFromLocationName(mapAddress[i], 1);
                if (addressList != null) {
                    android.location.Address curAddress = addressList.get(0);
                    mapPosition = new LatLng(curAddress.getLatitude(),
                            curAddress.getLongitude());
                }
                // mMap.addMarker(new MarkerOptions().position(mapPosition));

                    MarkerOptions marker = new MarkerOptions();
                    marker.position(new LatLng(mapPosition.latitude , mapPosition.longitude ));
                    marker.title("");
                    marker.draggable(true);
                    mMap.addMarker(marker);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        long minTime = 10000;
        float minDistance = 0;

        MyLocationListener listener = new MyLocationListener();
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            return;
        }

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                minTime, minDistance, listener);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                minTime, minDistance,listener);
        Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(lastLocation != null){
            showCurrentLocation(lastLocation.getLatitude(),lastLocation.getLongitude());

        }
    }

}
