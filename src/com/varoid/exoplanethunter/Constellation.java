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


import java.util.ArrayList;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;


import android.content.res.Resources;




 
public class Constellation {
    

    public Constellation() {
        super();
    
    }
    public static Map<String, String> getmap(Resources res) {
    	String[]  Array =res.getStringArray(R.array.constellation); 
        
        Map<String, String> constellation = new HashMap<String, String>();
        constellation.put("And", Array[0]);
        constellation.put("Ant", Array[1]);
        constellation.put("Aqr", Array[2]);
        constellation.put("Apu", Array[3]);
        constellation.put("Aql", Array[4]);
        constellation.put("Ari", Array[5]);
        constellation.put("Ara", Array[6]);
        constellation.put("Aur", Array[7]);
        constellation.put("Boo", Array[8]);
        constellation.put("Cae", Array[9]);
        constellation.put("Cam", Array[10]);
        constellation.put("Cnc", Array[11]);
        constellation.put("CVn", Array[12]);
        constellation.put("CMa", Array[13]);
        constellation.put("CMi", Array[14]);
        constellation.put("Cap", Array[15]);
        constellation.put("Car", Array[16]);
        constellation.put("Cas", Array[17]);
        constellation.put("Cen", Array[18]);
        constellation.put("Cep", Array[19]);
        constellation.put("Cet", Array[20]);
        constellation.put("Cha", Array[21]);
        constellation.put("Cir", Array[22]);
        constellation.put("Col", Array[23]);
        constellation.put("Com", Array[24]);
        constellation.put("CrA", Array[25]);
        constellation.put("CrB", Array[26]);
        constellation.put("Crv", Array[27]);
        constellation.put("Crt", Array[28]);
        constellation.put("Cru", Array[29]);
        constellation.put("Cyg", Array[30]);
        constellation.put("Del",  Array[31]);
        constellation.put("Dor",  Array[32]);
        constellation.put("Dra",  Array[33]);
        constellation.put("Equ",  Array[34]);
        constellation.put("Eri",  Array[35]);
        constellation.put("For",  Array[36]);
        constellation.put("Gem",  Array[37]);
        constellation.put("Gru",  Array[38]);
        constellation.put("Her",  Array[39]);
        constellation.put("Hor", Array[40]);
        constellation.put("Hya", Array[41]);
        constellation.put("Hyi", Array[42]);
        constellation.put("Ind", Array[43]);
        constellation.put("Lac", Array[44]);
        constellation.put("Leo", Array[45]);
        constellation.put("LMi", Array[46]);
        constellation.put("Lep", Array[47]);
        constellation.put("Lib", Array[48]);
        constellation.put("Lup", Array[49]);
        constellation.put("Lyn", Array[50]);
        constellation.put("Lyr", Array[51]);
        constellation.put("Men", Array[52]);
        constellation.put("Mic", Array[53]);
        constellation.put("Mon", Array[54]);
        constellation.put("Mus", Array[55]);
        constellation.put("Nor", Array[56]);
        constellation.put("Oct", Array[57]);
        constellation.put("Oct", Array[58]);
        constellation.put("Ori", Array[59]);
        constellation.put("Pav", Array[60]);
        constellation.put("Peg", Array[61]);
        constellation.put("Per", Array[62]);
        constellation.put("Phe", Array[63]);
        constellation.put("Pic", Array[64]);
        constellation.put("Psc", Array[65]);
        constellation.put("PsA", Array[66]);
        constellation.put("Pup", Array[67]);
        constellation.put("Pyx", Array[68]);
        constellation.put("Ret", Array[69]);
        constellation.put("Sge", Array[70]);
        constellation.put("Sgr", Array[71]);
        constellation.put("Sco", Array[72]);
        constellation.put("Scl", Array[73]);
        constellation.put("Sct", Array[74]);
        constellation.put("Ser", Array[75]);
        constellation.put("Sex",Array[76]);
        constellation.put("Tau", Array[77]);
        constellation.put("Tel", Array[78]);
        constellation.put("Tri", Array[79]);
        constellation.put("TrA",  Array[80]);
        constellation.put("Tuc", Array[81]);
        constellation.put("UMa",  Array[82]);
        constellation.put("UMi",  Array[83]);
        constellation.put("Vel",  Array[84]);
        constellation.put("Vir",  Array[85]);
        constellation.put("Vol",  Array[86]);
        constellation.put("Vul",  Array[87]);
        
        
        
        
        return Constellation.sortHashMap(constellation);
    
    }
    
    private static Map<String, String> sortHashMap(Map<String, String> constellation){
        Map<String, String> tempMap = new HashMap<String, String>();
        for (String wsState : constellation.keySet()){
            tempMap.put(wsState,constellation.get(wsState));
        }

        List<String> mapKeys = new ArrayList<String>(tempMap.keySet());
        List<String> mapValues = new ArrayList<String>(tempMap.values());
       Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
        Object[] sortedArray = sortedSet.toArray();
        int size = sortedArray.length;
        for (int i=0; i<size; i++){
            sortedMap.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), 
                          (String)sortedArray[i]);
        }
        return sortedMap;
    }

    
    
 public static String getconstellation(String string, Resources res) {
        
        return getmap(res).get(string) ;
 
}
        }