<!DOCTYPE html>
<html>

<head>
    
    <meta charset="UTF-8">
	<title>Compare Cabins</title>
	
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

	
	
		<center><h1>${User.username} Past Stays</h1></center>
	
	<#list PastStays as PS>
	
		<div class = "container">
		
			<img src="placeholder_600x400.svg" alt="thumbnail" style="width:410px;height:200px;display:block;margin:auto;float:left;vertical-align:middle">
		
			<h2>${PS.getCabin().title}</h2>
			<hr>
			
			<p>
				Description: ${PS.getCabin().title}<br />
				Start Date: ${PS.getRentRecord().startDate}<br />
				End Date: ${PS.getRentRecord().endDate}<br />
				
				<form action="PastStaysReview" method="post">
           	    <button name="PastStaysReview" class="btn">ADD REVIEW</button>
           	    <button name="PastStaysReview" class="btn">EDIT REVIEW</button>
                </form>
				
			</p>
            
		</div>
		
		<br />
	
	</#list>
	

</body>
</html>
