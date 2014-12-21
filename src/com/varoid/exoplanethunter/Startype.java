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

import com.varoid.exoplanethunter.R;

import android.content.res.Resources;

public class Startype {

    String type;
    //int bild;

    
    public Startype(String a_type)
    {
        this.type = a_type;
    }
    
    
    public int getBildId(Resources res)
    {
    	String[]  colorArray = res.getStringArray(R.array.color);
    	String[]  lumArray = res.getStringArray(R.array.lum);
        if(this.getColor(res).equals( colorArray[3]) && this.getLum(res).contains(lumArray[5])){
            return R.drawable.red;
            
       }else if(this.getColor(res).equals(colorArray[0]) && this.getLum(res).contains(lumArray[5])) {
            return R.drawable.blue;
       
       }else if(this.getColor(res).equals(colorArray[2]) && this.getLum(res).contains(lumArray[5])) {
            return R.drawable.orange;
       
       }else if(this.getColor(res).equals(colorArray[1]) && this.getLum(res).contains(lumArray[5])) {
            return R.drawable.white;
       
       }else if(this.getColor(res).equals(colorArray[0]) && this.getLum(res).contains(lumArray[3])) {
            return R.drawable.bluegiant;
       
       }else if(this.getColor(res).equals(colorArray[2]) && this.getLum(res).contains(lumArray[3])) {
            return R.drawable.orangegiant;
       
       }else if(this.getColor(res).equals(colorArray[1]) && this.getLum(res).contains(lumArray[3])) {
            return R.drawable.whitegiant;
       
       }else if(this.getColor(res).equals(colorArray[3]) && this.getLum(res).contains(lumArray[3])) {
            return R.drawable.redgiant;
       }  else if(this.getLum(res).equals(lumArray[8])) {
           return R.drawable.pulsar;
      }
		return R.drawable.unknownstar;
    }
    
    public String getColor(Resources res) {
    	String[]  Array = res.getStringArray(R.array.color); 
    
        if(type.startsWith("O"))
        {
            return Array[0];
        }
        if(type.startsWith("B"))
        {
            return Array[0];
        }
        if(type.startsWith("A"))
        {
            return Array[0];
        }
        if(type.startsWith("F"))
        {
            return Array[1];
        }
        if(type.startsWith("G"))
        {
            return Array[2];
        }
        if(type.startsWith("K"))
        {
            return Array[3];
        }
        if(type.startsWith("M"))
        {
            return Array[3];
        }
       
        
        if(type.equals(""))
        {
            return "";
        }
        
        return "";
    }
 
    public String getLum(Resources res) {
    	String[]  Array = res.getStringArray(R.array.lum); 
        if(type.endsWith("0"))
        {
            return  Array[0];
        }
        if(type.endsWith("1a"))
        {
            return  Array[1];
        }
        if(type.endsWith("Ib"))
        {
            return Array[2];
        }
        if(type.endsWith("II"))
        {
            return Array[3];
        }
        if(type.endsWith("III"))
        {
            return Array[4];
        }
        if(type.endsWith("IV"))
        {
            return Array[5];
        }
        if(type.endsWith("V"))
        {
            return Array[6];
        }
        if(type.endsWith("VI"))
        {
            return Array[7];
        }
        if(type.endsWith("VII"))
        {
            return Array[8];
        }
        if(type.equals("sdB+M"))
        {
            return Array[9];
        }
        if(type.contains(""))
        {
            return "";
        }
        return "";
    }
    
    public String getHexColor(Resources res){
		
    	String[]  colorArray = res.getStringArray(R.array.color);

    	 if(this.getColor(res).equals(colorArray[3])){
    	            return "#ff0000";
    	            
    	       }else if(this.getColor(res).equals(colorArray[0])){
    	            return "#058AF7";
    	       
    	     
    	       
    	       }else if(this.getColor(res).equals(colorArray[1])) {
    	            return "#CDDEFA";
    	       
    	      
    	       
    	       }else if(this.getColor(res).equals(colorArray[2])) {
    	            return "#fff000";
    	       
    	       }
    	    
    			return "#fff000";
    	    }

    	
    	
    	
  
    
    public String gettype(Resources res){
    	String[]  Array = res.getStringArray(R.array.type); 
    	if(this.getLum(res).equals("pulsar")){
    	 type=" " + Array[0];
    			 }
    
    	else if(!this.getColor(res).equals("") && !this.getLum(res).equals("")){
    		type=" " + Array[1]+" " + this.getColor(res)+ " " + Array[2]+" " +this.getLum(res)+".";
    	}
    	else
    	{
        type="";
    	}
    	return type;
    
}
    }