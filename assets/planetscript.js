function planetsystem(planets, sunradius, habmax, habmin, suncolor, w, h){
   
     var t0 = Date.now();


var svg = d3.select("#planetarium").insert("svg")
       .attr("width", w).attr("height", h);
var def = svg.append("defs");
var gradient = def.append("radialGradient").attr("id", "suncolor")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "50%").attr("fy", "50%");

gradient.append("stop").attr("offset", "0%").attr("style","stop-color:#FFFFFF"); 
gradient.append("stop").attr("offset", "40%").attr("style","stop-color:"+suncolor);      
gradient.append("stop").attr("offset", "100%").attr("style","stop-color#ffbf00").attr("style","stop-opacity:0");  
     
     gradient.append("circle").attr("r",sunradius).attr("cx", w/2)
       .attr("cy", h/2).attr("id", "sun").attr("style","fill:url(#suncolor)");
    
   


var gcoldjovian = def.append("radialGradient").attr("id", "coldjovian")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "20%").attr("fy", "20%");
 
gcoldjovian.append("stop").attr("offset", "0%").attr("style","stop-color:#DFA1F0"); 
gcoldjovian.append("stop").attr("offset", "40%").attr("style","stop-color:#B614E3");      
gcoldjovian.append("stop").attr("offset", "100%").attr("style","stop-color#6E018C");
    
    var ghotjovian = def.append("radialGradient").attr("id", "hotjovian")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "20%").attr("fy", "20%");
 
ghotjovian.append("stop").attr("offset", "0%").attr("style","stop-color:#DFA1F0"); 
ghotjovian.append("stop").attr("offset", "40%").attr("style","stop-color:#E01B56");      
ghotjovian.append("stop").attr("offset", "100%").attr("style","stop-color#6E018C");
    
       var gneptunian = def.append("radialGradient").attr("id", "neptunian")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "20%").attr("fy", "20%");
 
gneptunian.append("stop").attr("offset", "0%").attr("style","stop-color:#fff"); 
gneptunian.append("stop").attr("offset", "40%").attr("style","stop-color:#69BCFF");      
    gneptunian.append("stop").attr("offset", "100%").attr("style","stop-color:#69BCFF");
    
    
          var gearth = def.append("radialGradient").attr("id", "earth")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "20%").attr("fy", "20%");
 
gearth.append("stop").attr("offset", "0%").attr("style","stop-color:#fff"); 
gearth.append("stop").attr("offset", "40%").attr("style","stop-color:#12DB0B");      
    gearth.append("stop").attr("offset", "100%").attr("style","stop-color:#1B92F2");
    
           var ghotearth = def.append("radialGradient").attr("id", "hotearth")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "20%").attr("fy", "20%");
 
ghotearth.append("stop").attr("offset", "0%").attr("style","stop-color:#fff"); 
ghotearth.append("stop").attr("offset", "40%").attr("style","stop-color:#FCA400");      
    ghotearth.append("stop").attr("offset", "100%").attr("style","stop-color:#FCDF00");
    
        var gcoldearth = def.append("radialGradient").attr("id", "coldearth")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "20%").attr("fy", "20%");
 
gcoldearth.append("stop").attr("offset", "0%").attr("style","stop-color:#fff"); 
gcoldearth.append("stop").attr("offset", "40%").attr("style","stop-color:#DCF7F7");      
    gcoldearth.append("stop").attr("offset", "100%").attr("style","stop-color:#69BCFF");
    
           var gmoon = def.append("radialGradient").attr("id", "moon")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "20%").attr("fy", "20%");
 
gmoon.append("stop").attr("offset", "0%").attr("style","stop-color:#8E9191"); 
gmoon.append("stop").attr("offset", "40%").attr("style","stop-color:#8E9191");      
    gmoon.append("stop").attr("offset", "100%").attr("style","stop#8E9191");


    
svg.append("circle").attr("r", habmin).attr("cx", w/2)
       .attr("cy", h/2).attr("class", "minorbit"); 
    
svg.append("circle").attr("r", habmax).attr("cx", w/2)
       .attr("cy", h/2).attr("class", "maxorbit"); 

     var container = svg.append("g")
       .attr("transform", "translate(" + w/2 + "," + h/2 + ")")




     container.selectAll("g.planet").data(planets).enter().append("g")
       .attr("class", "planet").each(function(d, i){
           
           
           
     
           
           d3.select(this).append("circle").attr("r", d.r).attr("id", "planet").attr("class","planet").attr("style","fill:url("+d.color+")").attr("cx", d.R).on("mousemove", function(d) { Android.showToast(d.Name)}).on("mousedown", function(d) {
             Android.showToast(d.Name) });
           });
          
container.append("use").attr("xlink:href","#sun" ).attr("x", -w/2)
       .attr("y", -h/2);
   

 d3.timer(function() {
       var delta = (Date.now() - t0);
    

       svg.selectAll(".planet").attr("transform", function(d) {
 
         return "matrix("+1*Math.cos(d.phi0 + delta * d.speed/20000) +"," +1*Math.sin(d.phi0 + delta * d.speed/20000) +", " +-1*Math.sin(d.phi0 + delta * d.speed/20000) +", "+1*Math.cos(d.phi0 + delta * d.speed/20000) +", 0,0)";
       });
     });} 
      
      
 function Dplanetsystem(planets, sunradius, suncolor, w, h){
   
     var t0 = Date.now();


	var svg = d3.select("#planetarium").insert("svg")
       .attr("width", w).attr("height", h);
	var def = svg.append("defs");
var gradient = def.append("radialGradient").attr("id", "suncolor")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "50%").attr("fy", "50%");

