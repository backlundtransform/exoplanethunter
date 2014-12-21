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

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
  
public class Compass extends View {
  
 private double direction;
 Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); 
 public Compass(Context context) {
	  super(context);
	  // TODO Auto-generated constructor stub
	 }
	  
	 public Compass(Context context, AttributeSet attrs) {
	  super(context, attrs);
	  // TODO Auto-generated constructor stub
	 }
	  
	 public Compass(Context context, AttributeSet attrs, int defStyle) {
	  super(context, attrs, defStyle);
	  // TODO Auto-generated constructor stub
	 }
	  
	 @Override
	 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	  setMeasuredDimension(
	    MeasureSpec.getSize(widthMeasureSpec),
	    MeasureSpec.getSize(heightMeasureSpec));
	 }
  
 
 @Override
 protected void onDraw(Canvas canvas) {
    
 int w = getMeasuredWidth();
  int h = getMeasuredHeight();
 int r;
  if(w > h){
   r = h/3;
  }else{
   r = w/3;
  }
    
  
 paint.setStyle(Paint.Style.STROKE);
 paint.setStrokeWidth(2);
  paint.setColor(Color.BLACK);
    

  
    
  paint.setColor(Color.RED);
  canvas.drawLine(
    w/2,
    h/2,
    (float)(w/2 + r * Math.sin(-direction)),
    (float)(h/2 - r * Math.cos(-direction)),
    paint);
 
  

  
 }
   
 public void update(double d){
  direction = d;

  invalidate();
 }
   
}