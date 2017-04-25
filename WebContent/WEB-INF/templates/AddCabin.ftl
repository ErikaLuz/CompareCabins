<!DOCTYPE html>
<html>

<head>
    
    <meta charset="UTF-8">
	<title>Compare Cabins</title>
	<link href="css/cabinListing.css" rel="stylesheet" type="text/css">
    
    <style>
    
    body
    {
    	padding: 10px;
    }
    
    #left 
	{
      float: left;
  	  width: 50%;
	}

	#right
	{
    	float: right;
   		width: 50%; 
   		height: 100%;
	}

    #top
    {
		width: 100%;
    }
    </style>
    
</head>

<body>
	
    <form action="AddCabin" method="post">
    
    <div id="top">
    
	  <div id = "left">
	  <img src="placeholder_600x400.svg" alt="thumbnail" style="width:500px;height:300px;padding-left:10px;display:block;margin:auto;">
	  <center><button name="addCabin" class="btn">Add Pictures</button></center>
	  </div>
    
    	  <div id = "right">
    	  	<br>
    		Title: <input type="text" name="title" placeholder="Enter a title for your cabin" /><br /> 
    		<hr>
    		<h2> Tell us about your cabin</h2>
 			Description: <input type="text" name="description" placeholder="enter description" />
 			<br />
    	</div>
    
    </div>
    <br/>
    
    <div>
    
    <h2> Add cabin Location</h2>
    Address: <input type="text" name="address" placeholder="enter address" /><br />
    City: <input type="text" name="city" placeholder="enter city" /><br />
    State: <input type="text" name="state" placeholder="enter state" /><br />
 
 	<h2> More cabin info</h2>
 	Bedroom Count: <input type="text" name="bedCount" placeholder="enter number of bedrooms" /><br />
 	Bathroom Count: <input type="text" name="bathCount" placeholder="enter number of bathrooms" /><br />
 	Maximum Occupancy:<input type="text" name="maxOcc" placeholder="enter maximum occupancy" /><br />
 	
 	<h2> Tell us about your cabin</h2>
 	Description: <input type="text" name="description" placeholder="enter description" />
 	<br />
 	Features: <input type="text" name="features" placeholder="enter cabin features" /><br />
    
    <h2>Add your cabin amenities</h2>
    <input type = "checkbox" name="amenities" value="hasLake">Has Lake<br />
    <input type = "checkbox" name="amenities" value="hasRiver">Has River<br />
    <input type = "checkbox" name="amenities" value="hasPool">Has Pool<br />
    <input type = "checkbox" name="amenities" value="hasHotTub">Has Hot Tub<br />
    <input type = "checkbox" name="amenities" value="hasWifi">Has Wifi<br />
    <input type = "checkbox" name="amenities" value="hasAirConditioning">Has Air Conditioning<br />
    <input type = "checkbox" name="amenities" value="hasWasherDryer">Has Washer & Dryer<br />
    <input type = "checkbox" name="amenities" value="allowsPets">Allows Pets<br />
    <input type = "checkbox" name="amenities" value="allowsSmoking">Allows Smoking<br />
    
    <h2>Add cabin availability: blank for now</h2>
    
    
    <button name="addCabin" class="btn">Add Cabin</button>
    
    
    </div>
    
    </form>

</body>
</html>
