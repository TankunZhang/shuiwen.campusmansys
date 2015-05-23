$("document").ready(function() {
	var addtr = "<tr id=\"editkemutr\" class=\"gradeA\"><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>";
	 $("#editkemutbody").append(addtr).show();
	var xiaoquid = $('#indexxiaoqu').val();
	var huancunxueqizhi = 0;
	$.ajax({
		type : "POST" ,
		contentType : "application/json;charset=utf-8" ,      				
		url : "findallsubjects?xiaoquid="+xiaoquid,
		dataType : "json" ,       					
		success : function(resultdata){
			var data = validJson(resultdata);
			if(data!=-1){
				$.each(data, function (i, item) { 
					jQuery("#kemuid").append("<option value="+ item.id+">"+ item.km_mingzi+"</option>");
					
				});
				kemuonchange($("#kemuid").val());
			}
		}
	});
	$("#kemuid").change(function(){
		$("#editkemutable").dataTable().fnClearTable();
		$("#editkemutbody").append(addtr).show();
		kemuonchange($(this).val());
	});
	function kemuonchange(kemubyid){
		$.ajax({
			type : "POST" ,
			contentType : "application/json;charset=utf-8" ,      				
			url : "finddankeyouhuibysubject?kemuid="+kemubyid,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=-1){
					huancunxueqizhi = data.length;
					setyouhui(data);
				}
			}
		});
		
	}
	
