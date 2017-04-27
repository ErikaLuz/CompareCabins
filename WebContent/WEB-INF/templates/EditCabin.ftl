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
	
	New Description: <input type="text" name="newDescription" placeholder="${Group.getCabin().description}" /><br />	
	New Bedroom Count: <input type="text" name="newBedCount" placeholder="${Group.getCabin().bedroomCount}" /><br />
	New Bathroom Count: <input type="text" name="neBathCount" placeholder="${Group.getCabin().bathCount}" /><br />
	New Max Occupancy: <input type="text" name="newMaxOcc" placeholder="${Group.getCabin().maxOccupancy}" /><br />
	
	<h2>New Amenities</h2>
	
	    Lake?<input type = "checkbox" name="amenities" value="hasLake" <#if Group.getAmenities().hasLake  > checked</#if>>
        River?<input type = "checkbox" name="amenities" value="hasRiver" <#if Group.getAmenities().hasRiver  > checked</#if>>
        Pool?<input type = "checkbox" name="amenities" value="hasPool" <#if Group.getAmenities().hasPool  >checked</#if>><br />
        Hot Tub?<input type = "checkbox" name="amenities" value="hasHotTub" <#if Group.getAmenities().hasHotTub  >checked</#if>>
        WiFi?<input type = "checkbox" name="amenities" value="hasWifi" <#if Group.getAmenities().hasWifi  >checked</#if>>
        Air Conditioning?<input type = "checkbox" name="amenities" value="hasAirConditioning" <#if Group.getAmenities().hasAirConditioning  >checked</#if>><br />
        Washer & Dryer?<input type = "checkbox" name="amenities" value="hasWasherDryer" <#if Group.getAmenities().hasWasherDryer  >checked</#if>>
        Allow Pets?<input type = "checkbox" name="amenities" value="allowsPets" <#if Group.getAmenities().allowsPets  >checked</#if>>
        Allow Smoking?<input type = "checkbox" name="amenities" value="allowsSmoking" <#if Group.getAmenities().allowsSmoking  >checked</#if>><br />
	
	<h2>New Features</h2>
	
	<#list Group.getFeatureList() as FL>
	New Feature: <input type="text" name="newFeature" placeholder="${FL.featureString}" /><br />
	</#list>
	
	<br /><br /><button name="submitEdit" class="btn">Submit</button>
	
	
	</form>

</body>
</html>
