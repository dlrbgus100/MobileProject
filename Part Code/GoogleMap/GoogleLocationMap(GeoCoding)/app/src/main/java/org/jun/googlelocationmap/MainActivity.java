package org.jun.googlelocationmap;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    LatLng Position;
    private GoogleMap map;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coder = new Geocoder(this, Locale.KOREAN);
        String address = "구미시 양호동";

        try {
            List<Address> addressList = coder.getFromLocationName(address, 1);
            if (addressList != null) {
                Address curAddresss = addressList.get(0);
                Position = new LatLng(curAddresss.getLatitude(),
                        curAddresss.getLongitude());
            }

            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            map.addMarker(new MarkerOptions().position(Position));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(Position, 15));
            map.addMarker(new MarkerOptions().position(Position));

            MarkerOptions marker = new MarkerOptions();
            marker.position(new LatLng(Position.latitude + 0.001, Position.longitude + 0.001));
            marker.title("국민은행 ");
            marker.draggable(true);
            //marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.bank));
            map.addMarker(marker);


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}