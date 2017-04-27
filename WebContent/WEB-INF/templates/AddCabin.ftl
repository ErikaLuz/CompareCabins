<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Add Your Cabin</title>

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
        <div class="panel-heading">
			 <h1 class="panel-title text-center">Add a cabin</h1>
        </div>
        <div class="panel-body">
             <div class="row">
                <div class="col-sm-12 center-block">
                    <form action="AddCabin" method="post">
                      <img src="placeholder_600x400.svg" alt="thumbnail" style="width:500px;height:300px;padding-left:10px;display:block;margin:auto;">
			  					<button name="upload" class="btn btn-block">Upload Photos!</button>
                </div>
            </div>
            <hr>
  	        <br>
            <div class="container-fluid bg-3 text-center">    
                <div class="row">
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label for="description">Choose a title for your cabin</label>
	                        <input type="text" class="form-control" name="title" placeholder="Name your cabin" />
                        </div>
                    </div>
                    <div class="col-sm-4"> 
                        <div class="form-group">
                            <label for="description">Describe your cabin</label>
                            <textarea class="form-control" id="description" name="description" rows="8"></textarea>
                        </div>
                    </div>
                    <div class="col-sm-4"> 
                        <div class="form-group">
                            <label for="location">Add your cabin's location</label><br />
                            Address: <input type="text" class="form-control" name="address" placeholder="ex: 123 Broad Street" /><br />
                            City: <input type="text" class="form-control" name="city" placeholder="ex: Athens" /><br />
                            State: <input type="text" class="form-control" name="state" placeholder="ex: Georgia" /><br />
                        </div>
                    </div>
                </div>
            </div>
            <br />
            <br />
            <div class="container-fluid bg-3 text-center">    
                <div class="row">
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label for="more info">Add more info about your cabin</label><br />
 	                          Bedroom Count: <input type="number" class="form-control" name="bedCount" placeholder="enter number of bedrooms" /><br />
 	                          Bathroom Count: <input type="number" class="form-control" name="bathCount" placeholder="enter number of bathrooms" /><br />
 	                          Maximum Occupancy:<input type="number" class="form-control"  name="maxOcc" placeholder="enter maximum occupancy" /><br />
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label for="features">Tell us some features</label><br />
 	                          <input type="text" class="form-control" name="features" placeholder="Examples: Rated #1 Cabin in town, 50-inch TV in living room, Amazing view of the sunset" /><br />
                        </div>
                    </div>
                    <div class="col-sm-4"> 
                        <label for="amenities">Tell us the cabin's amenities</label><br />
                            Lake?<input type = "checkbox" name="amenities" value="hasLake">
                            River?<input type = "checkbox" name="amenities" value="hasRiver">
                            Pool?<input type = "checkbox" name="amenities" value="hasPool"><br />
                            Hot Tub?<input type = "checkbox" name="amenities" value="hasHotTub">
                            WiFi?<input type = "checkbox" name="amenities" value="hasWifi">
                            Air Conditioning?<input type = "checkbox" name="amenities" value="hasAirConditioning"><br />
                            Washer & Dryer?<input type = "checkbox" name="amenities" value="hasWasherDryer">
                            Allow Pets?<input type = "checkbox" name="amenities" value="allowsPets">
                            Allow Smoking?<input type = "checkbox" name="amenities" value="allowsSmoking"><br />
                    </div>
                </div>
            </div>
            <hr>
            <div class="text-center"> 
                <button name="addCabin" class="btn center-block">Add Cabin</button>
            </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
