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
import android.content.res.Resources;

public class ExoString {
	public static String correct;
	
	public static String checkstring(String data, String correct ){
        
	      if(!data.equals(""))
	      {
	      return correct;
	      }
	      else
	      {
	          
	     return "";
	      }
	      }

	       
	public static Float floatvalue(String data){
        
	      if(!data.equals(""))
	      {
	      return Float.valueOf(data);
	      }
	      else
	      {
	          
	      return Float.valueOf("0");
	      }
	      } 
	        
	public static Float floatsize(String radius){
        
	      if(!radius.equals(""))
	      {
	      return Float.valueOf(radius);
	      }
	      else
	      { 
	    return Float.valueOf("1");
	      }
		}
	public static Float Planetspeed(float period, float meandist){
        
	      if(period==0)
	      {
	      return (float) 1;
	      }
	      else
	      {
	          
	      return (float) (0.761*Math.PI*meandist*160/(period));
	      }
	      }
	    
	


	  public static String decFormat(String data)
	  {
		  
		  correct =String.format("%.2f", floatvalue(data));
		 return checkstring(data, correct );
				 
				 }
	  
	  
	  
	  public static String decMean(String data, Resources res) {
	    	String[]  Array =res.getStringArray(R.array.decMean); 
	  
		  correct =" " + Array[0]+" " +String.format("%.2f", floatvalue(data))+" " +Array[1];
			
		
		  return checkstring(data,correct);
	  }
	  
	 
	 
	  
	  public static String decFormattemp(String data)
	  {
		  
		   correct = String.format("%.2f", floatvalue(data)-272.15);
		
			  return checkstring(data, correct);
		 
	  }
	  
	  public static String decFormatdist(String data, Resources res) {
	    	String[]  Array =res.getStringArray(R.array.decFormatdist);
	
		 correct =  " " +Array[0]+" " +String.format("%.2f", floatvalue(data)* 3.26)+" " +Array[1];
		  return checkstring(data, correct);
		 
	  }
	  
	  public static String removedec(String data)
	  {
		  
		   correct = String.format("%.0f", floatvalue(data));
		
			  return checkstring(data, correct);
		 
	  }
	  
	  
	  public static String name(String planetname,String starname, String constellation, Resources res) {
	    	String[]  Array =res.getStringArray(R.array.planetname);
		  
		   correct = Array[0]+" " +planetname+" " +Array[1] +" " +starname+" " +
				   Array[2]+" " +constellation;
		 
		   return checkstring(planetname, correct);
			 
	  }
	   
	   public static String name(String starname,String constellation, Resources res) {
	    	String[]  Array = res.getStringArray(R.array.starname);
			  
		   correct = Array[0]+" " +starname+" " +Array[1]+" " +constellation;
		   return checkstring(starname, correct);	  
			
		  }

	  
	  public static String orbit(String data, Resources res) {
	    	String[]  Array = res.getStringArray(R.array.orbit);
	  
		  correct = Array[0] +" " +data+" " +
				  Array[1];
		  return checkstring(data, correct);
	  }
	  
	 
	  public static String disc(String data, Resources res) {
	    	String  string = res.getString(R.string.disc);
	  
		  correct = System.getProperty ("line.separator")
	      +System.getProperty ("line.separator")
	      +string+" " + data+". ";
		  return checkstring(data, correct);
	  }
	  
	  public static String mag(String data, Resources res) {
	    	String[]  Array = res.getStringArray(R.array.mag);
	  
		 
		      if(floatvalue(data)<7){
		    	  
		    	  correct=Array[0];
		    	  
		       return  checkstring(data, correct);
		          
		      }else if(floatvalue(data)>7){
		    	  correct=Array[1];
		    	 return  checkstring(data, correct);
		      }
		 
		return "";

	  }
	  
	  
	  public static String moon(String string, Resources res) {
	    	String  moon = res.getString(R.string.moon);  
	    	
	    	if(string.equals("1")){
		  return moon;
		  }
		  else{
			  return "";
		  }
	  }
	  
	  
	  public static String meantemp(String min,String max,String planetname, Resources res) {
	    	String[]  Array = res.getStringArray(R.array.meantemp);
		  
	 
		 if(min.equals(max)){
			 
			 correct=System.getProperty ("line.separator")
			              +System.getProperty ("line.separator")
			              +" " +Array[0]+" " + max+" °C ";
			  return  checkstring(min, correct);
		      
			
		  }
		  else{
			  correct=System.getProperty ("line.separator")
	              +System.getProperty ("line.separator")
	              +" " +Array[1]+" " + planetname +" " +Array[2]+" " + max+" " +Array[3]+" " + planetname+" " +Array[4]+" " + min+" °C.";
			  return  checkstring(min, correct);
		  }
	  }
	  
	  
	  
	  
	
	
	  public static String habzone(String min,String max, Resources res) {
	    	String[]  Array = res.getStringArray(R.array.habzone);  
	    	correct = Array[0]+" " + decFormat(min)+" " +Array[1]+" " +decFormat(max)+" " +Array[2]; 
		  return  checkstring(min, correct);
	  }
	  public static String numberplanets(String planet, String hab, Resources res) {
	    	String[]  Array = res.getStringArray(R.array.numberplanets);
		  if(floatvalue(planet)== 1 && floatvalue(hab)== 1){
			  
			  correct = Array[0];
			  return  checkstring(planet, correct);
			 
			
		  }
		  else if(floatvalue(planet)> 1 && floatvalue(hab)> 1)
		  {
			  
			  correct =Array[1]+" " +planet+" " +Array[2]+" " +hab+" " +Array[3];
			  return  checkstring(planet, correct);
		 } 
		  else if(floatvalue(planet)> 1 && floatvalue(hab)==0){
			  
			  correct = Array[1]+" " +planet+" " +Array[4];
			  return  checkstring(planet, correct);
		  }
		  else if(floatvalue(planet)== 1 && floatvalue(hab)==0){
			  
			  correct =Array[5];
			  return  checkstring(planet, correct);
		  }
		  else
		  {
			     return " ";
	  }
	  
	  } 
}   
