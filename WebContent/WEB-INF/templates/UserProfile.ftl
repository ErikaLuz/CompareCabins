<!DOCTYPE html>
<html>

<head>
    
    <meta charset="UTF-8">
	<title>Compare Cabins</title>
	<link href="css/cabinListing.css" rel="stylesheet" type="text/css">
    
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
	<center><h1>PROFILE:</h1>
    Username: ${User.username}<br />
    Name: ${User.firstName} ${User.lastName}<br />
 	Email: ${User.email}<br />
 	
 	<br /><br />
 	
 	</center>
 	
 	<h2>Reviews: </h2>
 
 	<#list Reviews as r>
 	
 	<div class = "container">
 	
 	<h3>${(r.title)!"DefaultTitle"}</h3>
 	<hr>
 	Number of Stars: ${r.numStars}<br />
 	Review: ${r.description}<br />
 	<br / >
 		 
 	</div>
 	<br / >
 	
 	</#list>
 	 <br/>
 
 	 <br />
 	 <center><button name="editProfile" class="btn">Edit Profile</button></center>
 	 
 	 
 	
</body>
</html>
