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

			<h2>Insert Cabin Listing code minus calendar here</h2>
			<br />
			<hr>
			<br />
			
			
			<h1>Info about your stay:</h1>
			
			Total Price: ${RentRecord.totalPrice}<br/>
			Start Date: ${RentRecord.startDate} <br/>
			End Date: ${RentRecord.endDate}<br/>
			
			<hr>
			
			<form action="PastStaysReview" method="post">
			[Numstars - do we want to do actual stars?]
		
			Number of Stars: <input type="text" name="numStars" placeholder="enter number of stars" /><br />
			Title: <input type="text" name="title" placeholder="enter a title for your review" />
			Review: <input type="text" name="review" placeholder="enter your review" />
			
			<button name="submitReview" class="btn">SUBMIT</button>
			</form>

</body>
</html>
