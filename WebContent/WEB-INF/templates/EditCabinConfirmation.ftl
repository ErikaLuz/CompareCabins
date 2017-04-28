<!DOCTYPE html>
<html>

<head>
    
    <meta charset="UTF-8">
	<title>Compare Cabins</title>
    
</head>

<body>
    
	<#if add = "add">
	
		<h1>Added your feature: </h1>
		<hr>
		${Feature.featureString}
	
	<#elseif add = "delete">
	
		<h1>Deleted your feature: </h1>
		<hr>
		
		${Feature.featureString}
	
	<#elseif add = "edit">
	
		<h1>Edit this feature:</h1>
		${Feature.featureString}<br/>
		<form action="EditCabin?featureId=${Feature.id}" method="post">
		<input type="text" name="editFeatureString" value="${Feature.featureString}" /><br />
		<button name="submitEditedFeature" class="btn">Submit</button><br/>
		</form>
		
	<#elseif add = "submitEditFeature">
	
		<h1>Your new feature is:</h1>
		${Feature.featureString}<br/>
	
	<#elseif add = "availability">
	
		<h1> You have just added this availability:</h1>
		
		Date: ${Date}</br> 
		Price:${Availability.price}</br>
	
	<#elseif add = "picture">
	
		<h1> This photo was deleted </h1>
		
<!--		<img src="${CP.filePath}" alt="cabin" style="width:300px;height:300px;"> -->
	
	</#if>
        
</body>
</html>
