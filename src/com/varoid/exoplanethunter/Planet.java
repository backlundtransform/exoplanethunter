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




import android.app.ProgressDialog;
import android.content.Intent;



import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;


import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;


public class Planet extends SherlockActivity {
	VaroidJSON j;
    
    String name, planetname, distance, image, 
    esitext, sphtext, massa, radius, gravity, 
    density,  constellation, composition,atmosphere, orbit,comp,zon,
    moon, year, disc, ts_min,ts_mean,ts_max, ts, atmos, atmosclass, massclass,planetimage, meandist,flykt,tryck;
    TextView vy, massrow, radiusrow,  densityrow, gravrow, info,flyktrow, tryckrow;
    
    ImageView img;
    RatingBar esi, sph;
    ImageButton  visitstar, bsolar,bcon;
    ProgressDialog dialog;
	
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
    
         super.onCreate(savedInstanceState);

         	dialog = new ProgressDialog(this);
		    dialog.setCancelable(true);
		    dialog.setMessage(getResources().getString(R.string.somethingelse));
		    dialog.show();
         
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
				Intent intent = new Intent(Planet.this, com.varoid.exoplanethunter.Splash.class);
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
               
                   planetname = getIntent().getExtras().getString("PLANETNAME");

                try {
                    planetname = URLEncoder.encode(planetname, "ISO-8859-1");
                
                    
                } catch (UnsupportedEncodingException e1) {
                    
                    e1.printStackTrace();
                } 
               
                
                
                
              String url=VaroidJSON.url+"fields=[name,star_name,star_distance,disc_year,esi,sph,mass,radius,period,gravity,density,star_constellation,zoneclass,compositionclass,hzd,habmoon,disc_method,tsmin,tsmean,tsmax,hza,atmosphereclass,massclass,meandistance,escvel,surfpress]&name="+planetname;
            
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
            	   getSupportActionBar().setTitle(j.planet("name"));
                   setContentView(R.layout.planet);
                   vy = (TextView) findViewById(R.id.Text1);
                   bsolar = (ImageButton) findViewById(R.id.bviewsolarsystem);
                   bcon = (ImageButton) findViewById(R.id.bconst);
                   info = (TextView) findViewById(R.id.info);
                   massrow  = (TextView) findViewById(R.id.textView4);
                   
                   
                   radiusrow  = (TextView) findViewById(R.id.textView6);
                   densityrow  = (TextView) findViewById(R.id.textView9);
                   gravrow  = (TextView) findViewById(R.id.textView10);
                   visitstar = (ImageButton) findViewById(R.id.starbutton);
                   flyktrow  = (TextView) findViewById(R.id.flykt);
                   tryckrow  = (TextView) findViewById(R.id.tryck);
                   esi=(RatingBar) findViewById(R.id.ratingBar1);
                   sph=(RatingBar) findViewById(R.id.ratingBar2);
                   esi.setEnabled(false);
                   sph.setEnabled(false);
                   img = (ImageView) findViewById(R.id.imageView1);
                   massclass = Planetclasses.getplanetmass(getResources()).get(j.planet("massclass"));
                   planetimage = j.planet("massclass");
                   composition = Planetclasses.getplanetcomp(getResources()).get(j.planet("compositionclass"));
                   atmosclass  = Planetclasses.getplanetatmos(getResources()).get(j.planet("atmosphereclass"));
                   disc = Planetclasses.getplanetdiscovery(getResources()).get(j.planet("disc_method"));
                   name =  ExoString.name(j.planet("name"),j.planet("star_name"),Constellation.getconstellation(j.planet("star_constellation"),getResources()),getResources());
                   distance = j.planet("star_distance");
                   massa =  j.planet("mass");
                   radius = j.planet("radius");
                   gravity = j.planet("gravity");
                   density = j.planet("density");
                   flykt = j.planet("escvel");
                   tryck = j.planet("surfpress");
                   orbit = j.planet("period");
                  
