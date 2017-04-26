var cabinDataJson;
window.onload = function () {
	jQuery.getJSON( "LoadCabinsJSON" , function( data ) {
		 for( var i = 0; i < data.cabins.length; i++){
			 var cabinDiv = jQuery("<div/>");
			 var imgElement = jQuery("<img/>");
			 var h3Element = jQuery("<h3/>");
			 var pElement = jQuery("<p/>");
			 
			 imgElement.attr( "src", data.cabins[i].primaryPhotoPath );
			 imgElement.attr( "width", "250" );
			 imgElement.attr( "height", "250" );
			 imgElement.addClass("thumbnail");
			 
			 h3Element.text( data.cabins[i].title );
			 pElement.text( data.cabins[i].description );
			 
			 cabinDiv.append(imgElement);
			 cabinDiv.append(h3Element);
			 cabinDiv.append(pElement);
			 cabinDiv.data("cabinId", data.cabins[i].cabinId);
			 
			 cabinDiv.click( cabinDivClick );
			 
			 jQuery("#results").append(cabinDiv);
		 }
		 cabinDataJson = data;
		 
		
	});
}

function cabinDivClick ( ) {
	location.href = "SearchCabinListing?" + jQuery.param( { cabinId: jQuery(this).data("cabinId") } );
}
