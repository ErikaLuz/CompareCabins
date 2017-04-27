<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Register Account</title>

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


	<h1>CONGRATULATION YOU HAVE ADDED THE CABIN: ${Cabin.title} </h1>
    
    <h2> Cabin Location</h2>
    Address: ${Cabin.address}<br />
    City:	${Cabin.city}<br />
    State: ${Cabin.state}<br />
 	
 	<h2> More cabin info</h2>
 	Bedroom Count: ${Cabin.bedroomCount}<br />
 	Bathroom Count: ${Cabin.bathCount}<br />
 	Maximum Occupancy: ${Cabin.maxOccupancy}<br />
 	
 	<h2> Tell us about your cabin</h2>
 	Description: ${Cabin.description}<br />
 	<br />
 	
 	<#if FeaturesCheck == "notNull">
 	
 		Features: ${Features.featureString}<br />
 	
 	<#else>
 	
 		No features added<br/>
 	
 	</#if>
 	
 	
    
    <#if AmenitiesCheck == "notNull">
    
	    <h2>Your cabin's amenities</h2>
	    Has Lake: ${Amenities.hasLake?c}<br />
	    Has River: ${Amenities.hasRiver?c}<br />
	    Has Pool: ${Amenities.hasPool?c}<br />
	    Has Hot Tub: ${Amenities.hasHotTub?c}<br />
	    Has Wifi: ${Amenities.hasWifi?c}<br />
	    Has Air Conditioning: ${Amenities.hasAirConditioning?c}<br />
	    Has Washer & Dryer: ${Amenities.hasWasherDryer?c}<br />
	    Allows Pets: ${Amenities.allowsPets?c}<br />
	    Allows Smoking: ${Amenities.allowsSmoking?c}<br />
	    
	<#else>
	
		No amenities added<br/>
    
    </#if>
    

</body>
</html>
