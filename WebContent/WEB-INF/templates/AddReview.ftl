<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Add a Review</title>

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
<#include "Header.ftl">
<div class="container">
        	<div class="panel panel-default">
             <div class="panel-body">
			
			<h2 class="text-center">Info about your stay:</h2>
			<p class="text-center">
				Total Price: ${Group.getRentRecord().totalPrice}<br/>
				Start Date: ${Group.startDate} <br/>
				End Date: ${Group.endDate}<br/><br />
			</p>
			
			<hr>
			
			<h2 class="text-center"> Leave your review: </h2>
			<div class="form-group text-center">
				<form action="PastStaysReview?rentRecordId=${Group.getRentRecord().id}" method="post">
			
				Number of Stars: <input type="text" class="form-control" name="numStars" placeholder="enter number of stars" /><br />
				Title: <input type="text" class="form-control" name="title" placeholder="enter a title for your review" /><br />
				Review: <textarea class="form-control" id="review" name="review" placeholder="enter your review" rows="8"></textarea><br /><br />
				
				<button name="submitReview" class="btn">SUBMIT</button>
				</form>
			</div>
</div>
</div>
</div>

</body>
</html>
