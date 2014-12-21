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
import java.util.List;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;



public class Orbit extends SherlockActivity  {
	VaroidJSON j;
	Startype startype;
	HZ hz;
	private RadioGroup radio;
	private JScript planets, Ourplanets;
	
	String selected;
	  private RadioButton radio3D, radio2D;
	   String  starname, starcolor;
	   ImageButton bplanet,visitstar,bcon;
	   ArrayList<String> planet_array_list;
	    ArrayList<String> planet_array_list2;
	    ProgressDialog dialog;
		
	   
	    float  sunradius, planetradius,distance, orbit, phi0,speed, meandist, habmax,habmin;
	    int maxradius, width;  
	    WebView mWebView = null;
		
	    public int maxradius(float meandistance)
	    {
	    	return (int) (2*meandistance+100);
	    }
	    
	    
	    
	    
		@Override
		protected void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);

			
			dialog = new ProgressDialog(this);
		    dialog.setCancelable(true);
		    dialog.setMessage(getResources().getString(R.string.somethingelse));
		    dialog.show();
			
			j = new VaroidJSON();
		    planet_array_list = new ArrayList<String>();
	           planet_array_list2 = new ArrayList<String>();
	           
	           
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
				
				
				
				Intent intent = new Intent(Orbit.this, com.varoid.exoplanethunter.Splash.class);
		       
		       
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
					starname=URLEncoder.encode( starname, "ISO-8859-1");
				}
				catch (UnsupportedEncodingException e1)
				{
					e1.printStackTrace();
				} 
				               
				String url=VaroidJSON.url+"fields=[star_name,star_type,name,star_constellation,star_radius,star_type,radius,period,hzd,meandistance,star_no_planets,star_habzonemin,star_habzonemax,period,omega,zoneclass,massclass,atmosphereclass]&star_name="+starname;
				 
				Log.v("TEST", url);  
				try
				{
					j.readJSONFeed(url);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				return j.get_data();
			}      

			@SuppressLint("SetJavaScriptEnabled")
			@Override
			@JavascriptInterface
			protected void onPostExecute(String result) {  
				dialog.cancel();
				setContentView(R.layout.solsystem);
				getSupportActionBar().setTitle(getResources().getString(R.string.solarsystem)+" "+j.planet("star_name"));
			    radio2D=(RadioButton)findViewById(R.id.click2D);
			    radio3D=(RadioButton)findViewById(R.id.click3D);
			    bplanet = (ImageButton) findViewById(R.id.bplanet);
			    visitstar = (ImageButton) findViewById(R.id.starbutton);
			       bcon = (ImageButton) findViewById(R.id.bconst);
			            	  
			    radio=(RadioGroup)findViewById(R.id.radio);
			    List<String> SpinnerArray =  new ArrayList<String>();
			    SpinnerArray.add(starname);
			    SpinnerArray.add(getResources().getString(R.string.oursun));

			    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Orbit.this, android.R.layout.simple_spinner_item, SpinnerArray);
			    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			    final Spinner Items = (Spinner) findViewById(R.id.spinnersystem);
			    Items.setAdapter(adapter);
			   
			    String[]  PlanetNames =getResources().getStringArray(R.array.ourPlanets); 
			    
			   
			    sunradius = (float) Math.abs(Math.log(ExoString.floatsize(j.planet("star_radius"))+1)*60 / Math.log(2));
			Log.v("starradius", String.valueOf(sunradius));
			    startype = new Startype(j.planet("star_type"));
			   starcolor=startype.getHexColor(getResources());
    			habmin =ExoString.floatvalue(j.planet("star_habzonemin"))*160+sunradius;
    			habmax= ExoString.floatvalue(j.planet("star_habzonemax"))*160+sunradius;
    			       
    			planets= new JScript();
    			            	   
    			for(int i=0;  i<ExoString.floatvalue(j.planet("star_no_planets"));i++)
    			{
    				rita_planet(ExoString.floatsize(j.planet(i, "radius")),ExoString.floatvalue(j.planet(i, "meandistance")) ,ExoString.floatvalue(j.planet(i, "period")) ,ExoString.floatvalue(j.planet(i, "omega")),j.planet(i, "name"),planets, sunradius, Planetclasses.getplanetcolor(j.planet(i, "zoneclass"),j.planet(i, "atmosphereclass")).get(j.planet(i, "massclass")));		
    				
    			}
    			            	      
    			planets.end();
    			maxradius = maxradius(meandist);
    			
    			Ourplanets= new JScript();
    			rita_planet((float)0.383,(float)0.4, (float)90,(float)0 ,PlanetNames[0],Ourplanets, 60,"#moon");
    			rita_planet((float)0.95,(float)0.7, (float)210, (float)180, PlanetNames[1],Ourplanets, 60, "#hotearth");				
    			rita_planet((float)1,1, (float)365, (float)70,PlanetNames[2],Ourplanets, 60, "#earth");				
    			rita_planet((float)0.532,(float)1.5,(float)700 ,(float)275,PlanetNames[3],Ourplanets, 60, "#moon");
    			rita_planet((float)10.97,(float)5.2,(float)4380 ,(float)270,PlanetNames[4],Ourplanets, 60, "#coldjovian");
    			rita_planet((float)9.14,(float)9.5, (float)10767,(float)180,PlanetNames[5],Ourplanets, 60, "#coldjovian");
    			rita_planet((float)3.98,(float)19.6,(float)30660,(float)340,PlanetNames[6],Ourplanets, 60, "#neptunian");
    			rita_planet((float)3.86,(float)30,(float)60225,(float)50,PlanetNames[7], Ourplanets, 60, "#neptunian");
    			Ourplanets.end();	
			  
			            
			 Log.v("alienplanets", planets.get());
			 Log.v("ourplanets", Ourplanets.get());
			
			    mWebView = (WebView)findViewById(R.id.web3D);
			    WebSettings webSettings = mWebView.getSettings();   
			    webSettings.setBuiltInZoomControls(true);
			            	                           
			    webSettings.setJavaScriptEnabled(true);
			    webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
			            	     
			    webSettings.setLoadWithOverviewMode(true);
			    mWebView.getSettings().setUseWideViewPort(true);
			    webSettings.setSupportMultipleWindows(true);
			    webSettings.setLoadsImagesAutomatically(true);
			  
			    webSettings.setDomStorageEnabled(true);
			   
			    mWebView.addJavascriptInterface(new JavaScriptInterface(getApplicationContext()), "Android");     	     
			    mWebView.setInitialScale(150);
			            	     
			   mWebView.setScrollbarFadingEnabled(false);
			       	      
			   mWebView.clearHistory();
			   mWebView.clearFormData();
			   mWebView.clearCache(true);
			   
			 

		
			   radio3d(starname);
			  
			   
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
			                           Intent intent = new Intent(Orbit.this, Staractivity.class);
			                             
			                         
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
			                                    Intent intent = new Intent(Orbit.this, Star.class);
			                                    
			                                    intent.putExtra("STARNAME",  j.planet("star_name"));
			                                    
			                                    
			                                 startActivity(intent);
			                                }
			                                }
			                           );   



			bplanet.setOnClickListener(new OnClickListener() {
			                              
			                            
			                            @Override
			                            public void onClick(View arg0) {
			                                
			                              Intent intent = new Intent(Orbit.this, Planetsun.class);
			                                
			                             intent.putExtra("ARRAY", planet_array_list);
			                             intent.putExtra("ARRAY2", planet_array_list2);  
			                             intent.putExtra("SOLARSYSTEM", j.planet("star_name"));
			                             intent.putExtra("CON", j.planet("star_constellation"));        
			                               startActivity(intent);
			                            
			                            
			                        
			                   }});
			
			   
			   Items.setOnItemSelectedListener(new OnItemSelectedListener()
				{
								    	@Override
								        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
								    		selected = Items.getSelectedItem().toString();
								    		
								    		if(radio2D.isChecked())
											{ 
													radio2d(selected);
							            	}
								            	else if(radio3D.isChecked())
								            	{
								            		radio3d(selected);
								            		  
								            	}
								    		Log.v("onItemSelected", selected);
								        }

										@Override
										public void onNothingSelected(AdapterView<?> arg0)
										{
											
										}
								    });
			            	      
			            	      
			            		  
		radio.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
					if(radio2D.isChecked())
					{ 
							radio2d(selected);
	            	}
		            	else if(radio3D.isChecked())
		            	{
		            		radio3d(selected);
		            		  
		            	}
		            
		            	}
					 
				});
		}
				            	
	}
		  public void radio2d(String selected)
		    {
		        if (selected.equals(getResources().getString(R.string.oursun)))
		        { 
		            mWebView.loadDataWithBaseURL("file:///android_asset/", display_2d_js(maxradius(meandist), Ourplanets.get(), (float)(3.0*160+60) ,(float)(0.725*160+60), 60, "#FFF000"), "text/html", "utf-8", null);   
		             
		        }
		        else if(selected.equals(starname))
		        {
		            mWebView.loadDataWithBaseURL("file:///android_asset/", display_2d_js(maxradius(meandist), planets.get(), habmax, habmin, sunradius, starcolor), "text/html", "utf-8", null);   
		        }
		        else
		        {
		            Log.v("unkown_select", selected);
		        }
		    }
		        
		   public void radio3d(String selected)
		    {
		        
		        if (selected.equals(getResources().getString(R.string.oursun)))
		        {
		            mWebView.loadDataWithBaseURL("file:///android_asset/", display_3d_js(maxradius(meandist), Ourplanets.get(), 60, "#FFF000"), "text/html", "utf-8", null);
		        }
		        else if(selected.equals(starname))
		        {
		            mWebView.loadDataWithBaseURL("file:///android_asset/", display_3d_js(maxradius(meandist), planets.get(), sunradius, starcolor), "text/html", "utf-8", null);
		        }
		        else
		        {
		            Log.v("unkown_select", selected);
		        }
		    }
	
	
	public void rita_planet(float _radie, float _meandistance, float _period, float _vinkel, String _Name, JScript addplanet, float _sunradius, String _planetcolor )
	{
		planetradius = (float)Math.abs((Math.log(_radie+2) / Math.log(3))*8);	
		
    	meandist=  _meandistance*160+_sunradius+planetradius; 
    	speed=ExoString.Planetspeed(_period,_meandistance);    
    	addplanet.addtext("{ R:"+meandist+", r:" +  planetradius  +", speed:" + speed +", phi0:"+ Math.ceil(_vinkel*Math.PI/180) +", Name:'"+ _Name+"', color:'"+ _planetcolor+"'},");
	}



	

