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

import java.util.Map;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListActivity;

import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class Constellationlist extends SherlockListActivity{
	
	
	     ArrayList<String> keylist = new ArrayList<String>();
      ArrayList<String> valuelist = new ArrayList<String>();
	     protected void onCreate(Bundle savedInstanceState)
	     {
	         super.onCreate(savedInstanceState);
	      

	        
			for (Map.Entry<String,String> entry : Constellation.getmap(getResources()).entrySet()){
					String key = entry.getKey();
					String value = entry.getValue();
					
					keylist.add(key);
					valuelist.add(value);
	        	 
	        	}
	       
	      setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, valuelist));
	    
	     }
	  protected void onListItemClick(ListView l, View v, int position, long id)
	     {
	         super.onListItemClick(l, v, position, id);
	         try
	         
	         {
	           Intent intent = new Intent(this, Staractivity.class);
	             
	         
	           
	             intent .putExtra("url", VaroidJSON.url+"star_constellation="+ keylist.get(position)+"&");

	             intent .putExtra("constellation", Constellation.getconstellation(keylist.get(position),getResources()));	 
	           
	           
	           
	         startActivity(intent);
	        
	         }
	         catch (Exception e)
	         {
	             e.printStackTrace();
	         }
	         
	     }
	  
	  @Override
	    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
	       MenuInflater inflater = getSupportMenuInflater();
	       inflater.inflate(R.menu.menu, menu);
	       return super.onCreateOptionsMenu(menu);
	    }
		
		@Override
		public boolean onMenuItemSelected(int featureId, MenuItem item) {
			switch (item.getItemId()) {
			
			 
			case R.id.home:
				
				
				
				Intent intent = new Intent(Constellationlist.this, com.varoid.exoplanethunter.Splash.class);
		       
		       
		        startActivity(intent);
				return true;
				
			case R.id.back:
				
				
				finish();
		        
		       
		       
				return true;
			
		}
			return false;
		}
	 }


