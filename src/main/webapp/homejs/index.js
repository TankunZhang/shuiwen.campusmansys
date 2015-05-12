$("document").ready(function() {
	
	$.ajax({
		type : "POST" ,
		contentType : "application/json;charset=utf-8" ,      				
		url : "findallschools",
		dataType : "json" ,       					
		success : function(resultdata){
			var data = validJson(resultdata);
			if(data!=0){
				$.each(data, function (i, item) { 
					jQuery("#indexxiaoqu").append("<option value="+ item.id+">"+ item.suoxie+"</option>");
				});
			}
		}
	});
	

	
	$('#indexxiaoqu').change(function(){
		$("#shouye").click();
	});
	
    
	function validJson(resultdata){
		if(resultdata.status==1){
			return resultdata.data;
		}else{
			alert("系统等待："+resultdata.info);
			return 0;
		}
		
	}
});