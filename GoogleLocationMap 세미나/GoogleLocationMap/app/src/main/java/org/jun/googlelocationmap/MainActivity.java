package org.jun.googlelocationmap;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
// 361444249,128.3910799
//map.setMapType(Googlemap.MAP_TYPE_SATELLITE);
public class MainActivity extends Activity {

    static final LatLng Kumoh = new LatLng( 36.1444249,128.3910799);
    private GoogleMap map;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        /*마커 클릭했을 떄 처리 : 리스너 달기
        MarkerOptions KumohMarker = new MarkerOptions().position(Kumoh).title("금오공과대학교").snippet("4년제 국립대학교");
        */

        map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.addMarker(new MarkerOptions().position(Kumoh));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom( Kumoh, 15));
        map.addMarker(new MarkerOptions().position(Kumoh));


        /* 마커를 추가하고 말풍선 표시한 것을 보여주도록 호출
        map.addMarker(KumohMarker).showInfoWindow();
        */


        /* 마커 클릭했을 떄 처리 : 리스너 달기
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MainActivity.this, "금오공과대학교\n4년제 국립대학교", Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/

    }

}