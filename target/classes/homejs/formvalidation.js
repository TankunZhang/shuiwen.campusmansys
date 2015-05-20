
$(document).ready(function(){
	
	$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
	
	$('select').select2();
	
	// Form Validation
    $("#formaddkemu").validate({
		rules:{
			required:{
				required:true
			},
			km_mingzi:{
				required:true
			},
			xueqizhi:{
				required:true,
				number: true
			},
			morenkeshi:{
				required:true,
				number: true
			},
			danjia:{
				required:true,
				number: true
			}
		},
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.control-group').addClass('error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.control-group').removeClass('error');
			$(element).parents('.control-group').addClass('success');
		}
	});
	
});
