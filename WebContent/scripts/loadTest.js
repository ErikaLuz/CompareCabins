
window.onload = function {
	jQuery.getJSON( "LoadCabinsJSON" , function( data ) {
		$('p').text(data.weekDay);
	});
}