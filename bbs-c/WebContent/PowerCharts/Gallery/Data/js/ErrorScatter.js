var dataString ='<chart  showBorder="0" caption="Rainfall Analysis" yAxisName="Rainfall Rate" xAxisName="Month" verticalErrorBarThickness="1" halfVerticalErrorBar="0" verticalErrorBarColor="5397D9" showvalues="0"  bgalpha="0"  bgcolor="62B0FF" showalternatehgridcolor="0" canvasborderthickness="1" canvasbordercolor="ffffff," divlinealpha="10"  borderthickness="1" bordercolor="3F3F3F" borderalpha="20" canvasborderalpha="20"   outcnvbasefontcolor="3F3F3F"  legendbordercolor="DAECFF" legendborderthickness="1" legendBorderThickness ="2" canvasbgcolor="62B0FF,FFFFFF" canvasBgRatio="1,99" canvasBgAngle="90" numberSuffix="mm">\n\
  <categories>\n\
    <category label="Jan" x="0" />\n\
    <category label="Mar" x="20" />\n\
    <category label="May" x="40" />\n\
    <category label="Jul" x="60" />\n\
    <category label="Sep" x="80" />\n\
    <category label="Nov" x="100" />\n\
  </categories>\n\
  <dataset seriesName="2006" color="5397D9" anchorRadius="5" anchorsides="13" anchorBgColor="6BF9E9" anchorBorderThickness="2" >\n\
    <set y="11" x="10" errorValue="0.78"/>\n\
    <set y="9" x="35" errorValue="0.81"/>\n\
    <set y="4" x="50" errorValue="0.68"/>\n\
    <set y="14" x="70" errorValue="0.86"/>\n\
    <set y="14" x="50" errorValue="0.79"/>\n\
    <set y="3" x="65" errorValue="0.87"/>\n\
    <set y="17" x="85" errorValue="0.7"/>\n\
    <set y="15" x="15" errorValue="0.82"/>\n\
  </dataset>\n\
  <dataset seriesName="2007" color="5397D9" anchorRadius="5" anchorsides="13" anchorBgColor="ffffff" anchorBorderThickness="2">\n\
    <set y="14" x="23" errorValue="0.72"/>\n\
    <set y="15" x="33" errorValue="0.9"/>\n\
    <set y="11" x="77" errorValue="0.83"/>\n\
    <set y="13" x="89" errorValue="0.9"/>\n\
    <set y="11" x="95" errorValue="0.86"/>\n\
    <set y="2" x="4" errorValue="0.7"/>\n\
    <set y="7" x="30" errorValue="0.8"/>\n\
    <set y="11" x="55" errorValue="0.83"/>\n\
  </dataset>\n\
  <styles>\n\
    <definition>\n\
		<style name="myAnchorAnim" type="animation" param="_y" start="0" duration="2"/>\n\
		<style name="myErrorAnim" type="animation" param="_yScale" start="0" duration="3" />\n\
	</definition>\n\
	<application>\n\
		<apply toObject="ANCHORS" styles="myAnchorAnim"/>\n\
		<apply toObject="ERRORBARS" styles="myErrorAnim"/>\n\
	</application>\n\
  </styles>\n\
</chart>';

