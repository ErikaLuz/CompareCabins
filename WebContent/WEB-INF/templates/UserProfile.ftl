<!DOCTYPE html>
<html>

<head>
    
    <meta charset="UTF-8">
	<title>Compare Cabins</title>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>${User.username}'s Profile</title>

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

	<#if viewOrEdit == "view">
	
		 <div class="container">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<h1 class="panel-title text-center">${User.username}'s Profile</h1>
			 			</div>


             <div class="panel-body">
		     <h3 class="text-center">Name: ${User.firstName} ${User.lastName}</h3><br />
		 	 <h3 class="text-center">Email: ${User.email}<h3><br />
		 	 <form action="ViewEditUserProfile" method="post" class="text-center">
             <button name="editUser" class="btn text-center">Edit Profile Information</button>
	 	     </form>
             
             <#if ReviewsCheck != "null">
             <hr>
	 	 <h2 class="text-center">${User.username}'s Cabins Reviews </h2>
	 
	 	 <#list Reviews as r>
		 	
			 	 <h3>${(r.title)!"DefaultTitle"} : ${r.numStars} Stars</h3><br />
			     ${r.description}
			 	 <hr>
		 	 <br / >
	 	
	 	 </#list>
	 	 </div>
         </div>
		</#if>
	<#elseif viewOrEdit == "edit">
    
    
    <div class="container">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<h3 class="panel-title text-center">Edit ${User.username}'s Profile</h3>
			 			</div>
			 			<div class="panel-body">
			    		<form action="ViewEditUserProfile" method="post">
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                             <input type="text" name="newFN" id="newFN" class="form-control input-sm" placeholder="${User.firstName}">
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="text" name="newLN" id="newLN" class="form-control input-sm" placeholder="${User.lastName}">
			    					</div>
			    				</div>
			    			</div>

			    			<div class="form-group">
			    				<input type="text" name="newEmail" id="newEmail" class="form-control input-sm" placeholder="${User.email}">
			    			</div>

			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="text" name="newUsername" id="newUsername" class="form-control input-sm" placeholder="${User.username}">
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="password" name="newPassword" id="newPassword" class="form-control input-sm" placeholder="${User.password}">
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<input type="submit" name="updateUser" value="Submit" class="btn btn-info btn-block">
			    		
			    		</form>
			    	</div>
	    		</div>
    		</div>
    	</div>
    </div>
	 	 
	<#elseif viewOrEdit == "updateConfirmation">
	<div class="container">
    <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<h1 class="panel-title text-center">Your profile has been edited</h1>
			 			</div>


             <div class="panel-body">
		
		<h4 class="text-center">Username: ${User.username}<h4><br />
		<h4 class="text-center">Password: ${User.password}<h4><br />
		<h4 class="text-center">First Name: ${User.firstName}<h4><br />
		<h4 class="text-center">Last Name: ${User.lastName}<h4><br />
		<h4 class="text-center">Email: ${User.email}<h4><br />	 
        </div>
        </div>
        </div>
	</#if>
 	 
</body>
</html>
