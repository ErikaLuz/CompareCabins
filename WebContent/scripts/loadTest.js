
window.onload = function () {
	jQuery.getJSON( "LoadCabinsJSON" , function( data ) {
		 for( var i = 0; i < data.cabins.length; i++){
			 var cabinDiv = jQuery("<div/>");
			 var imgElement = jQuery("<img/>");
			 var h3Element = jQuery("<h3/>");
			 var pElement = jQuery("<p/>");
			 
			 imgElement.attr( "src", data.cabins[i].primaryPhotoPath );
			 imgElement.attr( "width", "120" );
			 imgElement.attr( "height", "120" );
			 imgElement.addClass("thumbnail");
			 
			 h3Element.text( data.cabins[i].title );
			 pElement.text( data.cabins[i].description );
			 
			 cabinDiv.append(imgElement);
			 cabinDiv.append(h3Element);
			 cabinDiv.append(pElement);
			 
			 cabinDiv.click( function () {
				 location.href = "SearchCabinListing?" + jQuery.param( { cabinId: data.cabins[i].id } );
			 } );
			 
			 jQuery("#results").append(cabinDiv);
		 }
		 
		
	});
}
