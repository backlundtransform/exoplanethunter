/*Copyright Göran Bäcklund gbanm06@gmail.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/


package com.varoid.exoplanethunter;

import android.os.Bundle;


import android.content.Context;
import android.content.Intent;

import android.content.res.Configuration;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;





import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

 

 
public class GPS extends SherlockActivity implements SensorEventListener{
		protected LocationManager locationManager;
		protected LocationListener locationListener;
		private SensorManager SensorManager;
		
		 
		 Compass myCompass;
		 Hemisphere map;
		 Calc  evelation_angle;
		protected Context context;
	
		TextView vylatitude,vylongitude,vyaltitude,vyazimuth,vytime,vydec,vyra;
	
		
		String provider;
		Location defaultloc;
	
		float altitude_angel,azimuth_angle;
		double latitude, longitude,declination,hourangle,siderealtime, right_ascension;
		 
		protected boolean gps_enabled,network_enabled;
 
		@Override
			protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			 getSupportActionBar().setTitle(R.string.search);
				setContentView(R.layout.gps);
				CheckEnableGPS();
				
				vylatitude = (TextView) findViewById(R.id.latitud);
				vylongitude= (TextView) findViewById(R.id.longitude);
				vyaltitude= (TextView) findViewById(R.id.altitude);
				vyazimuth= (TextView) findViewById(R.id.azimuth);
				vytime= (TextView) findViewById(R.id.time);
				vyra= (TextView) findViewById(R.id.ra);
				vydec= (TextView) findViewById(R.id.dec);
				map = (Hemisphere)findViewById(R.id.starmap);
				myCompass = (Compass)findViewById(R.id.mycompass);
				SensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
  			  	locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				Criteria criteria = new Criteria();
				String bestProvider = locationManager.getBestProvider(criteria, false);
			
				try{

						defaultloc = locationManager.getLastKnownLocation(bestProvider);
						
						location(defaultloc);
						
				}
				catch(NullPointerException e)
				{
					longitude= 0;
					latitude=0;
				}
				
				
				map.setOnTouchListener(new OnTouchListener(){
					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						Intent intent = new Intent(GPS.this, com.varoid.exoplanethunter.MainActivity.class);
						intent .putExtra("url", VaroidJSON.url+Planetclasses.explore(right_ascension,declination));
		    	        startActivity(intent);
						return true;
					}
				
				});
		
					
	 locationListener = new LocationListener() {
		  
		@Override
		public void onLocationChanged(Location location) {
		
			location(location);
		
		}
 
		@Override
		public void onProviderDisabled(String provider) {
			
		}
 
		@Override
		public void onProviderEnabled(String provider) {

				
		}
 
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			
		}
		    } ;
		    
		
		    locationManager.requestLocationUpdates(bestProvider, 0, 0, locationListener);
		
		}
		
		
		 
		  @Override
		public void onConfigurationChanged(Configuration newConfig) {
			
			super.onConfigurationChanged(newConfig);
			map.refresh();
		}



		@Override
		  public void onAccuracyChanged(Sensor sensor, int accuracy) {
			 
  		
		  }
		  
		  
		  
		  @Override
		  protected void onResume() {
			  super.onResume();
			 
				  SensorManager.registerListener(this, SensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), android.hardware.SensorManager.SENSOR_DELAY_NORMAL);
				  SensorManager.registerListener(this, SensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), android.hardware.SensorManager.SENSOR_DELAY_NORMAL);
			 
				  
			  
		}
		  
		  protected void onPause() {
			    super.onPause();
			    	map.refresh();
			    	SensorManager.unregisterListener(this);
			    	
			    }
		

		  @Override
		public void onDestroy() {
			
			super.onDestroy();
			map.refresh();
		}

		float[] accelerometer_values;
		  float[] magnitude_values;
		@SuppressWarnings("static-access")
		@Override
		  public void onSensorChanged(SensorEvent event)
		  {
		
				 azimuth_angle = 0;
			  
	           
			 
			  if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
			  {
				 
				  accelerometer_values =Calc.filter(event.values.clone(), accelerometer_values);
				  evelation_angle = new Calc(accelerometer_values[0],accelerometer_values[1],accelerometer_values[2]);
				  altitude_angel = (float) (Math.toDegrees(evelation_angle.dot_product())-90);
				 
			  }
		
			  if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
			  {
				 magnitude_values = Calc.filter(event.values.clone(),  magnitude_values);
			  }
			  float[] RO = new float[16];
			  float[] RI= new float[16];
			  float[] IO = new float[16];
			  float[] orientationValues = new float[3];
			  
		
			  if (magnitude_values != null && accelerometer_values != null)
			  {
				  SensorManager.getRotationMatrix(RO, IO,accelerometer_values, magnitude_values);
				  SensorManager.remapCoordinateSystem(RO, SensorManager.AXIS_X,
			      SensorManager.AXIS_Z, RI);
				  SensorManager.getOrientation(RI, orientationValues);

				  	
				
			  }
			      azimuth_angle = (float) ((Math.toDegrees(orientationValues[0])));
			   	  azimuth_angle = (azimuth_angle  + 360)%360;
			  	  declination=Calc.declination(latitude,altitude_angel, azimuth_angle);
				  vydec.setText("Dec: " + String.format("%.2f", declination));
				  hourangle=Calc.hourangle(latitude,altitude_angel, azimuth_angle);
				  vyaltitude.setText("Alt: " +String.format("%.2f", altitude_angel));
				  vyazimuth.setText("Az: " +String.format("%.2f", azimuth_angle));
				  siderealtime=Calc.siderealtime(longitude, Calc.minutes(), Calc.days());
				  right_ascension=Calc.right_ascension(siderealtime,hourangle);
				  vyra.setText("Ra: " + String.format("%.2f", right_ascension/15));
				  vytime.setText(Calc.timeformat(siderealtime));
				  myCompass.update(Math.toRadians(azimuth_angle));
				  map.update((int)right_ascension,(int)declination);
			

			  
			  
			
		  }
		
		private void CheckEnableGPS(){
		    String provider = Settings.Secure.getString(getContentResolver(),
		      Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		       if(!provider.equals("")){
		        
		        Toast.makeText(GPS.this, getResources().getString(R.string.gps) ,
		          Toast.LENGTH_LONG).show();
		       }else{
		        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
		           startActivity(intent);
		       }

		   }
		
		private void location(Location location){
			longitude= location.getLongitude();
			latitude= location.getLatitude();
			vylatitude.setText("Lat:" + String.format("%.2f", latitude));
			vylongitude.setText("Long:" + String.format("%.2f", longitude));
			siderealtime=Calc.siderealtime(longitude, Calc.minutes(), Calc.days());
			
			vytime.setText(Calc.timeformat(siderealtime));

		   }

		 @Override
		    public boolean onCreateOptionsMenu(Menu menu) {
		       MenuInflater inflater = getSupportMenuInflater();
		       inflater.inflate(R.menu.search, menu);
		       return super.onCreateOptionsMenu(menu);
		    }
			
			@Override
			public boolean onMenuItemSelected(int featureId, MenuItem item) {
				switch (item.getItemId()) {
				
				case R.id.search:
	    		
	    			
	            		
	             		
	    			
					map.refresh();
		               finish();
		            		
		             		
		    			
		    			try
		     	         
		     	         {
		     	           Intent intent = new Intent(GPS.this, com.varoid.exoplanethunter.MainActivity.class);
		     	           intent .putExtra("url", VaroidJSON.url+Planetclasses.explore(right_ascension,declination));
		     	           startActivity(intent);
		     	        
		     	         }
		     	         catch (Exception e)
		     	         {
		     	             e.printStackTrace();
		     	         }
	     	        
	     	        
				
				case R.id.back:
					
					
					finish();
			        
			       
			       
					return true;
				
			}
				return false;
			}



			
		
		
		 }	

		