                   ts_min =  j.planet("tsmin");
                   ts_max =  j.planet("tsmax");
                   meandist =  j.planet("meandistance");
               
                   zon = Planetclasses.getPlanetHZD(j.planet("hzd"), getResources());
                   atmos = Planetclasses.getPlanetHZA(j.planet("hza"), atmosclass, getResources());
                   meandist= ExoString.decMean(meandist,getResources()); 
                   moon=ExoString.moon(j.planet("habmoon"),getResources());
                   ts_max=ExoString.decFormattemp(ts_max);
                   ts_min=ExoString.decFormattemp(ts_min);
                   year=ExoString.disc(ExoString.removedec(j.planet("disc_year")),getResources());
                   distance = ExoString.decFormatdist(distance,getResources());
                   orbit = ExoString.orbit(orbit,getResources());
                   ts =  ExoString.meantemp(ts_min,ts_max,j.planet("name"),getResources());
                   sphtext = j.planet("sph");
                   esitext= j.planet("esi");
                   Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_SHORT);
                  
                   if (name.equals("")){
                	   
                	   toast.setGravity(Gravity.CENTER, 0, 0);
                       toast.show();
                         
                   }
                   
                   
                   
               
                	   img.setImageResource(Planetclasses.getplanetpic(j.planet("zoneclass"),j.planet("atmosphereclass")).get(j.planet("massclass")));
                     
                   
                vy.setText(name+distance+System.getProperty ("line.separator")
                   +System.getProperty ("line.separator")
                   +massclass+ composition  +System.getProperty ("line.separator")
                   + System.getProperty ("line.separator") +orbit + meandist+ zon+ atmos+moon + ts + year + disc
               
                             );
      
                        massrow.setText(Planetclasses.getData(massa, massa+"*" +getResources().getString(R.string.earth), getResources()));
                        radiusrow.setText(Planetclasses.getData(radius, radius+"*" +getResources().getString(R.string.earth), getResources()));
                        densityrow.setText(Planetclasses.getData(density, density+"*" +getResources().getString(R.string.earth), getResources()));
                        gravrow.setText(Planetclasses.getData(gravity, gravity+"*" +getResources().getString(R.string.earth), getResources()));
                        tryckrow.setText(Planetclasses.getData(tryck, tryck+"*" +getResources().getString(R.string.earth), getResources()));
                        flyktrow.setText(Planetclasses.getData(flykt, flykt+"*" +getResources().getString(R.string.earth), getResources()));
      
                        esi.setRating(ExoString.floatvalue(esitext)*5);
                        sph.setRating(ExoString.floatvalue(sphtext)*5);
                        
                        bsolar.setOnClickListener(new OnClickListener() {
                              
                            
                            @Override
                            public void onClick(View arg0) {
                                
                                Intent intent = new Intent(Planet.this, Orbit.class);
                                  
                                
                               intent.putExtra("STARNAME", j.planet("star_name"));
                                       
                                 startActivity(intent);
                            
                            
                        
                   }});
                        
                        
                        bcon.setOnClickListener(new OnClickListener() {
                            
                            
                            @Override
                            public void onClick(View arg0) {
                          try
                         
                         {
                           Intent intent = new Intent(Planet.this, Staractivity.class);
                           intent.putExtra("constellation",  Constellation.getconstellation(j.planet("star_constellation"),getResources()));
                           intent .putExtra("url", VaroidJSON.url+"star_constellation="+ j.planet("star_constellation")+"&");  
                           
                           
                         startActivity(intent);
                        
                         }
                         catch (Exception e)
                         {
                             e.printStackTrace();
                         }
                    
                           }});
                
                        visitstar.setOnClickListener(new OnClickListener() {
                              

                                @Override
                                public void onClick(View arg0) {
                                    Intent intent = new Intent(Planet.this, Star.class);
                                    
                                    intent.putExtra("STARNAME",  j.planet("star_name"));
                                    
                                    
                                 startActivity(intent);
                                }
                                }
                           );   
                    }
            	  
           

        }
    
    
    } 
	
	

