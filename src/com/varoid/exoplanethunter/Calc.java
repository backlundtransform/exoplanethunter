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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.text.format.Time;
import android.util.Log;

public class Calc {

	double a1, a2, a3, b1, b2, b3;
	
	public Calc(double _a1,double _a2,double _a3,double _b1,double _b2,double _b3){
		a1=_a1; 
		a2=_a2;
		a3=_a3;
		b1=_b1; 
		b2=_b2;
		b3=_b3;
		
	}
	
	
	public Calc(double _a1,double _a2,double _a3){
		a1=_a1; 
		a2=_a2;
		a3=_a3;
		b1=0; 
		b2=0;
		b3=1;
	}
	
	
	
	public double dot_product(){
		
		
				
		return Math.acos((a1*b1+a2*b2+a3*b3)
				/((sqrt_vals(a1, a2, a3) * 
						sqrt_vals(b1, b2, b3))));
	}
	
	
	private double sqrt_vals(double _x1, double _x2, double _x3)
	{	
		double sqrt=Math.sqrt(_x1*_x1 + _x2*_x2 +_x3*_x3);
	if(sqrt==0){
		sqrt=1;
	} 
		return sqrt;
	}
	
	
	static final float ALPHA = 0.25f;
	
	public static float[] filter( float[] input, float[] output ) {
	    if ( output == null ) return input;     
	    for ( int i=0; i<input.length; i++ ) {
	        output[i] = output[i] + ALPHA * (input[i] - output[i]);
	    }
	    return output;
	}
	
	
	
	public static double siderealtime(double longitude, int min, int days){
		double startime=0.0657098*days-17.41409 +((min+4*longitude)/60)*1.002737909;
		
		
		if(startime<0){
			
			startime=startime+24;
		}
		
		if(startime>24){
			startime=startime-24;
		}
		
		return startime;
		
	 }
	
	
	public static double declination(double latitude, float altitude, float azimuth){
		
		double sindec=Math.sin(Math.toRadians(latitude))*Math.sin(Math.toRadians(altitude))-Math.cos(Math.toRadians(latitude))*Math.cos(Math.toRadians(altitude))*Math.cos(Math.toRadians(azimuth_angle(azimuth)));
		
		return Math.toDegrees(Math.asin(sindec));
		
	 }
	
	
	public static double hourangle(double latitude, float altitude, float azimuth){
		
		
		
		
		double numerator=Math.sin(Math.toRadians(azimuth_angle(azimuth)));
		double denominator=Math.cos(Math.toRadians(azimuth_angle(azimuth)))*Math.sin(Math.toRadians(latitude))+Math.tan(Math.toRadians(altitude))*Math.cos(Math.toRadians(latitude));
		
		return Math.toDegrees(Math.atan2(numerator,denominator));
				
				
		}
	

	public static int days(){
		
		 Time now = new Time(Time.TIMEZONE_UTC);
		    now.setToNow();
		    
				 Calendar cal1 = new GregorianCalendar();
				 Calendar cal2 = new GregorianCalendar();
				 cal1.set(now.year, 0, 1); 
				 cal2.set(now.year,now.month, now.monthDay);
				 Date d2=  cal2.getTime();
				 Date d1=  cal1.getTime();
		return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
		
	}
	
	public static int minutes(){
		
		
		
		Time now = new Time(Time.TIMEZONE_UTC);
		    now.setToNow();
		  if( TimeZone.getDefault().inDaylightTime( new Date() )){
		    return now.hour*60+now.minute;}else{
		    	
		    	 return (now.hour+1)*60+now.minute;
		    }
		
	}
	
public static float azimuth_angle(float azimuth){
		
if(azimuth>180){
		
  		return azimuth-180;
		
	}
	else if(azimuth<180){
		
		return azimuth+180;
		
	}
return azimuth;
		
	}

public static double right_ascension(double hour,double hourangle){
	double right_ascension=Math.abs(hour*15-hourangle);
if(right_ascension>(double)360){
		
  		return right_ascension-360;
		
	}
	else{
		
		return right_ascension;
		
	}

		
	}

public static String timeformat(double time){
	
	String number;
	int decimal = (int) time; 
	float fractional = (float) (time - decimal);
	if(fractional<(float) 10/60){
		
		 number="0"+String.valueOf(fractional*60).substring(0, 1);
	}else{
		
		 number=String.valueOf(fractional*60).substring(0, 2); 
		
	}
return decimal +":"+number;
		
	}
	
	


}
