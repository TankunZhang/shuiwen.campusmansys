$("document").ready(function() {
	val = xiaoquid = $('#indexxiaoqu').val();

	$('.input-mask-date').mask('9999-99-99  99:99:99');
	if(xiaoquid>0){
		$.ajax({
			type : "POST" ,
			contentType : "application/json;charset=utf-8" ,      				
			url : "findallsubjects?xiaoquid="+xiaoquid,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				
				if(data!=0){
					$.each(data, function (i, item) { 
						jQuery("#kemuid").append("<option value="+ item.id+">"+ item.km_mingzi+"</option>");
						jQuery('#collapseOne').prepend("<div class='widget-content' id="+item.id+">"+item.km_mingzi+"</div>");
					});
				}
			}
		});
	}else{
		$("#xiaoqutitle").html("操作功能，将您请选择校区后开启！");
		$("#aeditbanji").hide();
		$("#btndeletefrom").hide();
		$("#btndeletefrom").hide();
		$("#addbanji").hide();
		$("#beginaddkemu").hide();
		
		
	}
	
	$('#btnaddSubmit').click(function(){
		var banjiData = JSON.stringify($('#formEditBanji').serializeArray());
//		alert(banjiData);
		$.ajax({
			type : "POST" ,
			contentType : "application/json" ,      				
			url : "addclass",
			data: banjiData,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=-1){
					alert("班级添加成功");
					$('#btneditCance').click();
					$("#content").load("kecheng.html");
				}
			}
		});
	});
	

	
	$.ajax({
		type : "POST" ,
		contentType : "application/json;charset=utf-8" ,      				
		url : "findallclasses?xiaoquid="+xiaoquid,
		dataType : "json" ,       					
		success : function(resultdata){
			var data = validJson(resultdata);
			if(data!=0){
				showingtable(data);
			}
		}
	});
	
	function showingtable(data){
		var stuttr = $("#banjisInfoTR");
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
                                    $(this).html(item.km_mingzi);
                                    break;  
                                case(2):  
                                    $(this).html(item.bj_mingzi);  
                                    break;  
                                case(3):  
                                    $(this).html(item.sk_jiaoshi);  
                                    break;  
                                case(4):  
                                    $(this).html(item.sk_shijian);  
                                    break;  
                                case(5):  
                                    $(this).html(item.keshi);  
                                    break;  
                                case(6):  
                                    $(this).html(item.kk_shijian);  
                                    break;  
                                case(7):  
                                    $(this).html(item.zhuangtai);  
                                    break;  
                                case(8):  
                                    $(this).html(item.zd_renshu);  
                                    break;  
                                case(9):  
                                    $(this).html(item.dq_renshu);  
                                    break;  
                                
                          }//end switch                          
           });//end children.each  
         
          //把克隆好的tr追加原来的tr后面  
          clonedTr.insertAfter(stuttr);  
       });//end $each  
		$("#banjisInfoTR").hide();
		$("#banjisInfoTable").show();
		$("#banjisInfoTable").dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers",
			"sDom": '<""l>t<"F"fp>'
		});
	}
	
	$('#banjisInfoTable tbody').on( 'click', 'a', function () {
		$('#btndeleteSubmit').show();
		$('#btneditSubmit').show();
		$('#btnaddSubmit').hide();
        var banjiid = $(this).parents('tr').eq(0).attr('id');
        $.ajax({
    		type : "POST" ,
    		contentType : "application/json;charset=utf-8" ,      				
    		url : "findclassbyid?id="+banjiid,
    		dataType : "json" ,       					
    		success : function(resultdata){
    			var data = validJson(resultdata);
    			if(data!=0){
    				showeditbanji(data);
    			}
    		}
    	});
        
    } );
	function showeditbanji(data){
		
//		$('#btneditCance').click();
		$("#id").val(data.id);
		$("#kemuid").val(data.kemuid);
		$('#xueqi').val(data.xueqi);
		$('#bj_mingzi').val(data.bj_mingzi);
		$('#sk_jiaoshi').val(data.sk_jiaoshi);
		$('#sk_shijian').val(data.sk_shijian);
		$('#keshi').val(data.keshi);
		$('#kk_shijian').val(data.kk_shijian);
		$('#zhuangtai').val(data.zhuangtai);
		$('#zd_renshu').val(data.zd_renshu);
	}
	
	$('#btneditSubmit').click(function() {
		var banjiData = JSON.stringify($('#formEditBanji').serializeArray());
//		alert(banjiData);
		$.ajax({
			type : "POST" ,
			contentType : "application/json" ,      				
			url : "updateclass",
			data: banjiData,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=-1){
					alert("班级修改成功");
					$('#btneditCance').click();
					$("#content").load("kecheng.html");
					
				}
				
			}     			
		});
	});
	
	$('#addbanji').click(function() {
		$("#id").val(-1);
		$('#sk_jiaoshi').val("");
		$('#keshi').val("");
		$('#zd_renshu').val("");
		
		$('#btndeleteSubmit').hide();
		$('#btneditSubmit').hide();
		$('#btnaddSubmit').show();
		
	});
	
	$('#banjisInfoTable tbody').on( 'click', 'button', function () {
        var banjiid = $(this).parents('tr').eq(0).attr('id');
        $.ajax({
			type : "POST" ,
			contentType : "application/json" ,      				
			url : "deleteclassnow?id="+banjiid,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=-1){
					alert("班级删除成功");
					$('#btneditCance').click();
					$("#content").load("kecheng.html");
				}
				
			}     			
		});
        
    } );
	
	$('#addkemuSubmit').click(function(){
		var km_mingzi = $('#km_mingzi').val();
		var xueqizhi = $('#xueqizhi').val();
		var morenkeshi = $('#morenkeshi').val();
		var danjia = $('#danjia').val();
		if(km_mingzi.length>0&&xueqizhi.length>0&&morenkeshi.length>0&&danjia.length>0){
			$('#addkemuCance').click();
			var kemuhuancun = "{\"km_mingzi\":\""+$('#km_mingzi').val()+"\",\"xueqizhi\":"+$('#xueqizhi').val()+",\"morenkeshi\":"+$('#morenkeshi').val()+",\"danjia\":"+$('#danjia').val()+"}";
			$("#huancun").val(kemuhuancun);
			$("#content").load("editkemu.html");
		}
		
//		var km_mingzi = $('#km_mingzi').val();
//		if(km_mingzi.length>0){
//		$.ajax({
//			type : "GET" ,
//			contentType : "application/json" ,      				
//			url : "addsubject?km_mingzi="+km_mingzi+"&xiaoquid="+xiaoquid,
//			dataType : "json" ,       					
//			success : function(resultdata){
//				var data = validJson(resultdata);
//				if(data!=-1){
//					alert("科目添加成功");
//					$('#addkemuCance').click();
//					$("#content").load("kecheng.html");
////					jQuery('#collapseOne').prepend("<div class='widget-content' id="+data.id+">"+data.km_mingzi+"</div>");
//					
////					
//				}
//			}
//		});
//		}else{
//			alert("请输入科目");
//		}
	});
	
	$('#bj_mingzi').click(function(){
		$(this).val($('#kemuid').find("option:selected").text()+$('#xueqi').val());
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