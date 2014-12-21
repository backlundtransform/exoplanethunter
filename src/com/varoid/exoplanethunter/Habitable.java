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


import com.actionbarsherlock.view.MenuItem;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;


public class Habitable extends SherlockListActivity
	 {
	     
	     
	     @Override
	     protected void onCreate(Bundle savedInstanceState)
	     {
	         super.onCreate(savedInstanceState);
	         final String[] menuItems =  getResources().getStringArray(R.array.habmenu); 

	         setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItems));
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
	 			
	 			
	 			
	 			Intent intent = new Intent(Habitable.this, com.varoid.exoplanethunter.Menu.class);
	 	       
	 	       
	 	        startActivity(intent);
	 			return true;
	 			
	 		case R.id.back:
	 			
	 			
	 			finish();
	 	        
	 	       
	 	       
	 			return true;
	 		
	 	}
	 		return false;
	 	}


	     @Override
	     protected void onListItemClick(ListView l, View v, int position, long id)
	     {
	         super.onListItemClick(l, v, position, id);
	         try
	         {
	             Intent intent = new Intent(this, MainActivity.class);
	             
	           intent .putExtra("url", VaroidJSON.habarray[position]);	 
	           
	           
	             startActivity(intent);
	         }
	         catch (Exception e)
	         {
	             e.printStackTrace();
	         }
	         
	     }
	 }
	 


