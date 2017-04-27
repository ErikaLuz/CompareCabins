<!DOCTYPE html>
<html>

<head>
    
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    
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
    
    <title>Your Past Stays</title>
    
</head>

<body>
    
    <#include "Header.ftl">
	
    <div class="container">
    <div class="panel panel-default">

    <div class="panel-heading">
        <h1 class="text-center">${User.username}'s Past Stays</h1>
    </div>
        
    <div class="panel-body">
        
        <#list PastStays as PS>

            <div class ="row">
            <div class="col-sm-6">
            
                <img src="${PS.getCabinPicture().filePath}" alt="thumbnail" style="width:410px;height:200px;display:block;margin:auto;float:left;vertical-align:middle">
                
            </div>
                
            <div class="col-sm-6">
            
                <h2 class="text-center">${PS.getCabin().title}</h2>
                <hr>

                <p>
                    <center>
                    ${PS.getCabin().description}<br /><br />
                    Day of arrival: ${StartDate}<br />
                    Day of departure: ${EndDate}<br />
                    </center>
                </p>

                <form action="PastStaysReview?rentRecordId=${PS.getRentRecord().id}" method="post">
                    <button name="addReview" class="btn center-block">ADD REVIEW</button>
                </form>
                <br/>
           
            </div>
            </div>
        
        </#list>
        
    </div>
    </div>
    </div>

</body>
</html>
