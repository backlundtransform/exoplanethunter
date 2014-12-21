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
import java.util.HashMap;
import java.util.List;

import com.actionbarsherlock.app.SherlockActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class Menu extends SherlockActivity
{
	 
	
	 private String[] menuClassNames = { 
	    		MainActivity.class.getName(), 
	    		Habitable.class.getName(), 
	    		Constellationlist.class.getName(),
	    		GPS.class.getName(),  
	    		Search.class.getName(), 
	    		
	    	};
	    
	  
	    
	    int[] icon = new int[]{
	    		 R.drawable.gasplanet,
	             R.drawable.jordplanet,
	             R.drawable.star,
	             R.drawable.map,
	             R.drawable.search,
	    	      
	        };
	    TextView vy;
	    String number;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.menu);
        	number = getIntent().getExtras().getString("number");
        	getSupportActionBar().setTitle(getResources().getString(R.string.number)+": "+number);
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
        final String[] menuItems = getResources().getStringArray(R.array.menu); 
        for(int i=0;i<5;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", menuItems[i]);
            
            hm.put("icon", Integer.toString(icon[i]));
            aList.add(hm);
        }
 
        String[] from = { "icon","txt" };
 
       
        int[] to = { R.id.icon,R.id.txt};
 
     
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.list, from, to);
 
       
        ListView listView = ( ListView ) findViewById(R.id.lista);
 
        listView.setAdapter(adapter);
        
 
      
        OnItemClickListener itemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
            	try
                {
                    Intent intent = new Intent(Menu.this, Class.forName(menuClassNames[position]));
                    
                    if(position==0){
                    intent .putExtra("url", VaroidJSON.url);
                    }
                   
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
 
    
        listView.setOnItemClickListener(itemClickListener);
    }
 
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, (android.view.Menu) menu);
        return true;
    }
}
	
	
	
	
	
	
	
    

