$("document").ready(function() {
	val = xiaoquid = $('#indexxiaoqu').val();
	$('.input-mask-date').mask('9999-99-99');
	
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
	
	$('#classcount').change(function(){
		clcount = $("#classcount").val();
		
		if(clcount==2){
			$("#classdiv1").show();
			$("#classdiv2").show();
			$("#classdiv3").hide();
			$("#classdiv4").hide();
		}else if(clcount==3){
			$("#classdiv1").show();
			$("#classdiv2").show();
			$("#classdiv3").show();
			$("#classdiv4").hide();
		}else if(clcount==4){
			$("#classdiv1").show();
			$("#classdiv2").show();
			$("#classdiv3").show();
			$("#classdiv4").show();
		}else if(clcount==1){
			$("#classdiv1").show();
			$("#classdiv2").hide();
			$("#classdiv3").hide();
			$("#classdiv4").hide();
		}

	});
	
	function showingkemu(data){
		
		$.each(data, function (i, item) { 
			for(i=1;i<5;i++){
			jQuery("#kemu"+i).append("<option value="+ item.id+">"+ item.km_mingzi+"</option>");
			}
		});
		
		$.ajax({
			type : "POST" ,
			contentType : "application/json;charset=utf-8" ,      				
			url : "findclassbysubject?kemuid="+$("#kemu1").val(),
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				showingallbanji(data);
			}
		});
	}
	function showingallbanji(data){
		$.each(data, function (i, item) { 
			for(i=1;i<5;i++){
				jQuery("#banji"+i).append("<option value="+ item.id+">"+ item.bj_mingzi+"</option>");
			}
		});
		
		
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
	
	$('#kemu1').change(function(){
		kemuonchange($(this).val(),1);
	});
	$('#kemu2').change(function(){
		kemuonchange($(this).val(),2);
	});
	$('#kemu3').change(function(){
		kemuonchange($(this).val(),3);
	});
	$('#kemu4').change(function(){
		kemuonchange($(this).val(),4);
	});
	
	function kemuonchange(kumubyid,kemudeid){
		$("#banji"+kemudeid).empty();
		$.ajax({
			type : "POST" ,
			contentType : "application/json;charset=utf-8" ,      				
			url : "findclassbysubject?kemuid="+kumubyid,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=-1){
					$.each(data, function (i, item) { 
							jQuery("#banji"+kemudeid).append("<option value="+ item.id+">"+ item.bj_mingzi+"</option>");
					});
				}
			}
		});
	}	
	
	$('#accounts').click(function(){
		if($("#id").val()>0){
			var kemushu = $('#classcount').val();
			var postdata = "{\"xueshengid\":"+$("#id").val()+",\"shoukuanren\":\""+$("#shoukuanren").val()+"\",\"kemushu\":"+kemushu+",\"kemu\":[";
			while(kemushu>0){
				if(kemushu==1){
					postdata=postdata+"{\"banjiid\":"+$("#banji"+kemushu).val()+",\"xueqishu\":"+$('#classtime'+kemushu).val()+"}]}";
				}else{
					postdata=postdata+"{\"banjiid\":"+$("#banji"+kemushu).val()+",\"xueqishu\":"+$('#classtime'+kemushu).val()+"},";
				}
				kemushu--;
			}
			alert(postdata);
			$.ajax({
				type : "POST" ,
				contentType : "application/json" ,      				
				url : "addstuclass",
				data: postdata,
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