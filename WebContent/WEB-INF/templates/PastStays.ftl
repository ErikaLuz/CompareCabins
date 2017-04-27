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
<#include "Header.ftl">
	<center><h1>${User.username} Past Stays</h1></center>
	
	<#list PastStays as PS>
	
		<div class = "container" id="${PS.getRentRecord().id}">
		
			<img src="${PS.getCabinPicture().filePath}" alt="thumbnail" style="width:410px;height:200px;display:block;margin:auto;float:left;vertical-align:middle">
		
			<h2>${PS.getCabin().title}</h2>
			<hr>
			
			<p>
				Description: ${PS.getCabin().description}<br />
				Start Date: ${StartDate}<br />
				End Date: ${EndDate}<br />
				
				<form action="PastStaysReview" method="post">
           	    <button name="addReview" class="btn">ADD REVIEW</button>
                </form>
				
			</p>
            
		</div>
		
		<br />
	
	</#list>
	

</body>
</html>
