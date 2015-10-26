var dataString ='<chart halfErrorBar="0" errorBarWidthPercent="100" showvalues="0"  linedashed="1" anchorradius="4" anchorsides="14" linethickness="3" anchorborderthickness="3" errorbarwidth="7" errorbarcolor="7BAB7E" errorbarthickness="2" xaxisname="Appliance" yaxisname="Life Span (yr)" caption="Life Span Chart of Appliances" subcaption="(With Error Values)" >\n\
	<categories>\n\
		<category label="AC - Central" />\n\
		<category label="AC - Window" />\n\
		<category label="Heat Pump" />\n\
		<category label="Coffee Machine" />\n\
		<category label="Dish Washer" />\n\
	</categories>\n\
	<dataset seriesName="At Home">\n\
		<set value="14" errorValue="1.2" />\n\
		<set value="9" errorValue="1.5" />\n\
		<set value="15" errorValue="1.3" />\n\
		<set value="9" errorValue="1.1" />\n\
		<set value="10" errorValue="1.2" />\n\
	</dataset>\n\
	<dataset seriesName="In Office">\n\
		<set value="11" errorValue="1" />\n\
		<set value="6" errorValue="0.5" />\n\
		<set value="10" errorValue="1.2" />\n\
		<set value="4" errorValue="0.8" />\n\
		<set value="7" errorValue="1" />\n\
	</dataset>\n\
	<styles>\n\
		<definition>\n\
	      <style name="myErrorAnim" type="animation" param="_yScale" start="0" duration="1"/>\n\
		</definition>\n\
		<application>\n\
			<apply toObject="ERRORBARS" styles="myErrorAnim"/>\n\
		</application>\n\
	</styles>\n\
</chart>';