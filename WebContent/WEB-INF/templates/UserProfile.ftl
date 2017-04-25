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

	<#if viewOrEdit == "view">
	
		 <center>
		 
			 <h1>${User.username}</h1>
		     Name: ${User.firstName} ${User.lastName}<br />
		 	 Email: ${User.email}<br />
		 	
		 	 <br/><br/>
	 	
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
	 	 
	 	 <form action="ViewEditUserProfile" method="post">
	 	 	<center><button name="editUser" class="btn">Edit Profile</button></center>	 	 		 
	 	 </form>
	
	<#elseif viewOrEdit == "edit">
	
		 <center><h1>Edit ${User.username} Profile</h1></center><br/>
		  
		 <form action="ViewEditUserProfile" method="post">
		 
		 	Username: <input type="text" name="newUsername" placeholder="${User.username} - Enter New Username" /> <br/>
		 	Password: <input type="text" name="newPassword" placeholder="${User.password} - Enter New Password" /> <br/>
		 	First Name: <input type="text" name="newFN" placeholder="${User.firstName} - Enter New First Name" /> <br/>
			Last Name: <input type="text" name="newLN" placeholder="${User.lastName} - Enter New Last Name" /> <br/>
			Email: <input type="text" name="newEmail" placeholder="${User.email} - Enter New Email" /> <br/><br/>
		 
	 	 	<center><button name="updateUser" class="btn">Submit</button></center>
	 	 		 	 		 
	 	 </form>
	 	 
	<#elseif viewOrEdit == "updateConfirmation">
	
		<h1>Your profile has been edited: </h1><br/>
		
		Username: ${User.username} <br/>
		Password: ${User.password} <br/>
		First Name: ${User.firstName} <br/>
		Last Name: ${User.lastName} <br/>
		Email: ${User.email} <br/>	 
		
	</#if>
 	 
</body>
</html>