gradient.append("stop").attr("offset", "0%").attr("style","stop-color:#FFFFFF"); 
gradient.append("stop").attr("offset", "40%").attr("style","stop-color:"+suncolor)      
gradient.append("stop").attr("offset", "100%").attr("style","stop-color#ffbf00").attr("style","stop-opacity:0"); 
     
     gradient.append("circle").attr("r",sunradius).attr("cx", w/2)
       .attr("cy", h/2).attr("id", "sun").attr("style","fill:url(#suncolor)");
    
   



var gcoldjovian = def.append("radialGradient").attr("id", "coldjovian")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "20%").attr("fy", "20%");
 
gcoldjovian.append("stop").attr("offset", "0%").attr("style","stop-color:#DFA1F0"); 
gcoldjovian.append("stop").attr("offset", "40%").attr("style","stop-color:#B614E3");      
gcoldjovian.append("stop").attr("offset", "100%").attr("style","stop-color#6E018C");
    
    var ghotjovian = def.append("radialGradient").attr("id", "hotjovian")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "20%").attr("fy", "20%");
 
ghotjovian.append("stop").attr("offset", "0%").attr("style","stop-color:#DFA1F0"); 
ghotjovian.append("stop").attr("offset", "40%").attr("style","stop-color:#E01B56");      
ghotjovian.append("stop").attr("offset", "100%").attr("style","stop-color#6E018C");
    
       var gneptunian = def.append("radialGradient").attr("id", "neptunian")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "20%").attr("fy", "20%");
 
gneptunian.append("stop").attr("offset", "0%").attr("style","stop-color:#fff"); 
gneptunian.append("stop").attr("offset", "40%").attr("style","stop-color:#69BCFF");      
    gneptunian.append("stop").attr("offset", "100%").attr("style","stop-color:#69BCFF");
    
    
          var gearth = def.append("radialGradient").attr("id", "earth")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "20%").attr("fy", "20%");
 
gearth.append("stop").attr("offset", "0%").attr("style","stop-color:#fff"); 
gearth.append("stop").attr("offset", "40%").attr("style","stop-color:#12DB0B");      
    gearth.append("stop").attr("offset", "100%").attr("style","stop-color:#1B92F2");
    
           var ghotearth = def.append("radialGradient").attr("id", "hotearth")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "20%").attr("fy", "20%");
 
ghotearth.append("stop").attr("offset", "0%").attr("style","stop-color:#fff"); 
ghotearth.append("stop").attr("offset", "40%").attr("style","stop-color:#FCA400");      
    ghotearth.append("stop").attr("offset", "100%").attr("style","stop-color:#FCDF00");
    
        var gcoldearth = def.append("radialGradient").attr("id", "coldearth")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "20%").attr("fy", "20%");
 
gcoldearth.append("stop").attr("offset", "0%").attr("style","stop-color:#fff"); 
gcoldearth.append("stop").attr("offset", "40%").attr("style","stop-color:#DCF7F7");      
    gcoldearth.append("stop").attr("offset", "100%").attr("style","stop-color:#69BCFF");
    
           var gmoon = def.append("radialGradient").attr("id", "moon")
.attr("gradientUnits", "objectBoundingBox").attr("gradientUnits", "objectBoundingBox").attr("fx", "20%").attr("fy", "20%");
 
gmoon.append("stop").attr("offset", "0%").attr("style","stop-color:#8E9191"); 
gmoon.append("stop").attr("offset", "40%").attr("style","stop-color:#8E9191");      
    gmoon.append("stop").attr("offset", "100%").attr("style","stop#8E9191");


    
  var container = svg.append("g")
       .attr("transform", "translate(" + w/2 + "," + h/2 + ")")
   container.selectAll("g.planet").data(planets).enter().append("g")
       .attr("class", "planet").each(function(d, i){
          
           
 d3.select(this).append("circle").attr("r", 2*d.r).attr("id", "planet").attr("class","planet").attr("style","fill:url("+d.color+")").attr("cx", d.R+2*sunradius).on("mousemove", function(d) { Android.showToast(d.Name)}).on("mousedown", function(d) {
             Android.showToast(d.Name) });
  
                          
       });
container.append("use").attr("xlink:href","#sun" ).attr("x", -w/2)
       .attr("y", -h/2);
   

     d3.timer(function() {
       var delta = (Date.now() - t0);
    

       svg.selectAll(".planet").attr("transform", function(d) {
 return "scale("+1*Math.cos(d.phi0+delta/20000*d.speed)+") matrix("+1*Math.cos(d.phi0+delta/20000*d.speed) +"," +1*Math.sin(d.phi0+delta/20000*d.speed) +", " +-1*Math.sin(d.phi0+delta/20000*d.speed) +", "+1*Math.cos(d.phi0+delta/20000*d.speed) +", 0,0) rotate(45)"; });
     });}
