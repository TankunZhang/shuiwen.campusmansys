$("document").ready(function() {
		$.ajax({
			type : "POST" ,
			contentType : "application/json" ,      				
			url : "findallusers",
			dataType : "json" ,       					
			success : function(data){
				showingtable(data);
			} 			
		});
	
	function showingtable(data){
		var guanliyuansTable = $("#GuanliyuansTable");
		$("#GuanliyuansInfoTR").show();
		var stuttr = $("#GuanliyuansInfoTR");
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
                                    $(this).html(item.gly_mingzi);
                                    break;  
                                case(2):  
                                    $(this).html(item.mima);  
                                    break;  
                                case(3):  
                                    $(this).html(item.scdl_shijian);  
                                    break;  
                                case(4):  
                                    $(this).html(item.zc_shijian);  
                                    break;  
                                case(5):  
                                    $(this).html(item.quanxian);  
                                    break;  
                          }//end switch                          
           });//end children.each  
         
          //把克隆好的tr追加原来的tr后面  
          clonedTr.insertAfter(stuttr);  
       });//end $each  
		$("#GuanliyuansInfoTR").hide();
		$("#GuanliyuansTable").dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers",
			"sDom": '<""l>t<"F"fp>'
		});
		$("#GuanliyuansTable").show();
	}
	
	$('#addGuanliyuanSubmit').click(function() {
		
		var formGuanliyuan = JSON.stringify($('#edityonghu').serializeArray());
		$.ajax({
			type : "POST" ,
			contentType : "application/json" ,      				
			url : "adduser",
			data: formGuanliyuan,
			dataType : "json" ,       					
			success : function(result){
				if(result!=1){
					alert("添加成功");
					AddAGuanliyuan(result)
					$('#modalCance').click();
//					$('#findAllGuanliyuansBtn').click();
				}else{
					alert("失败");
				}
			}     			
		});
	});
	
	$("#aedityonghu").click(function() {
//		alert("sdfsdf");
		$('#btndeleteSubmit').show();
		$('#btneditSubmit').show();
		$('#btnaddSubmit').hide();
		
	});
	
	$('#btnaddSubmit').click(function(){
		var userData = JSON.stringify($('#formEditUser').serializeArray());
//		alert(studentData);
		$.ajax({
			type : "POST" ,
			contentType : "application/json" ,      				
			url : "adduser",
			data: userData,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=-1){
					alert("用户添加成功");
					$('#btneditCance').click();
					$("#content").load("yonghu.html");
				}
			}
		});
	});
	
	$('#addyonghu').click(function(){
		$("#id").val(-1);
		$('#gly_mingzi').val("");
		$('#mima').val("");
		$('#quanxian').val("");

		
		$('#btndeleteSubmit').hide();
		$('#btneditSubmit').hide();
		$('#btnaddSubmit').show();
	});
	
	$('#GuanliyuansTable tbody').on( 'click', 'a', function () {
        var userid = $(this).parents('tr').eq(0).attr('id');
        $.ajax({
    		type : "POST" ,
    		contentType : "application/json;charset=utf-8" ,      				
    		url : "finduserbyid?id="+userid,
    		dataType : "json" ,       					
    		success : function(resultdata){
    			var data = validJson(resultdata);
    			if(data!=0){
    				showedituser(data);

    			}
    		}
    	});
        
    } );
	$('#GuanliyuansTable tbody').on( 'click', 'button', function () {
        var userid = $(this).parents('tr').eq(0).attr('id');
        $.ajax({
			type : "POST" ,
			contentType : "application/json" ,      				
			url : "deleteuser?id="+userid,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=-1){
					alert("用户删除成功");
					$('#btneditCance').click();
					$("#content").load("yonghu.html");
				}
				
			}     			
		});
        
    } );
	
	function showedituser(data){
		
		$("#id").val(data.id);
		$("#gly_mingzi").val(data.gly_mingzi);
		$('#mima').val(data.mima);
		$('#quanxian').val(data.quanxian);
		
	}
	
	
	function validJson(resultdata){
		if(resultdata.status==1){
			return resultdata.data;
		}else{
			alert("系统等待："+resultdata.info);
			return -1;
		}
	}
});