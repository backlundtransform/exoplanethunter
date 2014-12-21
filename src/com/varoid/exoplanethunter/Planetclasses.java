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

import java.util.HashMap;
import java.util.Map;



import android.content.res.Resources;






public class Planetclasses{

	public Planetclasses(){

		
		}
	
	 
	 public static String getData(String data, Resources res) {
		   	String nodata =res.getString(R.string.nodata);
	 
		 if(data.equals(""))
		 {
			 return nodata;
		 }
		 return data;
	 }
	 
	 
	 public static String getData(String data, String myData, Resources res) {
		   	String nodata =res.getString(R.string.nodata);
			 
		 if(data.equals(""))
		 {
			 return nodata;
		 }
		 return myData;
	 }
	 
	 
	 
	 
   public static Map<String, String> getplanetdiscovery(Resources res) {
   	String[]  Array =res.getStringArray(R.array.discinfo);
	   
		   	  Map<String, String> disc= new HashMap<String, String>();
		   
		   	disc.put("radial velocity", Array[0] );
		   	disc.put("transit", Array[1]);
		   	disc.put("microlensing",Array[2]);
		    disc.put("imaging", Array[3]);
		    
		   	disc.put("astrometry",Array[4]);
		   	disc.put("pulsar", Array[5]);
		  	disc.put("", "" );
		   
				return disc;

		} 
	 
	
	   public static Map<String, String> getplanetatmos(Resources res) {
		   	String[]  Array =res.getStringArray(R.array.atmosinfo);
		   	  Map<String, String> atmos= new HashMap<String, String>();
		   
		   	atmos.put("hydrogen-rich", " " +Array[0]);
		   	atmos.put("metals-rich", " " +Array[1]);
		   	atmos.put("none", " " +Array[2]);
		   	atmos.put("","" );
				return atmos;

		} 
		   

		 public static String explore(double right_ascension, double declination) {
			
		return "star_RA:gt="+(right_ascension/15-1)+"&star_RA:lt="+(right_ascension/15+1)+"&star_DEC:gt="+(declination-10)+"&star_DEC:lt="+(declination+10)+"&";
		 }
	 
	 
	 public static Map<String, String> getplanetcomp(Resources res) {
		   	String[]  Array =res.getStringArray(R.array.compinfo);
	   	  Map<String, String> comp= new HashMap<String, String>();
	   
	   	  comp.put("gas",  " " +Array[0]);
	   	  comp.put("water-gas",  " " +Array[1]);
	   	  comp.put("rocky-water",  " " +Array[2]);
	   	  comp.put("rocky-iron",   " " +Array[3]);
	   	  comp.put("iron",   " " +Array[4]);
	   	  comp.put("", "");
	   	 
			return comp;

	}
	 
	
	 
