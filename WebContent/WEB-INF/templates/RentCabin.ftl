<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Rent ${Cabin.title}</title>

    <!-- Bootstrap -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">
    <script src="scripts/rentPage.js" type="text/javascript"></script>
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
             
        <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6">
             
             
        <#if CPCheck != "null">
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Wrapper for slides -->
    <div class="carousel-inner">
      <div class="item active">
      <img src="${PriorityPicture.filePath}" class="img-rounded img-responsive" alt="thumbnail">
      </div>
				<#list CabinPictures as cp>
				<div class="item">
				<img src="${cp.filePath}" class="img-rounded img-responsive" alt="thumbnail">
				</div>
	            </#list>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
</div>
		
		<#else>
			<img src="http://placehold.it/600x400" class="img-rounded img-responsive" alt="thumbnail">
		
		</#if>
		
		
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
        <h1 class="text-center">${Cabin.title}</h1>
        <hr>
        <p class="text-center">${Cabin.description}</p>
        </div>
        </div>
        <hr>
        <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6">

            <p class="text-center">
                Street Address : ${Cabin.address}<br/> 
                City : ${Cabin.city}<br/> 
                State : ${Cabin.state}<br/> 
            </p>

        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
			<form>
			  Start Date:
			  <input type="date" id="startDate" name="startDate">
			  End Date:
			  <input type="date" id="endDate" name="endDate">
			  <input type="submit" class="btn" id="rentButton" value="Rent"></input>
			  <button name="checkPrice" id="checkPrice" class="btn btn-block">Check Price!</button>
			</form>
				<span id="price"></span>
			
        </div>
        </div>
        <hr>

    </div>
    </div>
    </div>
    </div>
</body>
</html>