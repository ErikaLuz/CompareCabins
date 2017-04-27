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
		
		<h1> test: you review has been submitted </h1>
		
		<h2>Info about your stay:</h2>
		
		Total Price: ${RentRecord.totalPrice}<br/>
		Start Date: ${StartDate} <br/>
		End Date: ${EndDate} <br/>
		
		<hr>
		
		<h2>Info about your review: </h2>
		
		NumStars: ${Review.numStars}<br/>
		Title: ${Review.title}<br/>
		Review: ${Review.description}<br/>

</body>
</html>
