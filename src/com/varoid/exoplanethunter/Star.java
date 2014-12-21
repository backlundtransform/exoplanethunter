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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;




import android.app.ProgressDialog;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.widget.ArrayAdapter;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;

public class Star extends SherlockActivity {
    VaroidJSON j;
    HZ hz;
    ImageView img;
    ArrayList<String> planet_array_list;
    ArrayList<String> planet_array_list2;
    String starname, distance, constellation, starmass,metal,temp,mag,
    starradius, luminosity, age, numberplanets,type,habzone, numberhab,numberpl,habzonemin,habzonemax;
    TextView vy , massrow, radiusrow,  luminosityrow, agerow, metalrow,temprow;
    ListView  planetlist;
    ImageButton bcon, bplanet, bsolar;
    ArrayAdapter<String> arrayAdapter;

    ProgressDialog dialog;
		
    Startype startype;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        dialog = new ProgressDialog(this);
	    dialog.setCancelable(true);
	    dialog.setMessage(getResources().getString(R.string.somethingelse));
	    dialog.show();
	    planet_array_list = new ArrayList<String>();
           planet_array_list2 = new ArrayList<String>();
         
           j = new VaroidJSON();
       
         new LongOperation().execute(""); 
    		}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getSupportMenuInflater();
       inflater.inflate(R.menu.menu, menu);
       return super.onCreateOptionsMenu(menu);
    }
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		
		 
		case R.id.home:
			
			
			
			Intent intent = new Intent(Star.this, com.varoid.exoplanethunter.Splash.class);
	       
	       
	        startActivity(intent);
			return true;
			
		case R.id.back:
			
			
			finish();
	        
	       
	       
			return true;
		
	}
		return false;
	}
           private class LongOperation extends AsyncTask<String, Void, String> {

               @Override
               protected String doInBackground(String... params) {
               
                  starname = getIntent().getExtras().getString("STARNAME");

                try
                	{
                    starname  = URLEncoder.encode(starname , "ISO-8859-1");
                
                    
                } catch (UnsupportedEncodingException e1) {
                    
                    e1.printStackTrace();
                } 
               
                
                
                
              String url= VaroidJSON.url+"fields=[name,star_name,star_type,star_distance,star_mass,star_radius,star_luminosity,star_age,star_constellation,star_no_planets,hzd,star_teff,star_apparmag,star_no_planetshz,star_habzonemin,star_habzonemax]&star.name="+starname;
            
            try
                 {
                Log.v("TEST", url);
                  j.readJSONFeed(url);
                
                 }
                 catch (Exception e)
                 {
                     e.printStackTrace();
                 }
                 return j.get_data();
               }      

               @Override
               protected void onPostExecute(String result) {    
            

            	   	   dialog.cancel();
            	       setContentView(R.layout.star);
            	       getSupportActionBar().setTitle(j.planet("star_name"));
                       bplanet = (ImageButton) findViewById(R.id.bplanet);
                       bsolar = (ImageButton) findViewById(R.id.bviewsolarsystem);
                       bcon = (ImageButton) findViewById(R.id.bconst);
                       vy = (TextView) findViewById(R.id.star);
                       massrow  = (TextView) findViewById(R.id.starmasstextView);
                       radiusrow  = (TextView) findViewById(R.id.starradieView);
                       luminosityrow  = (TextView) findViewById(R.id.starluminositettextView);
                       temprow  = (TextView) findViewById(R.id.startemptextView);
                       agerow  = (TextView) findViewById(R.id.staragetextView);
                       planetlist = (ListView) findViewById(R.id.list);
                       img = (ImageView) findViewById(R.id.starcolor);
                       
                       bcon = (ImageButton) findViewById(R.id.bconst);
           
                       starname =  ExoString.name(j.planet("star_name"),Constellation.getconstellation(j.planet("star_constellation"),getResources()),getResources());
                      
                       starmass = j.planet("star_mass");
                       starradius = j.planet("star_radius");
                       luminosity= j.planet("star_luminosity");
                       age = ExoString.removedec(j.planet("star_age"));
                    
                       temp= j.planet("star_teff");
                       mag = j.planet("star_apparmag");
                    
                       habzonemin= j.planet("star_habzonemin");
                       habzonemax= j.planet("star_habzonemax");
                       numberhab = j.planet("star_no_planetshz");
                       numberplanets = j.planet("star_no_planets");
                       startype = new Startype(j.planet("star_type"));
                       distance = j.planet("star_distance");
                       mag = ExoString.mag(mag, getResources());
                       habzone=ExoString.habzone( habzonemin,habzonemax ,getResources()); 
                    
                       
                       massrow.setText(Planetclasses.getData(starmass, starmass+"*" +getResources().getString(R.string.sun), getResources()));
                       radiusrow.setText(Planetclasses.getData(starradius, starradius+"*" +getResources().getString(R.string.sun), getResources()));
                       luminosityrow.setText(Planetclasses.getData(luminosity, 
                               ExoString.decFormat(luminosity)+"*" +getResources().getString(R.string.sun), getResources())
                        );
                       agerow.setText(Planetclasses.getData(age, age+" "+getResources().getString(R.string.age), getResources()));
                       temprow.setText(Planetclasses.getData(temp, ExoString.decFormattemp(temp)+" °C", getResources()));
                       img.setImageResource(startype.getBildId(getResources()));
                       distance = ExoString.decFormatdist(distance, getResources());
                       type=startype.gettype( getResources());
                       numberplanets=ExoString.numberplanets(numberplanets,numberhab, getResources());
                       Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_SHORT);
                        
                      
                        
                       if (starname.equals("")){
                    	   toast.setGravity(Gravity.CENTER, 0, 0);
                           toast.show();
                              
                       }
                         
                         
                        vy.setText( starname+" "+distance +type+" "+mag+System.getProperty ("line.separator")
                                   +System.getProperty ("line.separator")
                                   +habzone+" "+numberplanets);
                          
                         
                          
                          
                        
                          for(int i=0;  i<ExoString.floatvalue(j.planet("star_no_planets"));i++){
                        	  		
                        	 hz = new HZ(j.planet(i,"hzd"));
                        	
                             
                                 if( hz.val.equals("mitt"))
                                 {
                                    
                                	 
                                	 planet_array_list.add(j.planet(i,"name")+" ("+getResources().getString(R.string.habzone)+")");
                                 }
                                 else
                                 {
                                     planet_array_list.add(j.planet(i,"name"));
                                 }
                             
                                 
                           
                             planet_array_list2.add(j.planet(i,"name"));
                             
                              
                          }
                          
                          bcon.setOnClickListener(new OnClickListener() {
                              
                            
                            @Override
                            public void onClick(View arg0) {
                          try
                         
                         {
                           Intent intent = new Intent(Star.this, Staractivity.class);
                             
                         
                           intent.putExtra("constellation",  Constellation.getconstellation(j.planet("star_constellation"),getResources()));
                             intent .putExtra("url", VaroidJSON.url+"star_constellation="+ j.planet("star_constellation")+"&");  
                           
                           
                         startActivity(intent);
                        
                         }
                         catch (Exception e)
                         {
                             e.printStackTrace();
                         }
                    
                           }});
                        
                        
                        bplanet.setOnClickListener(new OnClickListener() {
                              
                            
                            @Override
                            public void onClick(View arg0) {
                                
                              Intent intent = new Intent(Star.this, Planetsun.class);
                                
                             intent.putExtra("ARRAY", planet_array_list);
                             intent.putExtra("ARRAY2", planet_array_list2);  
                             intent.putExtra("SOLARSYSTEM", j.planet("star_name"));
                             intent.putExtra("CON", j.planet("star_constellation"));       
                               startActivity(intent);
                            
                            
                        
                   }});
                        bsolar.setOnClickListener(new OnClickListener() {
                              
                            
                            @Override
                            public void onClick(View arg0) {
                                
                              Intent intent = new Intent(Star.this, Orbit.class);
                                
                              
                             intent.putExtra("STARNAME", j.planet("star_name"));
                                     
                               startActivity(intent);
                            
                            
                        
                   }});
                
               
                }
           
}}