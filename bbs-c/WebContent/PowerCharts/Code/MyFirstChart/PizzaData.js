var dataString= '<chart caption="Pizza Mania" subcaption="Consumption" \n\
palettecolors="880000,880000" plotgradientcolor="fba71a" showplotBorder="0" \n\
plotfillalpha="90,90" canvasbgalpha="0" numdivlines="0" showyaxisvalues="0" \n\
rotateValues="1" formatNumberScale="0" \n\
canvasborderalpha="0" bgImage="pizza.jpg" \n\
>\n\
<set label="Jan" value="900" />\n\
<set label="Feb" value="500" />\n\
<set label="Mar" value="700" />\n\
<set label="Apr" value="1000" />\n\
<set label="May" value="500" />\n\
<set label="June" value="200" />\n\
<set label="July" value="400"  />\n\
\n\
   <styles>\n\
	  <definition>\n\
		 <style name="ani" type="animation" DURATION="3" easing="bounce" start="0" param="_xscale"/>\n\
		 <style name="myFont" type="font" color="390D07"/>\n\
		 <style name="myFontCaption" type="font" size="18" color="ffffff" />\n\
		 <style name="myFontSubCaption" type="font" size="12" color="ffffff"/>\n\
		 <style name="myShadow" type="shadow" color="333333" angle="45" strength="3"/>\n\
	  </definition>\n\
	  <application>\n\
		 <apply toObject="Dataplot" styles="ani"/>\n\
		 <apply toObject="Datavalues" styles="myFont"/>\n\
		 <apply toObject="Datalabels" styles="myFont"/>\n\
		 <apply toObject="caption" styles="myFontCaption,myShadow"/>\n\
		 <apply toObject="subcaption" styles="myFontSubCaption,myShadow"/>\n\
	  </application>\n\
   </styles>\n\
\n\
</chart>';