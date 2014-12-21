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



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;


import android.util.Log;


public class JSONParser {
 
    // constructor
	protected String ret = new String("return");
	HttpGet httpGet;
	JSONObject json;
	JSONArray j_arr;

	
    public JSONParser() {
 
     
    }
    
    
    public String getJSONArray(int i, String json_text){
    	try
    	{
    		j_arr = new JSONArray(json_text);
    		JSONObject json_data = j_arr.getJSONObject(i);
    		try
	    	{
    			
	    		return json_data.toString();
	    	}
	    	catch(Exception e)
	    	{
	    		
	    		Log.v("error1", e.getMessage().toString());
	    		
	    		return "";
	    	}
    	}
    	catch(Exception e){
    		Log.v("error2", e.getMessage().toString());
    		return "";
    	}
    	
    }
    
    
    public String getJSON(String obj, String json_text){
    	try
    	{
    		json = new JSONObject(json_text);
    		try
	    	{
    			
	    		return json.getString(obj);
	    	}
	    	catch(Exception e)
	    	{
	    	Log.v("error3", e.getMessage().toString());
	    		return "";
	    	}
    	}
    	catch(Exception e){
    		Log.v("error4", e.getMessage().toString());
    		return "";
    	}
    	
    }
    
    public String get_data()
    {
    	return this.ret;
    }
 
    public void readJSONFeed(String URL) throws Exception {
    	this.ret = "ff";
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        this.ret = "wdsffdfdg";
        
        try
        {
        	
        	httpGet = new HttpGet(URL);
    	}
        catch(IllegalArgumentException e)
        {
        	
        	this.ret = "ucvt589hygf " + e.toString() + e.getMessage();
        }
        this.ret = "wdsfsdedg";
        try {
            HttpResponse response = httpClient.execute(httpGet);

            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
            	this.ret = "wedfgdfgg";
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
            	this.ret = "Failed to download file";
            }
        } catch (Exception e) {
            this.ret = "readJSONFeed" + e.toString();
        }        
        this.ret = stringBuilder.toString();
    }
}
