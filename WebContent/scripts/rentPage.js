/**
 * 
 */

window.onload = function () {
	$("#checkPriceButton").click( checkPriceButtonClick );
	jQuery("#startDate").datepicker();
	jQuery("#endDate").datepicker();


}

function checkPriceButtonClick ( event ) {
	
	event.preventDefault();
	jQuery.getJSON( "CheckPriceJSON" , $(this).form.serialize(), function( data ) {
		
		if(data.available) {		
			jQuery("#price").text("Total Price: " + data.totalPrice);
			$("#price").css("font-size", "32pt" );
			$("#price").css("color", "green");
			$("#rentButton").css("display", "inline" );
		} else {
			jQuery("#price").text("The cabin is not available for those Dates!")
			$("#price").css("font-size", "22pt" );
			$("#price").css("color", "red");
			$("#rentButton").css("display", "none" );
		}
		 
		 event.preventDefault();
		 
		
	});
}

