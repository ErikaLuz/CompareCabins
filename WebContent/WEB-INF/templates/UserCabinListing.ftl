<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Your Profile</title>

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
			    		<h1 class="text-center">Your Cabins</h1>
			 			</div>


             <div class="panel-body">
	
	<form action="AddCabin" method="post">
    <button name="goToCabin" class="btn center-block">ADD CABIN</button>
    </form>
    <br />
    <hr>
    <br />

	<#list Groups as group>
	
		<div class = "cabinListing">
        <div class="row">
        <div class="col-xs-5 col-sm-5 col-md-5">
			<a href="http://placehold.it"><img src="http://placehold.it/600x400" class="img-rounded img-responsive" alt="thumbnail"></a>
		</div>
        
        <div class="col-xs-7 col-sm-7 col-md-7">
			<h2 class="text-center">${group.getCabin().title}</h2>
			<hr>
			
			<p>
				Description: ${group.getCabin().description}<br />
				Address: ${group.getCabin().address}<br />
				City: ${group.getCabin().city}<br />
				State: ${group.getCabin().state}<br />
			</p>
        </div>
        </div>
        </div>
		
		<br />
	
	</#list>
    </div>
    </div>
    </div>
</body>
</html>