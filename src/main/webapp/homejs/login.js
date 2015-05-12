$("document").ready(function() {
	$('#submit').click(function() {
		var username = $("#username").val();
		var password = $("#password").val();	
		
		$.ajax({
			type : "POST" ,
			contentType : "application/json" ,      				
			url : "validUser?username=" + username + "&password=" + password,
			dataType : "json" ,       					
			success : function(result){
				goNextPage(result);
			}     			
		});
	});
	
	function goNextPage(data){
		if(data.realguanliyuan==1){
			location.href="index.html";
		}else{
			alert("用户名密码错误~~")
		}
	}
});