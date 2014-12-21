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

public class HZ {
    String val;
    HashMap<String, String> alternatives;
        
    HZ(String str)
    {
    	
    if(fl_val(str)!=-999)
    {
        if((fl_val(str)<1) && (fl_val(str)>-1))
        {
            val = "mitt";
        }
        else if(fl_val(str)< -1)
        {
            val = "too_close";
        }
        else if(fl_val(str)> 1)
        {
            val = "far_away";
        }
     }
    else
        val = "does not exist";
    }
    
    
    private float fl_val(String sr)
    {
    	
    	if(!sr.equals("")){
        return Float.valueOf(sr);
        }
    	else
    	{
        return -999;
    }
    }
    
    
    public String getVal(HashMap<String, String> ret)
    {
        return ret.get(this.val);
    }
}