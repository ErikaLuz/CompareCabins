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
      <div class="container">
            <img class="img-responsive" src="http://placehold.it/1500x300">
            <form action="TestServlet" method="post"> 
                <button name="TestUser" class="btn">TEST CLASSES</button>
            </form>
           <br /><br />
            <form action="CabinListing" method="post">
                <button name="cabinListing" class="btn">CABIN LISTING</button>
            </form>
           <br /><br />
           	<form action="UserCabinListing" method="post">
                <button name="userCabinListing" class="btn">USER CABIN LISTING</button>
            </form>
           <br /><br />
           	<form action="AddCabin" method="post">
                <button name="goToCabin" class="btn">GO TO ADD CABIN</button>
            </form>
            <br /><br />
           	<form action="ViewEditUserProfile" method="post">
                <button name="viewUser" class="btn">VIEW USER</button>
            </form>
            <br /><br />
           	<form action="PastStaysReview" method="post">
                <button name="pastStays" class="btn">TEST PAST STAYS</button>
            </form>
 		   
      </div>
  </body>
</html>