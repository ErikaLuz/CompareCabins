<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Compare Cabins</title>

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

	<form action="Search" method="get">
		<div>
			<input type="checkbox" name="hasLake" />Lake<br> <input
				type="checkbox" name="hasRiver" />River<br> <input
				type="checkbox" name="hasPool" />Pool<br> <input
				type="checkbox" name="hasHotTub" />Hot tub<br> <input
				type="checkbox" name="hasWifi" />WiFi<br> <input
				type="checkbox" name="hasAirConditioning" />Air Conditioning<br>
			<input type="checkbox" name="hasWasherDryer" />Washer Dryer<br>
			<input type="checkbox" name="allowsPets" />Allows Pets<br> <input
				type="checkbox" name="allowsSmoking" />Allows Smoking<br> <input
				type="date" name="startAvailability" />Start Date(2000-01-01
			format)<br> <input type="date" name="endAvailability" />End
			Date(2000-01-01 format)<br> <input id="searchButton"
				type="submit" value="Submit">
		</div>
	</form>

	<div id="results">
		<h2>cabins:</h2>
	</div>

</body>
</html>