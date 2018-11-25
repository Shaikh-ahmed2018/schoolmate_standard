$(document).ready(function() {

	$('.pagebanner').hide();
	$('.pagelinks').hide();
});

function moreEvents() {
	window.location.href="menuslist.html?method=viewMoreEvents";
}

function moreMeetings() {
	window.location.href = "menuslist.html?method=viewMoreMeeting";
}

function moreRemainders() {
	window.location.href = "menuslist.html?method=viewMoreReminder";
}