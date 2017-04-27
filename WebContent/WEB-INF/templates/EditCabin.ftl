<!DOCTYPE html>
<html>

<head>
    
    <meta charset="UTF-8">
	<title>Compare Cabins</title>
	
	<style>
    
	    .container
	    {
	    	padding-left: 5px;
	    	padding-right: 10px;
	    	border-style: solid;
	    	margin-left: .5cm;
	    	margin-right: .5cm;
	    }
	    
	    h1 
	    {
	    	font-size: 50px;
	    }
    
    </style>
    
</head>

<body>

	<h1>Edit Cabin: ${Group.getCabin().title}</h1>
	<hr>
	
	<form action="EditCabin" method="post">
	
	<h2>Delete Pictures:</h2>
	
	<#list Group.getCabinPictureList() as CP>	
	<img src="${CP.filePath}" alt="cabin" style="width:300px;height:300px;">
	<button name="deletePhoto" class="btn">Delete Photo</button><br />
	</#list>

	
	<h2>Add an Availability:</h2>
   	
   	Date(2000-01-01 format)<input type="date" name="cabinAvailability" /><br>
   	Price: <input type="text" name="cabinPrice" /><br />
   	<button name="addAvailability" class="btn">Add Availability</button>
	
	
	<h2>Cabin Info</h2>
	
	Title: <input type="text" name="newTitle" value="${Group.getCabin().title}" /><br />
	Address: <input type="text" name="newAddress" value="${Group.getCabin().address}" /><br />
	City: <input type="text" name="newCity" value="${Group.getCabin().city}" /><br />
	State: <input type="text" name="newState" value="${Group.getCabin().state}" /><br />
	Description: <input type="text" name="newDescription" value="${Group.getCabin().description}" /><br />	
	Bedroom Count: <input type="text" name="newBedCount" value="${Group.getCabin().bedroomCount}" /><br />
	Bathroom Count: <input type="text" name="newBathCount" value="${Group.getCabin().bathCount}" /><br />
	Max Occupancy: <input type="text" name="newMaxOcc" value="${Group.getCabin().maxOccupancy}" /><br />
	
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
      	
	<h2>Features</h2>
	
	<#list Group.getFeatureList() as FL>
	Feature: ${FL.featureString}
	<button name="editFeature" class="btn">Edit Feature</button>
	<button name="deleteFeature" class="btn">Delete Feature</button><br/>
	</#list>
	
	<input type="text" name="newFeature" value="" /><button name="addFeature" class="btn">Add Feature</button><br/>
	 
	</form>
	
	
	
	

</body>
</html>
