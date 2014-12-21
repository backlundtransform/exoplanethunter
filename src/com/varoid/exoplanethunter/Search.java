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



import android.content.Intent;
import android.os.Bundle;



import android.widget.Button;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class Search extends SherlockActivity{
	 private Spinner massclass;
	 private Spinner comclass;
	 private Spinner atmosclass;
	 private Spinner tempsclass;
	 private Spinner sort;
	 private Spinner disc;
	 private Spinner light;
	 private RadioGroup radioSort;

	  private RadioButton radioSortButton;
	  String bild="";
	 Button search;
	 protected void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	  getSupportActionBar().setTitle(R.string.search);
	      	setContentView(R.layout.search);
	      	massclass = (Spinner) findViewById(R.id.masspinner);
	      	comclass = (Spinner) findViewById(R.id.Sammansattningspinner);
	      	atmosclass = (Spinner) findViewById(R.id.Atmosfarspinner);
	      	tempsclass  = (Spinner) findViewById(R.id.tempspinner);
	      	sort  = (Spinner) findViewById(R.id.spinner1);
	      	disc = (Spinner) findViewById(R.id.discspinner);
	      	light = (Spinner) findViewById(R.id.lightyearspinner);
	      	
	    
	      	
	      	
	      	radioSort = (RadioGroup) findViewById(R.id.radiosort);
	    	
			int selectedId = radioSort.getCheckedRadioButtonId();
   			 
			
			radioSortButton = (RadioButton) findViewById(selectedId);
	      	
	    
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
				
			
    		
    			
            		
             		
    			
     	         Intent intent = new Intent(Search.this,  com.varoid.exoplanethunter.MainActivity.class);
     	             
     	        
     	           
     	         intent .putExtra("url", VaroidJSON.url+ Planetclasses.getSearchmass(getResources()).get(massclass.getSelectedItem().toString())+Planetclasses.getSearchcomp(getResources()).get(comclass.getSelectedItem().toString())+Planetclasses.getSearchatmos(getResources()).get(atmosclass.getSelectedItem().toString())+Planetclasses.getSearchtemp(getResources()).get(tempsclass.getSelectedItem().toString())+Planetclasses.getSearchdiscovery(getResources()).get(disc.getSelectedItem().toString())+Planetclasses.getSearchlightyear(getResources()).get(light.getSelectedItem().toString())+"sort=["+Planetclasses.getSortfield(getResources()).get(sort.getSelectedItem().toString())+":"+Planetclasses.getSort(getResources()).get(radioSortButton.getText().toString())+"]&");	 
     	           
     	           
     	             
     	         startActivity(intent);
     	        
     	        
			
			case R.id.back:
				
				
				finish();
		        
		       
		       
				return true;
			
		}
			return false;
		}
}
