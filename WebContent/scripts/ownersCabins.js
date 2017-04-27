
window.onload = function () {
	
	jQuery("div.cabinListing").bind( {
		mouseover: cabinDivMouseOver,
		mouseleave: cabinDivMouseLeave,
		click: cabinDivClick
	});
	
}

function cabinDivClick ( ) {
	location.href = "OwnerCabinListing?" + jQuery.param( { cabinId: jQuery(this).attr("id") } );
}

function cabinDivMouseOver ( ) {
	$(this).addClass( "mouseOver" );
}

function cabinDivMouseLeave ( ) {
	$(this).removeClass( "mouseOver" );
}