	 public static Map<String, String> getplanetmass(Resources res) {
		   	String[]  Array =res.getStringArray(R.array.massinfo);
   	  Map<String, String> mass = new HashMap<String, String>();
   
   	  mass.put("Mercurian", Array[0]);
   	  mass.put("Terran",Array[1]);
   	  mass.put("SubTerran",Array[2]);
   	  mass.put("Superterran", Array[3]);
   	  mass.put("Neptunian", Array[4]);
   	  mass.put("Jovian", Array[5]);
   	 mass.put("", "");
     
		return mass;

} 
	 		
  
	 public static Map<String, Integer> getplanetpic(String zone, String atmos) {
	   	  Map<String, Integer> img = new HashMap<String, Integer>();
	   	  img.put("", R.drawable.unknownplanet);
	   	  img.put("Mercurian",R.drawable.iron);
	   	  img.put("Neptunian", R.drawable.neptune);
	   	  
	   	if(atmos.equals("no-atmosphere")){
	   		img.put("Terran", R.drawable.iron);
		   	  img.put("SubTerran",R.drawable.iron );
		   	  img.put("Superterran", R.drawable.iron);
		   	img.put("Jovian", R.drawable.jovian);
	   	}
	   	else
	   	{
	   	 
	   	  if(zone.equals("Hot")){
	   		img.put("Terran", R.drawable.hotstone);
		   	  img.put("SubTerran",R.drawable.hotstone );
		   	  img.put("Superterran", R.drawable.hotsuperearth);
		   	  img.put("Jovian", R.drawable.hotjupiter);  
	   		  
	   	  }else if(zone.equals("Cold")){
	   	  img.put("Terran", R.drawable.coldstone);
	   	  img.put("SubTerran",R.drawable.coldstone );
	   	  img.put("Superterran", R.drawable.coldsuperearth);
	   	  img.put("Jovian", R.drawable.jovian);}
	   	  else{
	   		  
	   		  img.put("Terran", R.drawable.stone);
		   	  img.put("SubTerran",R.drawable.stone );
		   	  img.put("Superterran", R.drawable.superearth);
		   	  img.put("Jovian", R.drawable.jovian);
	   	  }
	   	}
			return img;

	} 
	 
	 
	 public static Map<String, String> getplanetcolor(String zone, String atmos) {
	   	  Map<String, String> color = new HashMap<String, String>();
	   	color.put("", "#coldjovian");
	   	color.put("Mercurian","#moon");
	   	color.put("Neptunian", "#neptunian");
	   	  
	   	if(atmos.equals("no-atmosphere")){
	   		color.put("Terran", "#moon");
	   		color.put("SubTerran","#moon" );
	   		color.put("Superterran", "#moon");
		   
	   	}
	   	else
	   	{
	   	 
	   	  if(zone.equals("Hot")){
	   		color.put("Terran", "#hotearth");
	   		color.put("SubTerran","#hotearth");
	   		color.put("Superterran", "#hotearth");
	   		color.put("Jovian", "#hotjovian");  
	   		  
	   	  }else if(zone.equals("Cold")){
	   		color.put("Terran", "#coldearth");
	   		color.put("SubTerran","#coldearth" );
	   		color.put("Superterran", "#coldearth");
	   		color.put("Jovian", "#coldjovian");}
	   	  else{
	   		  
	   		color.put("Terran", "#earth");
	   		color.put("SubTerran","#earth" );
	   		color.put("Superterran", "#earth");
	   		color.put("Jovian", "#hotjovian");
	   	  }
	   	}
			return color;

	} 
	  
	    
	 
	 public static Map<String, String> getSearchdiscovery(Resources res) {
	    	String[]  Array = res.getStringArray(R.array.disc_arrays);
	   	  Map<String, String> Searchdisc= new HashMap<String, String>();
	   	Searchdisc.put(Array[0], "");
	   	Searchdisc.put(Array[1],"disc_method=detected+by+radial+velocity&");
	   	Searchdisc.put(Array[2],"disc_method=detected+by+transit&");
	   	Searchdisc.put(Array[3], "disc_method=detected+by+microlensing&");
	   	Searchdisc.put(Array[4], "disc_method=detected+by+astrometry&");
	   	Searchdisc.put(Array[5],"disc_method=pulsar&" );
	   	Searchdisc.put(Array[6], "disc_method=detected+by+imaging&" );
	   
			return Searchdisc;

	} 
	 
	
	 
	    public static Map<String, String> getSearchmass(Resources res) {
	    	String[]  Array = res.getStringArray(R.array.mass_arrays);
	    	  Map<String, String> searchmass = new HashMap<String, String>();
	    	  searchmass.put(Array[0], "");
	    	  searchmass.put(Array[1], "massclass=Jovian&");
	    	  searchmass.put(Array[2],"massclass=Neptunian&");
	    	  searchmass.put(Array[3], "massclass=Superterran&");
	    	  searchmass.put(Array[4], "massclass=Terran&");
	    	  searchmass.put(Array[5], "massclass=Mercurian&");
	        
			return searchmass;

}
	    
	    public static Map<String, String> getSearchcomp(Resources res) {
	    	String[]  Array =res.getStringArray(R.array.comp_arrays);
	    	Map<String, String> searchcomp = new HashMap<String, String>();
	    	searchcomp.put(Array[0], "");
	    	searchcomp.put(Array[1], "compositionclass=gas&");
	    	searchcomp.put(Array[2], "compositionclass=water-gas&");
	    	searchcomp.put(Array[3],"compositionclass=rocky-water&");
	    	searchcomp.put(Array[4], "compositionclass=rocky-iron&");
	    	searchcomp.put(Array[5], "compositionclass=iron&");
	        
			return searchcomp;
					
					

}
	    
