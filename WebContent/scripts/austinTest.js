

window.onload = function () {
//	document.getElementById("day1").onclick = dayClick;
	$('#calendarTable td').click( dayClick );
	$('#clear').click( clearClick );
	loadCalendar
};

/*function ajaxCall() {
	var ajax = new XMLHttpRequest();
	
	ajax.onreadystatechange = function() {
		if( ajax.readyState == 4)
			if( ajax.status == 200 ) {
				
			}
	}
	
}
*/
function loadCalendar() {
	jQuery.getJSON( "AustinTest", function( data ) {
		
	})
}

function dayClick() {
	jQuery.getJSON( "AustinTest" , function( data ) {
		$('p').text(data.weekDay);
	});
}

function clearClick() {
	$('p').text("");
}