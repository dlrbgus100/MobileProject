package org.jun.googlelocationmap;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

//map.setMapType(Googlemap.MAP_TYPE_SATELLITE);
public class MainActivity extends Activity {

    Geocoder coder;
    private GoogleMap map;
    LatLng mapposition;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        coder = new Geocoder(this, Locale.KOREAN);
        String address = "구미시 구미역";      // 데이터 주소
        try {
            List<Address> addressList = coder.getFromLocationName(address, 1);
            if(addressList != null){
                Address curAddresss = addressList.get(0);
                mapposition = new LatLng( curAddresss.getLatitude(),
                        curAddresss.getLongitude());
            }


            map.addMarker(new MarkerOptions().position(mapposition));
            MarkerOptions marker = new MarkerOptions();
            marker.position(new LatLng(mapposition.latitude+0.001, mapposition.longitude+0.001));
            marker.title("국민은행 ");
            marker.draggable(true);
           // marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.bank));
            map.addMarker(marker);


        } catch (Exception ex){
            ex.printStackTrace();
        }
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        long minTime = 10000;
        float minDistance = 0;

        MyLocationListener listener = new MyLocationListener();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
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



    class MyLocationListener implements LocationListener{
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
      //  map.addMarker(new MarkerOptions().position(curPoint));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));

        // 지도 유형 설정. 지형도인 경우에는 GoogleMap.MAP_TYPE_TERRAIN, 위성 지도인 경우에는 GoogleMap.MAP_TYPE_SATELLITE
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }
}