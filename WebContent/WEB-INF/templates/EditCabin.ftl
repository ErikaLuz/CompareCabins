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

	<h1>Edit Cabin: ${Cabin.title}</h1>
	<hr>
	
	<form action="EditCabin" method="post">
	
	New Description: <input type="text" name="newDescription" placeholder="${Cabin.description}" /><br />	
	New Bedroom Count: <input type="text" name="newBedCount" placeholder="${Cabin.bedroomCount}" /><br />
	New Bathroom Count: <input type="text" name="neBathCount" placeholder="${Cabin.bathCount}" /><br />
	New Max Occupancy: <input type="text" name="newMaxOcc" placeholder="${Cabin.maxOccupancy}" /><br />
	
	<h2>New Amenities</h2>
	
	    Lake?<input type = "checkbox" name="amenities" value="hasLake" <#if ${Amenities.hasLake}  > checked</#if>>
        River?<input type = "checkbox" name="amenities" value="hasRiver" <#if ${Amenities.hasRiver}  > checked</#if>>
        Pool?<input type = "checkbox" name="amenities" value="hasPool" <#if ${Amenities.hasPool}  >checked</#if>><br />
        Hot Tub?<input type = "checkbox" name="amenities" value="hasHotTub" <#if ${Amenities.hasHotTub}  >checked</#if>>
        WiFi?<input type = "checkbox" name="amenities" value="hasWifi" <#if ${Amenities.hasWifi}  >checked</#if>>
        Air Conditioning?<input type = "checkbox" name="amenities" value="hasAirConditioning" <#if ${Amenities.hasAirConditioning}  >checked</#if>><br />
        Washer & Dryer?<input type = "checkbox" name="amenities" value="hasWasherDryer" <#if ${Amenities.hasWasherDryer}  >checked</#if>>
        Allow Pets?<input type = "checkbox" name="amenities" value="allowsPets" <#if ${Amenities.allowsPets}  >checked</#if>>
        Allow Smoking?<input type = "checkbox" name="amenities" value="allowsSmoking" <#if ${Amenities.allowsSmoking}  >checked</#if>><br />
	
	</form>

</body>
</html>