public String display_3d_js(int _maxradius, String _jtext, float _sunr, String  _starcolor)
{
    String s_maxr = String.valueOf(_maxradius).toString();
    
    String center = String.valueOf(_maxradius/2).toString();
   
    String _ret = "<meta content='width=device-width, " +
            "initial-scale=1.0, maximum-scale=1.0, " +
            "user-scalable=0;' name='viewport' />" +
            "<link href='planet.css' type='text/css' rel='stylesheet' />" +
            "<script type=text/javascript src='d3.v3.min.js'></script>" +
            "<script type='text/javascript' src='planetscript.js'>" +
            "</script><body> <div id='planetarium'></div>" +
            "<script >  var w = "+s_maxr+
            ", h = "+s_maxr+"; var planets = " 
            +_jtext+"; var sunradius = "+ _sunr +"; " +
                    "var suncolor= '"+ _starcolor +"'; " +
                    " Dplanetsystem(planets, sunradius, suncolor, w, h); window.scrollTo("+center+" - screen.width/2,"+center+" - screen.height/2);" +
                    "</script>" +
            "</body>";
    Log.v("3d_ret", _ret);
    return _ret;
}


public String display_2d_js(int _maxradius, String _jtext, float _habmax, float _habmin, float _sunr, String  _starcolor)
{
    String s_maxr = String.valueOf(_maxradius).toString();
    String center = String.valueOf(_maxradius/2).toString();
    String _ret = "<meta content='width=device-width, " +
            "initial-scale=1.0, maximum-scale=1.0, user-scalable=0;'" +
            " name='viewport' /><link href='planet.css' type='text/css' " +
            "rel='stylesheet' />" +
            "<script type=text/javascript src='d3.v3.min.js'></script>" +
            "<script type='text/javascript' src='planetscript.js'></script>" +
            "<body> <div id='planetarium'></div>" +
            "<script >  var w = "+s_maxr+", h = "+s_maxr+";" +
            " var planets = " +_jtext+"; var sunradius =  "+_sunr+
            "; var habmin = "+ _habmin+";  var habmax ="+_habmax+
            ";  var suncolor= '"+ _starcolor +"'; " +
            " planetsystem(planets,sunradius, habmin, habmax, suncolor, w,h);window.scrollTo("+center+" - screen.width/2,"+center+" - screen.height/2);" +
            "</script></body>";
    Log.v("2d_ret", _ret);
    return _ret;
}

public class JavaScriptInterface {
Context mContext;

JavaScriptInterface(Context c) {
    mContext = c;
}
@JavascriptInterface
public void showToast(String toast) {
    Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
}
}	
		
	class JScript {
		private String p;
		
		public JScript()
		{
			p = "[";
		}
		
		void addtext(String t)
		{
			p = p + t;
		}
		
		void end()
		{
			p = p + "]";
		}
		
		String get()
		{
			return p;
		}
	
		
	}	
	

	 
	 


}


