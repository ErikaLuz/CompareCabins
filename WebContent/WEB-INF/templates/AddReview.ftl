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

			<div id="${RentRecord.id}"}></div>

			<h2>Insert Cabin Listing code minus calendar here</h2>
			<br />
			<hr>
			<br />
			
			
			<h2>Info about your stay:</h2>
			
			Total Price: ${RentRecord.totalPrice}<br/>
			Start Date: ${StartDate} <br/>
			End Date: ${EndDate}<br/><br />
			
			<hr>
			
			<form action="PastStaysReview?rentRecordId=${RentRecord.id}" method="post">
		
			Number of Stars: <input type="text" name="numStars" placeholder="enter number of stars" /><br />
			Title: <input type="text" name="title" placeholder="enter a title for your review" /><br />
			Review: <input type="text" name="review" placeholder="enter your review" /><br /><br />
			
			<button name="submitReview" class="btn">SUBMIT</button>
			</form>

</body>
</html>
