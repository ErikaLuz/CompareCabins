<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	
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
    
    <title>Edit Cabin</title>
	
</head>

<body>

    <#include "Header.ftl">
        
    <div class="container">
    <div class="row centered-form">
    <div class="panel panel-default">
    <div class="panel-heading">
	
    <h1>Edit Cabin: ${Group.getCabin().title}</h1><hr>
	
    <div class="panel-body">
        
    <!-- Deleting Pictures -->
	
	<h2>Delete Pictures:</h2>
	
	<#list Group.getCabinPictureList() as CP>	
	
        <form action="EditCabin?cpId=${CP.id}" method="post">

            <img src="${CP.filePath}" alt="cabin" style="width:300px;height:300px;">
            <button name="deletePhoto" class="btn">Delete Photo</button><br />

        </form>
        
	</#list>
        
    <!-- Edit availability, cabin values, and amenities -->

	<form action="EditCabin?cabinId=${Group.getCabin().id}" method="post">
        
        <!-- Add availability -->
        
            <h2>Add an Availability:</h2>

            Date(2000-01-01 format)<input type="date" name="cabinAvailability" /><br>
            Price: <input type="text" name="cabinPrice" /><br />
            <button name="addAvailability" class="btn">Add Availability</button>

        <!-- Edit cabin values -->
        
            <h2>Cabin Info</h2>

            Title: <input type="text" name="newTitle" value="${Group.getCabin().title}" /><br />
            Address: <input type="text" name="newAddress" value="${Group.getCabin().address}" /><br />
            City: <input type="text" name="newCity" value="${Group.getCabin().city}" /><br />
            State: <input type="text" name="newState" value="${Group.getCabin().state}" /><br />
            Description: <input type="text" name="newDescription" value="${Group.getCabin().description}" /><br />	
            Bedroom Count: <input type="text" name="newBedCount" value="${Group.getCabin().bedroomCount}" /><br />
            Bathroom Count: <input type="text" name="newBathCount" value="${Group.getCabin().bathCount}" /><br />
            Max Occupancy: <input type="text" name="newMaxOcc" value="${Group.getCabin().maxOccupancy}" /><br />
        
        <!-- Edit cabin amenities -->

            <h2>Amenities</h2>

            Lake?<input type = "checkbox" name="amenities" value="hasLake" <#if Group.getAmenities().hasLake  > checked</#if>><br />
            River?<input type = "checkbox" name="amenities" value="hasRiver" <#if Group.getAmenities().hasRiver  > checked</#if>><br />
            Pool?<input type = "checkbox" name="amenities" value="hasPool" <#if Group.getAmenities().hasPool  >checked</#if>><br />
            Hot Tub?<input type = "checkbox" name="amenities" value="hasHotTub" <#if Group.getAmenities().hasHotTub  >checked</#if>><br />
            WiFi?<input type = "checkbox" name="amenities" value="hasWifi" <#if Group.getAmenities().hasWifi  >checked</#if>><br />
            Air Conditioning?<input type = "checkbox" name="amenities" value="hasAirConditioning" <#if Group.getAmenities().hasAirConditioning  >checked</#if>><br />
            Washer & Dryer?<input type = "checkbox" name="amenities" value="hasWasherDryer" <#if Group.getAmenities().hasWasherDryer  >checked</#if>><br />
            Allow Pets?<input type = "checkbox" name="amenities" value="allowsPets" <#if Group.getAmenities().allowsPets  >checked</#if>><br />
            Allow Smoking?<input type = "checkbox" name="amenities" value="allowsSmoking" <#if Group.getAmenities().allowsSmoking  >checked</#if>><br />

            <br /><br /><button name="submitEdit" class="btn">Submit</button>  	

    </form>

    <!-- Edit cabin features -->
      	
	<h2>Features</h2>
	
	<#list Group.getFeatureList() as FL>
        
        <form action="EditCabin?featureId=${FL.id}" method="post">
        
            Feature: ${FL.featureString}
            <button name="editFeature" class="btn">Edit Feature</button>
            <button name="deleteFeature" class="btn">Delete Feature</button><br/>
            
	</#list>
	
        <input type="text" name="newFeature" value="" /><button name="addFeature" class="btn">Add Feature</button><br/>
	
        </form>
	
	</div>
    </div>
    </div>
    </div>
    </div>
	
</body>
</html>
