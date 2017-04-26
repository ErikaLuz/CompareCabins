<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>${Cabin.title}</title>

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
        <div class="container">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<h1 class="text-center">${Cabin.title}</h1>
			 			</div>
             <div class="panel-body">
             
        <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6">
             
             
        <#if CPCheck != "null">
        
	        <img src="${PriorityPicture.filePath}" class="img-rounded img-responsive" alt="thumbnail"">
			<br/><center><button name="viewAllPhotos" class="btn">View All Photos</button></center><br/>
		
		<#else>
		
			<img src="http://placehold.it/600x400" class="img-rounded img-responsive" alt="thumbnail"">
			<br/><center><button name="viewAllPhotos" class="btn">View All Photos</button></center><br/>
		
		</#if>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
        <h1 class="text-center">Availability --Add Calendars--</h1>
        </div>
        </div
		
        <div class="section">
            
      		${Cabin.description}
        
        </div>
        <br />

        
        <#if FeaturesCheck != "null">
        
	        <div class="section">
	            
	            <#list Features as f>
	            
	           		${f.featureString} 
	            
	            </#list>
	        
	        </div>
	        <br>
        
        </#if>
        
        <div class="section">

            <p>
                Bedroom(s): ${Cabin.bedroomCount}<br/> 
                Bathrooms: ${Cabin.bathCount}<br/> 
                Max Occupancy: ${Cabin.maxOccupancy}</br>
            </p>

        </div>
        <br />
        
        <#if AmenitiesCheck != "null">
        
	        <div class="section">
	        	
	        	<h3>Amenities</h3><hr>
	        	
	        	<p>
	        		Has Lake: ${(Amenities.hasLake)?c}<br />
	        		Has River: ${(Amenities.hasRiver)?c}<br />
	        		Has Pool: ${(Amenities.hasPool)?c}<br />
	        	</p>
	        	
	        </div>
	        <br />
        
        </#if>
        
        <#if ReviewsCheck != "null">
        
	        <div class="section">
	        	
	        	<h3>Reviews</h3><hr>
	        	
	        	<p>
	        		<#list Reviews as r>
	            
	            		Number of Stars: ${r.numStars}<br />
	            		Title: ${r.title}<br />
	            		Review: ${r.description} <br />
	            		
	            		<br />
	            
	          	   </#list>
	        	</p>
	        	
	        </div>
	    	<br />
    	
    	</#if>
    	
    </div>
    </div>
    </div>
    </div>
</body>
</html>
