<!DOCTYPE html>
<html>

<head>
    
    <meta charset="UTF-8">
	<title>Compare Cabins</title>
	<link href="css/cabinListing.css" rel="stylesheet" type="text/css">
    
</head>

<body>

	<h1>YOUR CABINS: </h1>

	<#list userCabins as cabin>
	
			<h3>${cabin.description}</h3>
			<hr>
			<p>
			Address: ${cabin.address}<br />
			City: ${cabin.city}<br />
			State: ${cabin.state}<br />
			</p>
			<form action="UserCabinListing" method="post">
            <button name="userCabinListing" class="btn">EDIT THIS CABIN</button>
            </form>
		
		<br />
	
	</#list>
    
</body>
</html>
