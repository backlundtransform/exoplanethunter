/*Copyright G�ran B�cklund gbanm06@gmail.com

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

import android.app.Activity;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;






public class Splash extends Activity{

	
	VaroidJSON j;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		 new LongOperation().execute(""); 
			
		 	
			

		}
		 private class LongOperation extends AsyncTask<String, Void, String> {

	         @Override
	         protected String doInBackground(String... params) {
	         	

	        	 j = new VaroidJSON();
		        	 try
		        	 {
		        		 
		        	  	j.readJSONFeed(VaroidJSON.numberurl);
		         	  }
		        	 catch (Exception e)
		        	 {
		        	   e.printStackTrace();
		        	 }
	        	
	        	 return "";
	         }

	         @Override
	         protected void onPostExecute(String result) {    
	           // Update Ui here 
	        	
	        	
					   String js = "";
	        	
					   js  = j.planet("count");
			           Intent openStartingPoint = new Intent(Splash.this, Menu.class);
			           openStartingPoint .putExtra("number", js);
					   startActivity(openStartingPoint);
			        
	        	}
	         	
	         	
	         }
	}

	


