<!DOCTYPE html>
<html>

<head>
    
    <meta charset="UTF-8">
	<title>Compare Cabins</title>
    
</head>

<body>
		<#include "Header.ftl">
		
		<h1> test: you review has been submitted </h1>
		
		<h2>Info about your stay:</h2>
		
		Total Price: ${Group.getRentRecord().totalPrice}<br/>
		Start Date: ${Group.startDate} <br/>
		End Date: ${Group.endDate} <br/>
		
		<hr>
		
		<h2>Info about your review: </h2>
		
		NumStars: ${Group.getReview().numStars}<br/>
		Title: ${Group.getReview().title}<br/>
		Review: ${Group.getReview().description}<br/>

</body>
</html>
