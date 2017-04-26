<!DOCTYPE html>
<html>

<head>
    
    <meta charset="UTF-8">
	<title>Compare Cabins</title>
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
<nav class="navbar navbar-inverse">
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
			    		<h3 class="panel-title text-center">${User.username}</h3>
			 			</div>


             <div class="panel-body">
		     <h3 class="text-center">Name: ${User.firstName} ${User.lastName}</h2><br />
		 	 <h3 class="text-center">Email: ${User.email}<h2><br />
		 	 <form action="ViewEditUserProfile" method="post">
             <button name="editUser" class="btn text-center">Edit Profile Information</button>
	 	     </form>
             <hr>
	 	 <h2 class="text-center">${User.username}'s Cabins Reviews: </h2>
	 
	 	 <#list Reviews as r>
	 	
		   	 <div class = "container">
		 	
			 	 <h3>${(r.title)!"DefaultTitle"}</h3><br />
			 	 ${r.numStars} Stars<br />
			 	 Review: ${r.description}<br />
			 	 <hr>
		 		 
		 	 </div>
		 	 </div>
		 	 <br / >
	 	
	 	 </#list>
	 	 
         </div>
	
	<#elseif viewOrEdit == "edit">
	<div class="container">
		 <center><h1>Edit ${User.username} Profile</h1></center><br/>
		  
		 <form action="ViewEditUserProfile" method="post">
		 
		 	Username: <input type="text" name="newUsername" placeholder="${User.username}" /> <br/><br/>
		 	Password: <input type="text" name="newPassword" placeholder="${User.password}" /> <br/><br/>
		 	First Name: <input type="text" name="newFN" placeholder="${User.firstName}" /> <br/><br/>
			Last Name: <input type="text" name="newLN" placeholder="${User.lastName}" /> <br/><br/>
			Email: <input type="text" name="newEmail" placeholder="${User.email}" /> <br/><br/><br/>
		 
	 	 	<center><button name="updateUser" class="btn">Submit</button></center>
	 	 		 	 		 
	 	 </form>
	 	 
	<#elseif viewOrEdit == "updateConfirmation">
	
		<h1>Your profile has been edited: </h1><br/>
		
		Username: ${User.username} <br/>
		Password: ${User.password} <br/>
		First Name: ${User.firstName} <br/>
		Last Name: ${User.lastName} <br/>
		Email: ${User.email} <br/>	 
        </div>
	</#if>
 	 
</body>
</html>
