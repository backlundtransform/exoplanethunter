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

import java.util.ArrayList;



import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class Planetsun extends SherlockActivity{
	ArrayList<String> planet_array_list;
	ArrayList<String> planet_array_list2;
	ListView planetlist;
	String sun,con;
	TextView solar;
	  ImageButton  visitstar, bsolar, bcon;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		 setContentView(R.layout.planetstar);
		 bcon = (ImageButton) findViewById(R.id.bconst);
		 planet_array_list = getIntent().getExtras().getStringArrayList("ARRAY");
		 planet_array_list2 = getIntent().getExtras().getStringArrayList("ARRAY2");
		 sun=getIntent().getExtras().getString("SOLARSYSTEM");
		 con=getIntent().getExtras().getString("CON");
		 planetlist=(ListView) findViewById(R.id.sunplanet);
		 
		 
		 bsolar = (ImageButton) findViewById(R.id.bviewsolarsystem);
      
         visitstar = (ImageButton) findViewById(R.id.starbutton);
		 
		 
		
		
		 getSupportActionBar().setTitle(getResources().getString(R.string.solarsystem)+" "+sun);
		 ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, planet_array_list);
	         planetlist.setAdapter(arrayAdapter); 
	           
	             
	         planetlist.setOnItemClickListener(new OnItemClickListener() {

	         public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	                	
	                	
	              Intent intent = new Intent(Planetsun.this, Planet.class);


	              intent.putExtra("PLANETNAME", planet_array_list2.get(position));
	                     
	                     
				   startActivity(intent);
	                	 
	               } 
 	});
	

	         bcon.setOnClickListener(new OnClickListener() {
                 
                 
                 @Override
                 public void onClick(View arg0) {
               try
              
              {
                Intent intent = new Intent(Planetsun.this, Staractivity.class);
                  
              
                intent.putExtra("constellation",  Constellation.getconstellation(con,getResources()));
                  intent .putExtra("url", VaroidJSON.url+"star_constellation="+ con+"&");  
                
                
              startActivity(intent);
             
              }
              catch (Exception e)
              {
                  e.printStackTrace();
              }
         
                }});
	
	 bsolar.setOnClickListener(new OnClickListener() {
         
         
         public void onClick(View arg0) {
             
             Intent intent = new Intent(Planetsun.this, Orbit.class);
               intent.putExtra("STARNAME", sun);
               startActivity(intent);
         	}});
	 
	 

     visitstar.setOnClickListener(new OnClickListener() {
           

             @Override
             public void onClick(View arg0) {
                 Intent intent = new Intent(Planetsun.this, Star.class);
                 
                 intent.putExtra("STARNAME",  sun);
                 
                 
              startActivity(intent);
             }
             }
        );   
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
			
			
			
			Intent intent = new Intent(Planetsun.this, com.varoid.exoplanethunter.Splash.class);
	       
	       
	        startActivity(intent);
			return true;
			
		case R.id.back:
			
			
			finish();
	        
	       
	       
			return true;
		
	}
		return false;
	}
	

}
