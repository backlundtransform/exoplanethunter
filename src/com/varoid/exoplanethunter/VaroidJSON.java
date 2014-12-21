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

public class VaroidJSON  extends JSONParser{
	
	 String planetJSON = "";
	 
	 
	    public static String url = "http://exoplanethunter.com/planets/search?";
		
	    public static String numberurl = "http://exoplanethunter.com/planets/number";
	    public static String findurl = "http://exoplanethunter.com/planets/find";
		public static String[]habarray = {
       	url+"habitable=1&", url+"habitableclass=psychroplanet&",url+"habitableclass=mesoplanet&" 
          , url+"habitableclass=thermoplanet&",
            url+"esi:gt=0.7&",
            url+"sph:gt=0&",
            url+"habmoon=1&"
 };  
 
	  
	    
	    
	    public VaroidJSON()
	    {
	        super();
	        
	    }
	    
	    
	    
	    public String planet(int index, String value)
	    {
	       
	    	this.planetJSON = this.ret;
	        this.planetJSON = this.getJSONArray(index, this.planetJSON);
	        this.planetJSON = this.getJSON(value, this.planetJSON);
	        
	        return this.planetJSON;
	    }
	    
	    public String planet(String value)
	    {
	       return this.planet(0, value);
	   }

}