	    public static Map<String, String> getSearchlightyear(Resources res) {
	    	String[]  Array =res.getStringArray(R.array.lightyears_arrays);
	    	
	    	Map<String, String> searchlightyear = new HashMap<String, String>();
	    	searchlightyear.put(Array[0], "");
	    	searchlightyear.put(Array[1], "star_distance:lt=6.25&");
	    	searchlightyear.put(Array[2], "star_distance:lt=62.5&");
	    	searchlightyear.put(Array[3],"star_distance:lt=625&");
	    	searchlightyear.put(Array[4], "star_distance:lt=6250&");
	    	
	  
	    	
			return searchlightyear;
					
					

}
	    public static Map<String, String> getSearchatmos(Resources res) {
	    	String[]  Array =res.getStringArray(R.array.atmos_arrays); 
	    	Map<String, String> searchatmos = new HashMap<String, String>();
	    	searchatmos.put(Array[0], "");
	    	searchatmos.put(Array[1], "atmosphereclass=metals-rich&");
	    	searchatmos.put(Array[2], "atmosphereclass=hydrogen-rich&");
	    	searchatmos.put(Array[3],"atmosphereclass=no-atmosphere&");
	    	
			return searchatmos;
					
			

}
	    public static Map<String, String> getSearchtemp(Resources res) {
	    	String[]  Array =res.getStringArray(R.array.temp_arrays); 
	    	Map<String, String> searchtemp = new HashMap<String, String>();
	    	searchtemp.put(Array[0], "");
	    	searchtemp.put(Array[1], "zoneclass=Hot&");
	    	searchtemp.put(Array[2], "zoneclass=Cold&");
	    	searchtemp.put(Array[3],"zoneclass=Varm&");
	    	
			return searchtemp;
					
}
	    
	    
	    
	    public static Map<String, String> getSortfield(Resources res) {
	    	String[]  Array =res.getStringArray(R.array.sort_arrays); 
	    	Map<String, String> sortfield = new HashMap<String, String>();
	    	sortfield.put(Array[0], "name");
	    	sortfield.put(Array[1], "star_distance");
	    	sortfield.put(Array[2],"habitable");
	    	sortfield.put(Array[3], "mass");
	    	sortfield.put(Array[4],"disc_year");
	    	
			return sortfield;
					
}
	   public static Map<String, String> getSort(Resources res) {
	    	
	    	Map<String, String> sort = new HashMap<String, String>();
	    	sort.put(res.getString(R.string.des), "desc");
	    	sort.put(res.getString(R.string.asc), "asc");
	    	
	    	
	    	
			return sort;
					
}   
	    public static String getPlanetHZA(String hza_, String atmosphere, Resources res) {
	    	String[]  Array =res.getStringArray(R.array.HZA); 
	    	HZ hza = new HZ(hza_);
	    	hza.alternatives = new HashMap<String, String>();
	    	hza.alternatives.put("mitt" ," " +Array[0] + atmosphere);
	    	hza.alternatives.put("too_close" , " " +Array[1]);
	    	hza.alternatives.put("far_away" ," " +Array[2] + atmosphere);
	    	hza.alternatives.put("does not exist" ,"");
	    	
	    	
	    	return hza.getVal(hza.alternatives);
	    }
	    
	    
	    public static String getPlanetHZD(String hzd_, Resources res) {
	    	String[]  Array =res.getStringArray(R.array.HZD); 
	    	HZ hzd = new HZ(hzd_);
	    	hzd.alternatives = new HashMap<String, String>();
	    	hzd.alternatives.put("mitt" , " " +Array[0]);
	    	hzd.alternatives.put("too_close" , " " +Array[1]);
	    	hzd.alternatives.put("far_away" , " " +Array[2]);
	    	hzd.alternatives.put("does not exist" ,"");
	    	
	    	return hzd.getVal(hzd.alternatives);
	    }
	  
	   
}