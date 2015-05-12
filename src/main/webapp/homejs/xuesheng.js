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
					jQuery("#xiaoquid").append("<option value="+ item.id+">"+ item.suoxie+"</option>");
				});
			}
		}
	});
	
	$('#btnaddSubmit').click(function(){
		var studentData = JSON.stringify($('#formEditXuesheng').serializeArray());
//		alert(studentData);
		$.ajax({
			type : "POST" ,
			contentType : "application/json" ,      				
			url : "addstudent",
			data: studentData,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=-1){
					alert("学生添加成功");
					$('#btneditCance').click();
					$("#content").load("xuesheng.html");
				}
			}
		});
	});
	

	$('.input-mask-date').mask('9999-99-99');
	
val = xiaoquid = $('#indexxiaoqu').val();
	
	$.ajax({
		type : "POST" ,
		contentType : "application/json;charset=utf-8" ,      				
		url : "findallstudents?xiaoquid="+xiaoquid,
		dataType : "json" ,       					
		success : function(resultdata){
			var data = validJson(resultdata);
			if(data!=0){
				showingtable(data);
			}
		}
	});
	
	function showingtable(data){
		var stuttr = $("#studentsInfoTR");
		$.each(data, function(index,item){                              
            //克隆tr，每次遍历都可以产生新的tr                              
              var clonedTr = stuttr.clone();
              clonedTr.attr('id',item.id);
              var _index = index;  
             
              //循环遍历cloneTr的每一个td元素，并赋值  
              clonedTr.children("td").each(function(inner_index){  
                 
                     //根据索引为每一个td赋值  
                           switch(inner_index){  
                                 case(0):   
                                    $(this).html(item.id);  
                                    break;  
                                 case(1):  
                                    $(this).html(item.xingming);
                                    break;  
                                case(2):  
                                    $(this).html(item.xingbie);  
                                    break;  
                                case(3):  
                                    $(this).html(item.cs_riqi);  
                                    break;  
                                case(4):  
                                    $(this).html(item.dizhi);  
                                    break;  
                                case(5):  
                                    $(this).html(item.gd_dianhua);  
                                    break;  
                                case(6):  
                                    $(this).html(item.mq_shouji);  
                                    break;  
                                case(7):  
                                    $(this).html(item.fq_shouji);  
                                    break;  
                                case(8):  
                                    $(this).html(item.youeryuan);  
                                    break;  
                                case(9):  
                                    $(this).html(item.zc_shijian);  
                                    break;  
                                case(10):  
                                    $(this).html(item.beizhu);  
                                    break;  
                          }//end switch                          
           });//end children.each  
         
          //把克隆好的tr追加原来的tr后面  
          clonedTr.insertAfter(stuttr);  
       });//end $each  
		$("#studentsInfoTR").hide();
		$("#studentsInfoTable").show();
		$("#studentsInfoTable").dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers",
			"sDom": '<""l>t<"F"fp>'
		});
	}
	
	$('#studentsInfoTable tbody').on( 'click', 'a', function () {
		$('#btndeleteSubmit').show();
		$('#btneditSubmit').show();
		$('#btnaddSubmit').hide();
        var studentid = $(this).parents('tr').eq(0).attr('id');
        $.ajax({
    		type : "POST" ,
    		contentType : "application/json;charset=utf-8" ,      				
    		url : "findstudentbyid?id="+studentid,
    		dataType : "json" ,       					
    		success : function(resultdata){
    			var data = validJson(resultdata);
    			if(data!=0){
    				showeditstudent(data);
//    				$.each(data, function (i, item) { 
//    					jQuery("#xiaoquid").append("<option value="+ item.id+">"+ item.suoxie+"</option>");
//    				});
    			}
    		}
    	});
        
    } );
	function showeditstudent(data){
		
//		$('#btneditCance').click();
		$("#id").val(data.id);
		$("#xiaoquid").val(data.xiaoquid);
		$('#xingming').val(data.xingming);
		$('#xingbie').val(data.xingbie);
		$('#cs_riqi').val(data.cs_riqi);
		$('#dizhi').val(data.dizhi);
		$('#gd_dianhua').val(data.gd_dianhua);
		$('#mq_shouji').val(data.mq_shouji);
		$('#fq_shouji').val(data.fq_shouji);
		$('#youeryuan').val(data.youeryuan);
		$('#beizhu').val(data.beizhu);
		$('#zhaopian').val(data.zhaopian);
		$('#jingli').val(data.jingli);
	}
	
	$('#btneditSubmit').click(function() {
		var studentData = JSON.stringify($('#formEditXuesheng').serializeArray());
//		alert(studentData);
		$.ajax({
			type : "POST" ,
			contentType : "application/json" ,      				
			url : "updatestudent",
			data: studentData,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=-1){
					alert("学生修改成功");
					$('#btneditCance').click();
					$("#content").load("xuesheng.html");
					
				}
				
			}     			
		});
	});
	
	$('#addxuesheng').click(function() {
		$("#id").val(-1);
		$('#xingming').val("");
		$('#cs_riqi').val("");
		$('#dizhi').val("");
		$('#gd_dianhua').val("");
		$('#mq_shouji').val("");
		$('#fq_shouji').val("");
		$('#youeryuan').val("");
		$('#beizhu').val("");
		$('#zhaopian').val("");
		$('#jingli').val("");
		
		$('#btndeleteSubmit').hide();
		$('#btneditSubmit').hide();
		$('#btnaddSubmit').show();
		
	});
	
	$('#btndelete').click(function() {
		var studentid = $('#delid').val();
		$.ajax({
			type : "POST" ,
			contentType : "application/json" ,      				
			url : "deletestudent?id="+studentid,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=-1){
					alert("学生删除成功");
					$('#btndeleteCance').click();
					$("#content").load("xuesheng.html");
				}
				
			}     			
		});
	});
	
	$('#studentsInfoTable tbody').on( 'click', 'button', function () {
        var studentid = $(this).parents('tr').eq(0).attr('id');
        $.ajax({
			type : "POST" ,
			contentType : "application/json" ,      				
			url : "deletestudent?id="+studentid,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=-1){
					alert("学生删除成功");
					$('#btneditCance').click();
					$("#content").load("xuesheng.html");
				}
				
			}     			
		});
        
    } );
	
	function validJson(resultdata){
		if(resultdata.status==1){
			return resultdata.data;
		}else{
			alert("系统等待："+resultdata.info);
			return -1;
		}
	}
});