        package com.example.project3_dzegarra;

        import android.Manifest;
        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.location.Address;
        import android.location.Geocoder;
        import android.location.Location;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.util.Log;
        import android.view.KeyEvent;
        import android.view.View;
        import android.view.inputmethod.EditorInfo;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;
        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import com.google.android.gms.location.FusedLocationProviderClient;
        import com.google.android.gms.location.LocationServices;
        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import java.io.IOException;
        import java.util.List;

        public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {


            private static final String TAG = "MapActivity";
            private EditText mSearchText;

            private Boolean mLocationPermissionsGranted = false;
            private GoogleMap mMap;
            private FusedLocationProviderClient mFusedLocationProviderClient;
            double[] coords = {38.8315,-77.3117};
            Button back;

            @Override
            public void onMapReady(GoogleMap googleMap) {
                Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
                mMap = googleMap;
                if (mLocationPermissionsGranted) {

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    mMap.setTrafficEnabled(true);
                    mMap.setIndoorEnabled(true);
                    getDeviceLocation();
                }
            }

            @Override
            protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_map);
                mSearchText = (EditText) findViewById(R.id.input_search);

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                back = findViewById(R.id.backtomain);
                getLocationPermission();
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MapActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

                mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if ( (actionId == EditorInfo.IME_ACTION_DONE) || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN ))){
                            getLatLong();

                            InputMethodManager imm = (InputMethodManager) mSearchText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                            LatLng loc = new LatLng(coords[0],coords[1]);
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc,17));
                            mMap.addMarker(new MarkerOptions().position(loc).title("Marker at location provided"));

                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                });


            }

            public void getLatLong() {


                String location = mSearchText.getText().toString();
                if(location.length()==0){
                    Toast.makeText(this,"Search for a restaurant!",Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Address> geocodeMatches = null;

                try {
                    geocodeMatches =  new Geocoder(this).getFromLocationName(location, 1);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    return;
                }
                if (!geocodeMatches.isEmpty())
                {
                    coords[0] = geocodeMatches.get(0).getLatitude();
                    coords[1] = geocodeMatches.get(0).getLongitude();
                }
                System.out.println("LAT: "+coords[0]+" LONG: "+coords[1]);
            }

            private void getDeviceLocation(){
                Log.d(TAG, "getDeviceLocation: getting the devices current location");

                mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

                try{

                    if(mLocationPermissionsGranted){
                        Task<Location> location = mFusedLocationProviderClient.getLastLocation();
                        location.addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if(task.isSuccessful()){
                                    Location currentLocation = (Location) task.getResult();

                                    if(currentLocation!=null) {
                                        coords[0] = currentLocation.getLatitude();
                                        coords[1] = currentLocation.getLongitude();
                                        LatLng loc = new LatLng(coords[0],coords[1]);
                                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc,17));
                                        mMap.addMarker(new MarkerOptions().position(loc).title("Your location => Longitude: "+Double.toString(coords[0])+" Latitude: "+Double.toString(coords[1])));
                                    }
                                    else{
                                        coords[0] = 38.8315;
                                        coords[1] = -77.3117;
                                        LatLng loc = new LatLng(coords[0],coords[1]);
                                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc,17));
                                        mMap.addMarker(new MarkerOptions().position(loc).title("GMU location => Longitude: "+Double.toString(coords[0])+" Latitude: "+Double.toString(coords[1])));
                                        Toast.makeText(MapActivity.this, "Unable to get current location", Toast.LENGTH_LONG).show();

                                    }

                                }else{
                                    coords[0] = 38.8315;
                                    coords[1] = -77.3117;
                                    LatLng loc = new LatLng(coords[0],coords[1]);
                                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc,17));
                                    mMap.addMarker(new MarkerOptions().position(loc).title("GMU location => Longitude: "+Double.toString(coords[0])+" Latitude: "+Double.toString(coords[1])));
                                    Toast.makeText(MapActivity.this, "Unable to get current location", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }catch (SecurityException e){
                    Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
                }
            }

                    public void getLocationPermission(){
                        ActivityCompat.requestPermissions(MapActivity.this, new String[]
                                {Manifest.permission.ACCESS_FINE_LOCATION}, 1234);
                        ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1234);
                        ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},1234);
                        mLocationPermissionsGranted=true;
                    }

                    @Override
                    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                        if (requestCode == 123) {
                            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                                mLocationPermissionsGranted=true;
                            } else {
                                Toast.makeText(this, "Required permission", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

}