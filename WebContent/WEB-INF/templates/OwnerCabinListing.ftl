<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Your Profile</title>

    <!-- Bootstrap -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<#include "Header.ftl">

    <div id="left">
        
        <#if CPCheck != "null">
        
	        <img src="${PriorityPicture.filePath}" style="width:700px;height:500px;padding-left:10px;display:block;margin:auto;">
			<br/><center><button name="viewAllPhotos" class="btn">View All Photos</button></center><br/>
		
		<#else>
		
			<img src="placeholder_600x400.svg" style="width:700px;height:500px;padding-left:10px;display:block;margin:auto;">
			<br/><center><button name="viewAllPhotos" class="btn">View All Photos</button></center><br/>
		
		</#if>
		
        <div class="section">
            
      		${Cabin.description}
        
        </div>
        <br />

        
        <#if AmenitiesCheck != "null">
        
	        <div class="section">
	            
	            <#list Features as f>
	            
	           		${f.featureString} 
	            
	            </#list>
	        
	        </div>
	        <br>
        
        </#if>
        
        <div class="section">

            <p>
                Bedroom(s): ${Cabin.bedroomCount}<br/> 
                Bathrooms: ${Cabin.bathCount}<br/> 
                Max Occupancy: ${Cabin.maxOccupancy}</br>
            </p>

        </div>
        <br />
        
        <#if AmenitiesCheck != "null">
        
	        <div class="section">
	        	
	        	<h3>Amenities</h3><hr>
	        	
	        	<p>
	        		Has Lake: ${(Amenities.hasLake)?c}<br />
	        		Has River: ${(Amenities.hasRiver)?c}<br />
	        		Has Pool: ${(Amenities.hasPool)?c}<br />
	        	</p>
	        	
	        </div>
	        <br />
        
        </#if>
        
        <#if ReviewsCheck != "null">
        
	        <div class="section">
	        	
	        	<h3>Reviews</h3><hr>
	        	
	        	<p>
	        		<#list Reviews as r>
	            
	            		Number of Stars: ${r.numStars}<br />
	            		Title: ${r.title}<br />
	            		Review: ${r.description} <br />
	            		
	            		<br />
	            
	          	   </#list>
	        	</p>
	        	
	        </div>
	    	<br />
    	
    	</#if>
    	
    </div>
    
    <div id="right">
        
        <h1 id="title">${Cabin.title}</h1>
        <hr>

        <div id="content">

            <h2 id="title">Availability</h2>

            <center>   

            <script language="javascript" type="text/javascript">
            var day_of_week = new Array('Sun','Mon','Tue','Wed','Thu','Fri','Sat');
            var month_of_year = new Array('January','February','March','April','May','June','July','August','September','October','November','December');

            //  DECLARE AND INITIALIZE VARIABLES
            var Calendar = new Date();

            var year = Calendar.getFullYear();     // Returns year
            var month = Calendar.getMonth();    // Returns month (0-11)
            var today = Calendar.getDate();    // Returns day (1-31)
            var weekday = Calendar.getDay();    // Returns day (1-31)

            var DAYS_OF_WEEK = 7;    // "constant" for number of days in a week
            var DAYS_OF_MONTH = 31;    // "constant" for number of days in a month
            var cal;    // Used for printing

            Calendar.setDate(1);    // Start the calendar day at '1'
            Calendar.setMonth(month);    // Start the calendar month at now


            /* VARIABLES FOR FORMATTING
            NOTE: You can format the 'BORDER', 'BGCOLOR', 'CELLPADDING', 'BORDERCOLOR'
                  tags to customize your caledanr's look. */

            var TR_start = '<TR>';
            var TR_end = '</TR>';
            var highlight_start = '<TD WIDTH="30"><TABLE CELLSPACING=0 BORDER=1 BGCOLOR=DEDEFF BORDERCOLOR=CCCCCC><TR><TD WIDTH=20><B><CENTER>';
            var highlight_end   = '</CENTER></TD></TR></TABLE></B>';
            var TD_start = '<TD WIDTH="30"><CENTER>';
            var TD_end = '</CENTER></TD>';

            /* BEGIN CODE FOR CALENDAR
            NOTE: You can format the 'BORDER', 'BGCOLOR', 'CELLPADDING', 'BORDERCOLOR'
            tags to customize your calendar's look.*/

            cal =  '<TABLE BORDER=1 CELLSPACING=0 CELLPADDING=0 BORDERCOLOR=BBBBBB><TR><TD>';
            cal += '<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2>' + TR_start;
            cal += '<TD COLSPAN="' + DAYS_OF_WEEK + '" BGCOLOR="#EFEFEF"><CENTER><B>';
            cal += month_of_year[month]  + '   ' + year + '</B>' + TD_end + TR_end;
            cal += TR_start;

            //   DO NOT EDIT BELOW THIS POINT  //

            // LOOPS FOR EACH DAY OF WEEK
            for(index=0; index < DAYS_OF_WEEK; index++)
            {

            // BOLD TODAY'S DAY OF WEEK
            if(weekday == index)
            cal += TD_start + '<B>' + day_of_week[index] + '</B>' + TD_end;

            // PRINTS DAY
            else
            cal += TD_start + day_of_week[index] + TD_end;
            }

            cal += TD_end + TR_end;
            cal += TR_start;

            // FILL IN BLANK GAPS UNTIL TODAY'S DAY
            for(index=0; index < Calendar.getDay(); index++)
            cal += TD_start + '  ' + TD_end;

            // LOOPS FOR EACH DAY IN CALENDAR
            for(index=0; index < DAYS_OF_MONTH; index++)
            {
            if( Calendar.getDate() > index )
            {
              // RETURNS THE NEXT DAY TO PRINT
              week_day =Calendar.getDay();

              // START NEW ROW FOR FIRST DAY OF WEEK
              if(week_day == 0)
              cal += TR_start;

              if(week_day != DAYS_OF_WEEK)
              {

              // SET VARIABLE INSIDE LOOP FOR INCREMENTING PURPOSES
              var day  = Calendar.getDate();

              // HIGHLIGHT TODAY'S DATE
              if( today==Calendar.getDate() )
              cal += highlight_start + day + highlight_end + TD_end;

              // PRINTS DAY
              else
              cal += TD_start + day + TD_end;
              }

              // END ROW FOR LAST DAY OF WEEK
              if(week_day == DAYS_OF_WEEK)
              cal += TR_end;
              }

              // INCREMENTS UNTIL END OF THE MONTH
              Calendar.setDate(Calendar.getDate()+1);

            }// end for loop

            cal += '</TD></TR></TABLE></TABLE>';

            //  PRINT CALENDAR
            document.write(cal);

            //  End -->
            </script>

            <br />
            <div>
            <input type="text" name="startDate" placeholder="Start Date" />
            <input type="text" name="endDate" placeholder="End Date" />
            </div>

            <br>
            <button name="Rent" class="btn">RENT</button>

            </center>
        
        </div>
    
    </div>

</body>
</html>