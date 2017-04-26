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
    <nav class="navbar navbar-default">
  	<div class="container-fluid">
    <div class="navbar-header">
    <a class="navbar-brand" href="index.html">Compare Cabins</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="index.html">Home</a></li>
      <li><a href="search.html">Search</a></li>
      <li><a href="search.html">Search</a></li>
      <li><a href="search.html">Search</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="registerForm.html"><span class="glyphicon glyphicon-user"></span> Register</a></li>
      <li><a href="loginForm.html"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </ul>
  </div>
</nav>


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
