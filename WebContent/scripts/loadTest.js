var cabinDataJson;
window.onload = function () {
	console.log("page loaded");
	jQuery.getJSON( "LoadCabinsJSON" , function( data ) {
		console.log("getJSON entered");
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
			 cabinDiv.mouseover( cabinDivMouseOver );
			 cabinDiv.mouseleave( cabinDivMouseLeave );
			 
			 jQuery("#results").append( cabinDiv );
		 }
		 cabinDataJson = data;
		 
		
	});
	jQuery("#startDate").datepicker();
	jQuery("#endDate").datepicker();
	$("#searchButton").click( searchButtonClick );

}

function searchButtonClick ( event ) {
	console.log("searchButtonClick event entered");
	/*
	var hasLake = jQuery("input:checkbox[name=hasLake]").val();
	var hasRiver= jQuery("input:checkbox[name=hasRiver]").val();
	var hasWifi = jQuery("input:checkbox[name=hasWifi]").val();
	var hasAirConditioning= jQuery("input:checkbox[name=hasAirConditioning]").val();
	var hasWasherDryer= jQuery("input:checkbox[name=hasWasherDryer]").val();
	var hasHotTub = jQuery("input:checkbox[name=hasHotTub]").val();
	var allowsPets = jQuery("input:checkbox[name=allowPets]").val();
	var allowsSmoking = jQuery("input:checkbox[name=allowsSmoking]").val();
	var startDate = jQuery("#startDate").val();
	var endDate = jQuery("#endDate").val();
	*/
	 event.preventDefault();
	var header = $("#results h2");
	jQuery("#results").empty().append(header);
	
	jQuery.getJSON( "LoadCabinsSearchJSON?" , $(this.form).serialize(), function( data ) {
		 for( var i = 0; i < data.cabins.length; i++)
		 {				 
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
			 cabinDiv.mouseover( cabinDivMouseOver );
			 cabinDiv.mouseleave( cabinDivMouseLeave );
		 
			 jQuery("#results").append( cabinDiv );
		 }
		 
		 cabinDataJson = data;
		 
	});
		 event.preventDefault();
	
}

function cabinDivClick ( ) {
	location.href = "GuestCabinListing?" + jQuery.param( { cabinId: jQuery(this).data("cabinId") } );
}

function cabinDivMouseOver ( ) {
	$(this).addClass( "mouseOver" );
}

function cabinDivMouseLeave ( ) {
	$(this).removeClass( "mouseOver" );
}
