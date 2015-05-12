$("document").ready(function() {
	
	$('.input-mask-date').mask('9999-99-99');
	var stuttr = $("#trkecheng");
	val = xiaoquid = $('#indexxiaoqu').val();
//	var clonedTr = stuttr.clone();
	var datatablezu = $("#tablekecheng").dataTable({
		"bDestroy": true,
		"bPaginate": false, //翻页功能
		"bLengthChange": false, //改变每页显示数据数量
		"bFilter": false, //过滤功能
		"bSort": false, //排序功能
		"bInfo": false,//页脚信息
		"bAutoWidth": true,//自动宽度
		"bJQueryUI": false,
	});
	
	if(xiaoquid<0){
		$("#xiaoqutitle").html("操作功能，将您请选择校区后开启！");
		$('#topdiv').hide();
	}else{
		$('#xiaoquid').val(xiaoquid);
	}
	
	var clcount = 0;
	$.ajax({
		type : "POST" ,
		contentType : "application/json;charset=utf-8" ,      				
		url : "findallsubjects?xiaoquid="+xiaoquid,
		dataType : "json" ,       					
		success : function(resultdata){
			var data = validJson(resultdata);
			if(data!=-1){
				showingkemu(data);
			}
		}
	});
	
	$('#ValidSearch').click(function() {
		var xingming = $("#validxingming").val();
		var cs_riqi = $("#validcs_riqi").val();	
		$.ajax({
			type : "POST" ,
			contentType : "application/json" ,      				
			url : "validstudent?xingming=" + xingming + "&cs_riqi=" + cs_riqi+"&xiaoquid="+xiaoquid,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=-1){
					writeStuInfo(data);
					$('#ValidCance').click();
					$('#editstudent').val("保存修改");
				}else{
					$('#ValidCance').click();
					alert("本小区没有此学生");
				}
			} 			
		});
	});
	
	function writeStuInfo(data){

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
	
	function showingkemu(data){
		
		$.each(data, function (i, item) { 
			jQuery("#kemuid").append("<option value="+ item.id+">"+ item.km_mingzi+"</option>");
		});
		kemuonchange($("#kemuid").val());
		
	}

	
	$('#editstudent').click(function(){
		if($('#id').val()>0){
			var studentData = JSON.stringify($('#form-wizard').serializeArray());
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
					}
					
				}     			
			});
		}else{
			var studentData = JSON.stringify($('#form-wizard').serializeArray());
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
						var xingming = $("#xingming").val();
						var cs_riqi = $("#cs_riqi").val();	
						$.ajax({
							type : "POST" ,
							contentType : "application/json" ,      				
							url : "validstudent?xingming=" + xingming + "&cs_riqi=" + cs_riqi+"&xiaoquid="+xiaoquid,
							dataType : "json" ,       					
							success : function(resultdata){
								var data = validJson(resultdata);
								if(data!=-1){
									writeStuInfo(data);
								}
							} 			
						});
					}
					
				}     			
			});
		}
	});
	
	$('#kemuid').change(function(){
		kemuonchange($(this).val());
	});
	
	function kemuonchange(kumubyid){
		$("#banjiid").empty();
		$.ajax({
			type : "POST" ,
			contentType : "application/json;charset=utf-8" ,      				
			url : "findclassbysubject?kemuid="+kumubyid,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=-1){
					$.each(data, function (i, item) { 
							jQuery("#banjiid").append("<option value="+ item.id+">"+ item.bj_mingzi+"</option>");
					});
				}
			}
		});
	}	
	
	$('#btnaddkecheng').click(function(){
		var xueshengid = $("#id").val();
		if(xueshengid>0){
		$('#xuankeid').val(0);
		var postdata = "{\"id\":"+$("#xuankeid").val()+",\"kemuid\":"+$("#kemuid").val()+",\"km_mingzi\":\""+$("#kemuid").find("option:selected").text()+
		"\",\"banjiid\":"+$("#banjiid").val()+",\"bj_mingzi\":\""+$("#banjiid").find("option:selected").text()+"\",\"xueqishu\":"+$("#xueqishu").val()+"}";
		$.ajax({
			type : "POST" ,
			contentType : "application/json;charset=utf-8" ,      				
			url : "cacheapplyclasses?xueshengid="+xueshengid,
			data: postdata,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=-1){
					showingtable(data);
					$('#btnaddkechengcancle').click();
				}
			}
		});
		}else{
			alert("请添加选择课程的 学生。");
		}
	});
	
	function showingtable(data){
		stuttr.show();
		$.each(data, function(index,item){    
			if($("#"+item.banjiid).length>0){
				$("#"+item.banjiid).show();
				var clonedTr = $("#"+item.banjiid);
	            //循环遍历cloneTr的每一个td元素，并赋值  
	              clonedTr.children("td").each(function(inner_index){  
	                 //根据索引为每一个td赋值  
		               switch(inner_index){  
		                     case(0):   
		                        $(this).html(item.km_mingzi);  
		                        break;  
		                     case(1):  
		                        $(this).html(item.bj_mingzi);
		                        break;  
		                    case(2):  
		                        $(this).html(item.xueqishu);  
		                        break;  
		                    
		              }//end switch                          
	           });//end children.each  
			}else{
				var clonedTr = stuttr.clone();
	              clonedTr.attr('id',item.banjiid);
	            //循环遍历cloneTr的每一个td元素，并赋值  
	              clonedTr.children("td").each(function(inner_index){  
	                 //根据索引为每一个td赋值  
		               switch(inner_index){  
		                     case(0):   
		                        $(this).html(item.km_mingzi);  
		                        break;  
		                     case(1):  
		                        $(this).html(item.bj_mingzi);
		                        break;  
		                    case(2):  
		                        $(this).html(item.xueqishu);  
		                        break;  
		                    
		              }//end switch                          
	           });//end children.each  
	         
	          //把克隆好的tr追加原来的tr后面  
	          clonedTr.insertAfter(stuttr);  
			}
				
       });//end $each  
		stuttr.hide();
		$("#tablekecheng").dataTable({
			"bDestroy": true,
			"bPaginate": false, //翻页功能
			"bLengthChange": false, //改变每页显示数据数量
			"bFilter": false, //过滤功能
			"bSort": false, //排序功能
			"bInfo": false,//页脚信息
			"bAutoWidth": true,//自动宽度
			"bJQueryUI": false,
		});
		$("#tablekecheng").show();
	}
	
	$('#tablekecheng tbody').on( 'click', 'button', function () {
        var banjiid = $(this).parents('tr').eq(0).attr('id');
        var xueshengid = $("#id").val();
        $.ajax({
    		type : "POST" ,
    		contentType : "application/json;charset=utf-8" ,      				
    		url : "delcacheapplyclass?xueshengid="+xueshengid+"&banjiid="+banjiid,
    		dataType : "json" ,       					
    		success : function(resultdata){
    			var data = validJson(resultdata);
    			if(data!=0){
    				$("#"+banjiid).hide();
    			}
    		}
    	});
        
    } );
	
	$('#btntijiaoxuanke').click(function(){
		var xueshengid = $("#id").val(); 
		if($("#id").val()>0){
			$.ajax({
				type : "POST" ,
				contentType : "application/json" ,      				
				url : "addstuclass?xueshengid="+xueshengid+"&shoukuanren="+$('#shoukuanren').val(),
				dataType : "json" ,       					
				success : function(resultdata){
					var data = validJson(resultdata);
					if(data!=-1){
						alert("选课成功");
						$("#content").load("baoming.html");
					}
				}
			});
		}else{
			alert("请选择学生");
		}
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	function validJson(resultdata){
		if(resultdata.status==1){
			return resultdata.data;
		}else{
			alert("系统等待："+resultdata.info);
			return -1;
		}
	}
});