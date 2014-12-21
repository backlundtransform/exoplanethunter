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

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

import android.graphics.Matrix;
import android.graphics.Paint;

import android.util.AttributeSet;

import android.view.MotionEvent;
import android.view.View;




public class Hemisphere extends View {
	  
	 private double rotation,translation;
	
	 private boolean hemisphere=false;
		View stars;
		 Matrix transform = new Matrix();
		 Paint paint = new Paint();
		
		
		 
		 private Bitmap northmap =bit(R.drawable.north);
		 private Bitmap southmap = bit(R.drawable.south); 
		
	 
	
	 public  Hemisphere (Context context) {
		  super(context);
		  
		  start();
		  
		 }
		  
		 public  Hemisphere (Context context, AttributeSet attrs) {
		  super(context, attrs);
		  // TODO Auto-generated constructor stub
		  start();
		 }
		  
		 public  Hemisphere (Context context, AttributeSet attrs, int defStyle) {
		  super(context, attrs, defStyle);
		  // TODO Auto-generated constructor stub
		  start();
		 }
		 
		 
		 protected void start()
		 {
			 this.setBackgroundColor(Color.parseColor("#050519"));
		 }
		 
		  
		 @Override
		 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		  setMeasuredDimension(
		    MeasureSpec.getSize(widthMeasureSpec),
		    MeasureSpec.getSize(heightMeasureSpec));
		  
		 }
	  
	 
	 @Override
	 protected void onDraw(Canvas canvas) {
		 
			 if(hemisphere)
				 rot(northmap, canvas);
			 else
				 rot(southmap, canvas);
		
			
		
	 }
	 
	 private void rot(Bitmap map, Canvas c)
	 {
		 transform.setRotate((float)rotation, map.getHeight()/2,  map.getWidth()/2);
		transform.postTranslate(-map.getWidth()/2, (float) translation);
		 
		    c.drawBitmap(map,transform, paint);
		 
	 }
	 
	 private int translate(Bitmap map, int trans)
	 {
		return (trans * map.getHeight())/180;
	 }
	

	 public void update(int _rotation, int _translation){
		 rotation = _rotation;
		 if(_translation>0){
			 hemisphere=true;
		 translation =-translate(northmap, _translation);
		 
		 
		 }
		 else{
			 
		  translation = translate(southmap, _translation);	
		  hemisphere=false;
		 }
		 
		
	  invalidate();
	 }

	public void refresh() {
		 
		 northmap.recycle(); 
		 southmap.recycle();
		 System.gc();
		
	}
	
	public Bitmap bit(int bit) {
		 
		 BitmapFactory.Options options=new BitmapFactory.Options();
		 options.inDither=false;                    
		 options.inPurgeable=true;                   
		 options.inInputShareable=true;              
		 options.inTempStorage=new byte[32 * 1024]; 
		
			 
		 
			 return BitmapFactory.decodeResource(getResources(),bit, options);
			 
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
	    case MotionEvent.ACTION_DOWN:
	       
	        break;
	    case MotionEvent.ACTION_MOVE:
	      
	        break;
	    case MotionEvent.ACTION_UP:
	    
	        break;
	}
	return true;
		
		
	}
	
	
	 
	   
	}
