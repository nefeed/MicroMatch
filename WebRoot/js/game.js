// JavaScript Document
$(document).ready(function(e) {
    $('#em1').click(function(){
		$(this).addClass('subject_current');
		$('#em2').removeClass('subject_current');
		$('#uncheck').show() ;
		$('#checked').hide() ;
		});
	$('#em2').click(function(){
		$(this).addClass('subject_current');
		$('#em1').removeClass('subject_current');
		$('#uncheck').hide() ;
		$('#checked').show() ;
		});
});