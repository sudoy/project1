$(function() {
	$('#alert').click(function(e) {
		$('.errorMessage').removeClass('errorMessage');
		$('.errorFrame').removeClass('errorFrame');
	});
	$('#allcheck').click(function(e) {
		$('input[type="checkbox"]').prop('checked', true);
	});
	$('#alluncheck').click(function(e) {
		$('input[type="checkbox"]').prop('checked', false);
	});
});