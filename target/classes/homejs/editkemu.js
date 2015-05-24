$("document").ready(function() {
	$('.keshi').mask('99');
	var huancun = $('#huancun').val();
	var setdankeyouhui = "";
	huancun=eval('('+huancun+')');
	$("#editkemutitle").html(huancun.km_mingzi+",单科优惠设置");
	
	var zongjia = 0;
	var zhekouzongjia = 0;
	var stuttr = $("#editkemutr");
	for(var i=1;i<huancun.xueqizhi+1;i++){
		//克隆tr，每次遍历都可以产生新的tr                              
        var clonedTr = stuttr.clone();
        clonedTr.attr('id',i);
        //循环遍历cloneTr的每一个td元素，并赋值  
        clonedTr.children("td").each(function(inner_index){  
               //根据索引为每一个td赋值  
                     switch(inner_index){  
                           case(0):   
                              $(this).html(i);  
                              break;  
                           case(1):  
                              $(this).html(huancun.morenkeshi);
                              break;  
                          case(2):  
                              $(this).html("<input type=\"text\" id=\"danjia"+i+"\" style=\"width: 40px;\" value=\""+huancun.danjia+"\" >");  
                              break;  
                          case(3):  
                        	  zongjia = zongjia+huancun.morenkeshi*huancun.danjia;
                              $(this).html(huancun.morenkeshi*huancun.danjia);  
                              break; 
                          case(4):  
                              $(this).html(huancun.morenkeshi*i);  
                              break; 
                          case(5):  
                          	  $(this).html(zongjia);  
                              break; 
                          case(6):  
                        	  $(this).html("<input type=\"text\" id=\"zhekoujia"+i+"\" style=\"width: 40px;\" >");
                              break; 
                          case(7):  
                        	  $(this).html("<input type=\"text\" id=\"zhekou"+i+"\" class=\"zhekou\" style=\"width: 40px;\" value=\"100%\" >");  
                          break;
                          
                    }//end switch                          
	     });//end children.each  
	   
	    //把克隆好的tr追加原来的tr后面  
//	    clonedTr.append($("#editkemutbody"));  
        
	    $("#editkemutbody").append(clonedTr);
	 }//end $each  
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
	
	$('#rejisuan').click(function(){
		zongjia = 0;
		var zhekouzongjia = 0;
		for(var i=1;i<huancun.xueqizhi+1;i++){
	        var clonedTr = $("#"+i);
	        clonedTr.children("td").each(function(inner_index){  
	               //根据索引为每一个td赋值  
	        	var zhekoushu = $("#zhekou"+i).val().split("%")[0];
                     switch(inner_index){  
	                  case(3):  
	                	  zongjia = zongjia+huancun.morenkeshi*$("#danjia"+i).val();
	                      $(this).html(huancun.morenkeshi*$("#danjia"+i).val());  
	                      break; 
	                  case(4):  
	                      $(this).html(huancun.morenkeshi*i);  
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
	        	var zhekoushu = $("#zhekou"+i).val().split("%")[0];
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
		alert(setdankeyouhui);
//		if($('#zhekoujia1').html().length>0){
//			$.ajax({
//				type : "POST" ,
//				contentType : "application/json" ,      				
//				url : "addsubject",
//				data: setdankeyouhui,
//				dataType : "json" ,       					
//				success : function(resultdata){
//					var data = validJson(resultdata);
//					if(data!=-1){
//						alert("科目添加成功成功");
//						$("#content").load("kecheng.html");
//					}
//					
//				}     			
//			});
//		}else{
//			alert('请统计计算！');
//		}
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