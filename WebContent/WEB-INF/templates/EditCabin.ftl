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
	
    <h1 class="text-center">Editting Cabin: ${Group.getCabin().title}</h1><hr>
	
    <div class="panel-body">
        

        
    <!-- Edit availability, cabin values, and amenities -->

	<form action="EditCabin?cabinId=${Group.getCabin().id}" method="post">
        <div class="row">
        <div class="col-sm-4">
        <!-- Add availability -->
            <h2>Add an Availability:</h2>

            Date(2000-01-01 format)<input type="date" class="form-control" name="cabinAvailability" /><br>
            Price: <input type="text" class="form-control" name="cabinPrice" /><br />
            <button name="addAvailability" class="btn">Add Availability</button>
        </div>
        <!-- Edit cabin values -->
        <div class="col-sm-4">
            <h2>Cabin Info</h2>

            Title: <input type="text" name="newTitle" class="form-control" value="${Group.getCabin().title}" /><br />
            Address: <input type="text" name="newAddress" class="form-control" value="${Group.getCabin().address}" /><br />
            City: <input type="text" name="newCity" class="form-control" value="${Group.getCabin().city}" /><br />
            State: <input type="text" name="newState" class="form-control" value="${Group.getCabin().state}" /><br />
            Description: <input type="text" name="newDescription" class="form-control" value="${Group.getCabin().description}" /><br />	
            Bedroom Count: <input type="text" name="newBedCount" class="form-control" value="${Group.getCabin().bedroomCount}" /><br />
            Bathroom Count: <input type="text" name="newBathCount" class="form-control" value="${Group.getCabin().bathCount}" /><br />
            Max Occupancy: <input type="text" name="newMaxOcc" class="form-control" value="${Group.getCabin().maxOccupancy}" /><br />
        </div>
        <!-- Edit cabin amenities -->
        <div class="col-sm-4">
            <h2>Amenities</h2>
            Lake?<input type = "checkbox" name="amenities" class="form-control" value="hasLake" <#if Group.getAmenities().hasLake  > checked</#if>><br />
            River?<input type = "checkbox" name="amenities" class="form-control" value="hasRiver" <#if Group.getAmenities().hasRiver  > checked</#if>><br />
            Pool?<input type = "checkbox" name="amenities" class="form-control" value="hasPool" <#if Group.getAmenities().hasPool  >checked</#if>><br />
            Hot Tub?<input type = "checkbox" name="amenities" class="form-control" value="hasHotTub" <#if Group.getAmenities().hasHotTub  >checked</#if>><br />
            WiFi?<input type = "checkbox" name="amenities" class="form-control" value="hasWifi" <#if Group.getAmenities().hasWifi  >checked</#if>><br />
            Air Conditioning?<input type = "checkbox" class="form-control" name="amenities" value="hasAirConditioning" <#if Group.getAmenities().hasAirConditioning  >checked</#if>><br />
            Washer & Dryer?<input type = "checkbox" class="form-control" name="amenities" value="hasWasherDryer" <#if Group.getAmenities().hasWasherDryer  >checked</#if>><br />
            Allow Pets?<input type = "checkbox" class="form-control" name="amenities" value="allowsPets" <#if Group.getAmenities().allowsPets  >checked</#if>><br />
            Allow Smoking?<input type = "checkbox" class="form-control" name="amenities" value="allowsSmoking" <#if Group.getAmenities().allowsSmoking  >checked</#if>><br />
			</div>
			</div>
			<div class="row">
			<div class="col-sm-12">
			<button name="submitEdit" class="btn btn-block">Submit</button> 
			</div>
    </form>

    <!-- Edit cabin features -->
      	
	<h2 class="text-center">Features</h2>
	
	<#list Group.getFeatureList() as FL>
        
        <form action="EditCabin?featureId=${FL.id}" method="post">
        
          	<p class="text-center">${FL.featureString}</p><br/>
          	<button name="deleteFeature" class="btn btn-block">Delete Feature</button><br/>
            <button name="editFeature" class="btn btn-block">Edit Feature</button>
            
	</#list>
	
        <input type="text" name="newFeature" value="" /><button name="addFeature" class="btn btn-block">Add Feature</button><br/>
	
        </form>
        
     <!-- Deleting Pictures -->
	
	<h2 class="text-center">Delete Pictures</h2>
	
	<#list Group.getCabinPictureList() as CP>	
	
        <form action="EditCabin?cpId=${CP.id}" method="post">
            <img src="${CP.filePath}" class="center-block" alt="cabin" style="width:300px;height:300px;">
            <button name="deletePhoto" class="btn center-block">Delete Photo</button>
        </form>
        
	</#list>
	
	</div>
    </div>
    </div>
    </div>
	
</body>
</html>
