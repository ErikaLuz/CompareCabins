<!DOCTYPE html>
<html>

<head>
    
    <meta charset="UTF-8">
	<title>Compare Cabins</title>
	<link href="css/cabinListing.css" rel="stylesheet" type="text/css">
    
</head>

<body>
	<h1>CONGRATULATION YOU HAVE ADDED THIS CABIN: ${Cabin.title} </h1>
    
    <h2>Cabin pictures null for now </h2>
    
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
 	Features: ${Features.featureString}<br />
    
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
    
    <h2>Cabin availability: blank for now</h2>
    
    
    </form>

</body>
</html>
