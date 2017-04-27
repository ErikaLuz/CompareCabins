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

	<h1>Updated ${Group.getCabin().title} Values</h1>
	<hr>
	
	<form action="EditCabin" method="post">
	
		Title: ${Group.getCabin().title}<br />	
		Address: ${Group.getCabin().address}<br />	
		City: ${Group.getCabin().city}<br />	
		State: ${Group.getCabin().state}<br />	
		Description: ${Group.getCabin().description}<br />	
		Bedroom Count: ${Group.getCabin().bedroomCount}<br />
		Bathroom Count: ${Group.getCabin().bathCount}<br />
		Max Occupancy: ${Group.getCabin().maxOccupancy}<br />
		
		<h2>Amenities</h2>
	
	    Lake: ${Group.getAmenities().isHasLake?c}<br />
        River: ${Group.getAmenities().isHasRiver?c}<br /> 
        Pool: ${Group.getAmenities().isHasPool?c}<br />
        Hot Tub: ${Group.getAmenities().isHasHotTub?c}<br />
        WiFi: ${Group.getAmenities().isHasWifi?c}<br />
        Air Conditioning: ${Group.getAmenities().isHasAirConditioning?c}<br />
        Washer & Dryer: ${Group.getAmenities().isHasWasherDryer?c}<br />
        Allow Pets: ${Group.getAmenities().isAllowsPets?c}<br />
        Allow Smoking: ${Group.getAmenities().isAllowsSmoking?c}<br />
	
	</form>

</body>
</html>
