package com.example.sample7googlemap;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends ActionBarActivity implements
	GoogleMap.OnMarkerClickListener,
	GoogleMap.OnInfoWindowClickListener,
	GoogleMap.OnMarkerDragListener {

	GoogleMap mMap;
	LocationManager mLM;
	String mProvider = LocationManager.GPS_PROVIDER;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLM = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        
        setupMapIfNeeded();
        
        Button btn = (Button)findViewById(R.id.btn_marker);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CameraPosition position = mMap.getCameraPosition();
				LatLng latLng = position.target;
				addMarker(latLng.latitude, latLng.longitude);
			}
		});
        
    }

    LocationListener mListener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			moveMap(location);
			mLM.removeUpdates(this);
		}
	};
	
	
	private void addMarker(double lat, double lng) {
		LatLng latLng = new LatLng(lat, lng);
		MarkerOptions options = new MarkerOptions();
		options.position(latLng);
		options.title("My Marker");
		options.snippet("my content...");
		options.icon(BitmapDescriptorFactory.defaultMarker());
		options.anchor(0.5f, 1);
		options.draggable(true);
		Marker marker = mMap.addMarker(options);
		
	}
	private void moveMap(Location location) {
		if (mMap != null) {
			LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
			CameraPosition position;
			CameraPosition.Builder builder = new CameraPosition.Builder();
			builder.target(latlng);
			builder.zoom(15.5f);
//			builder.bearing(30);
//			builder.tilt(30);
			position = builder.build();
			
			CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
//			CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latlng, 15.5f);
			
//			update = CameraUpdateFactory.zoomIn();
			
//			mMap.moveCamera(update);
			mMap.animateCamera(update);
			
			
		}
	}
	
    @Override
    protected void onStart() {
    	super.onStart();
    	Location location = mLM.getLastKnownLocation(mProvider);
    	if (location != null) {
    		mListener.onLocationChanged(location);
    	}
    	mLM.requestLocationUpdates(mProvider, 0, 0, mListener);
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	mLM.removeUpdates(mListener);
    }
    @Override
    protected void onResume() {
    	super.onResume();
    	setupMapIfNeeded();
    }

    private void setupMapIfNeeded() {
    	if (mMap == null) {
    		mMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragment1)).getMap();
    		if (mMap != null) {
    			setupMap();
    		}
    	}
    }
    
    private void setupMap() {
    	mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    	mMap.setMyLocationEnabled(true);
    	
    	mMap.getUiSettings().setZoomControlsEnabled(true);
    	
    	mMap.getUiSettings().setCompassEnabled(true);
    	mMap.setOnMarkerClickListener(this);
    	mMap.setOnInfoWindowClickListener(this);
    	mMap.setOnMarkerDragListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	@Override
	public boolean onMarkerClick(Marker marker) {
		Toast.makeText(this, "marker click", Toast.LENGTH_SHORT).show();
//		marker.showInfoWindow();
		return false;
	}
	@Override
	public void onInfoWindowClick(Marker marker) {
		Toast.makeText(this, "info window click", Toast.LENGTH_SHORT).show();
		marker.hideInfoWindow();
	}
	@Override
	public void onMarkerDrag(Marker arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onMarkerDragEnd(Marker arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onMarkerDragStart(Marker arg0) {
		// TODO Auto-generated method stub
		
	}
}