function setyouhui(kemudata){
	var zongjia = 0;
	var zhekouzongjia = 0;
	var stuttr = $("#editkemutr");
	$.each(kemudata, function (i, item) {
			//克隆tr，每次遍历都可以产生新的tr                              
	        var clonedTr = stuttr.clone();
	        clonedTr.attr('id',i);
	        //循环遍历cloneTr的每一个td元素，并赋值  
	        clonedTr.children("td").each(function(inner_index){  
	               //根据索引为每一个td赋值  
	                     switch(inner_index){  
	                           case(0):   
	                        	   $(this).attr('id',"xueqishu"+i);
	                              $(this).html(item.xueqishu);  
	                              break;  
	                           case(1):  
	                        	   $(this).attr('id',"keshi"+i);
	                              $(this).html(item.keshi);
	                              break;  
	                          case(2):  
	                              $(this).html("<input type=\"text\" id=\"danjia"+i+"\" style=\"width: 40px;\" value=\""+item.danjia+"\" >");  
	                              break;  
	                          case(3):  
	                        	  $(this).attr('id',"xueqizongjia"+i);
	                              $(this).html(item.keshi*item.danjia);  
	                              break; 
	                          case(4):  
	                        	  $(this).attr('id',"lianbaokeshi"+i);
	                              $(this).html(item.keshi*item.xueqishu);  
	                              break; 
	                          case(5):  
	                        	  $(this).attr('id',"zongjia"+i);
	                          	  $(this).html(item.zongjia);  
	                              break; 
	                          case(6):  
	                        	  $(this).html("<input type=\"text\" id=\"zhekoujia"+i+"\" style=\"width: 40px;\" value="+item.zhekoujia+">");
	                              break; 
	                          case(7):  
	                        	  if(item.zhekou==100){
	                        		  $(this).html("<input type=\"text\" id=\"zhekou"+i+"\" class=\"zhekou\" style=\"width: 40px;\" value=\""+item.zhekou+"%\" >");  
	                        	  }else{
	                        		  $(this).html("<input type=\"text\" id=\"zhekou"+i+"\" class=\"zhekou\" style=\"width: 40px;\" value=\"0"+item.zhekou+"%\" >");
	                        	  }
	                        	  break;
	                          
	                    }//end switch                          
		     });//end children.each  
		   
		    //把克隆好的tr追加原来的tr后面  
	//	    clonedTr.append($("#editkemutbody"));  
	        
		    $("#editkemutbody").append(clonedTr);
		 });//end $each  
		$('.zhekou').mask('999%');
		$("#editkemutr").hide();
		$("#editkemutable").show();
		$("#editkemutable").dataTable({
			"bDestroy": true,
			"bPaginate": false, //翻页功能
			"bLengthChange": false, //改变每页显示数据数量
			"bFilter": false, //过滤功能
			"bSort": false, //排序功能
			"bInfo": false,//页脚信息
			"bAutoWidth": true,//自动宽度
			"bJQueryUI": false,
		});
	}
	
	$('#rejisuan').click(function(){
		zongjia = 0;
		var zhekouzongjia = 0;
		
		for(var i=1;i<huancunxueqizhi;i++){
	        var clonedTr = $("#"+i);
	        //循环遍历cloneTr的每一个td元素，并赋值  
	        clonedTr.children("td").each(function(inner_index){  
	               //根据索引为每一个td赋值  
	        	var zhekoushu = $("#zhekou"+i).val().split("%")[0];
                     switch(inner_index){  
	                  case(3):  
	                	  zongjia = zongjia+$("#keshi"+i).html()*$("#danjia"+i).val();
	                	  $(this).html($("#keshi"+i).html()*$("#danjia"+i).val()); 
	                      break; 
	                  case(4): 
	                	  $(this).html($("#keshi"+i).html()*$("#xueqishu"+i).html());  
	                      break; 
	                  case(5):  
	                  	  $(this).html(zongjia);  
	                      break; 
	                  case(6):  
	                	  $("#zhekoujia"+i).val(zongjia*zhekoushu/100);
	                      break; 
                    }//end switch                          
		     });//end children.each  
		 }//end $each  
		setdankeyouhui = setdankeyouhui + "]}";
	});
	
	$('#setdankeyouhui').click(function(){
		setdankeyouhui = "{\"km_mingzi\":\""+huancun.km_mingzi+"\",\"xiaoquid\":"+$('#indexxiaoqu').val()+",\"xueqizhi\":"+huancun.xueqizhi+",\"keshi\":"+huancun.morenkeshi+",\"dankeyouhui\":[";
		for(var i=1;i<huancun.xueqizhi+1;i++){
			if(i!=1){
				setdankeyouhui = setdankeyouhui+","
			}
			//克隆tr，每次遍历都可以产生新的tr                              
	        var clonedTr = $("#"+i);
	        //循环遍历cloneTr的每一个td元素，并赋值  
	        clonedTr.children("td").each(function(inner_index){  
	               //根据索引为每一个td赋值  
                switch(inner_index){  
                     case(0):  
                	   		setdankeyouhui =setdankeyouhui + "{\"xueqishu\":"+$(this).html()+","; 
                   		break; 
                     case(1):  
                 	    setdankeyouhui = setdankeyouhui + "\"keshi\":"+$(this).html()+","; 
             			break;
                     case(2):  
                 	    setdankeyouhui = setdankeyouhui +  "\"danjia\":"+$("#danjia"+i).val()+","; 
           				break;
	                  case(5):  
	                	  setdankeyouhui = setdankeyouhui + "\"zongjia\":"+zongjia+","; 
	                      break; 
	                  case(6):  
	                  	setdankeyouhui = setdankeyouhui + "\"zhekoujia\":"+ $("#zhekoujia"+i).val()+","; 
	                      break; 
	                  case(7):  
                    	  setdankeyouhui = setdankeyouhui + "\"zhekou\":"+zhekoushu+"}"; 
                          break;
                    }//end switch                          
		     });//end children.each  
		 }//end $each  
		setdankeyouhui = setdankeyouhui + "]}";
		if($('#zhekoujia1').html().length>0){
			$.ajax({
				type : "POST" ,
				contentType : "application/json" ,      				
				url : "addsubject",
				data: setdankeyouhui,
				dataType : "json" ,       					
				success : function(resultdata){
					var data = validJson(resultdata);
					if(data!=-1){
						alert("科目添加成功成功");
//						$("#content").load("kecheng.html");
					}
					
				}     			
			});
		}else{
			alert('请统计计算！');
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