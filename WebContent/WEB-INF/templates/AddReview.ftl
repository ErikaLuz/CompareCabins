<!DOCTYPE html>
<html>

<head>
    
    <meta charset="UTF-8">
	<title>Compare Cabins</title>
    
</head>

<body>
<#include "Header.ftl">

			<h2>Insert Cabin Listing code minus calendar here</h2>
			<br />
			<hr>
			<br />
			
			<h2>Info about your stay:</h2>
			
			Total Price: ${Group.getRentRecord().totalPrice}<br/>
			Start Date: ${Group.startDate} <br/>
			End Date: ${Group.endDate}<br/><br />
			
			<hr>
			
			<h2> Leave your review: </h2>
			
			<form action="PastStaysReview?rentRecordId=${Group.getRentRecord().id}" method="post">
		
			Number of Stars: <input type="text" name="numStars" placeholder="enter number of stars" /><br />
			Title: <input type="text" name="title" placeholder="enter a title for your review" /><br />
			Review: <input type="text" name="review" placeholder="enter your review" /><br /><br />
			
			<button name="submitReview" class="btn">SUBMIT</button>
			</form>

</body>
</html>
