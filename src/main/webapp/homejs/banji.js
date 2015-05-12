$("document").ready(function() {
	val = xiaoquid = $('#indexxiaoqu').val();

	
	$.ajax({
		type : "POST" ,
		contentType : "application/json;charset=utf-8" ,      				
		url : "findallstuclasses?xiaoquid="+xiaoquid,
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
                                    $(this).html(item.xingming);
                                    break;
                                 case(2):  
                                     $(this).html(item.bj_mingzi);
                                     break;  
                                case(3):  
                                    $(this).html(item.xuefei);  
                                    break;  
                                case(4):  
                                    $(this).html(item.shoukuanren);  
                                    break;  
                                case(5):  
                                    $(this).html(item.queqinliang);  
                                    break;  
                                case(6):  
                                    $(this).html(item.qq_beizhu);  
                                    break;  
                                case(7):  
                                    $(this).html(item.pingjia);  
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
			'bStateSave':true,
			"sPaginationType": "full_numbers",
			"sDom": '<""l>t<"F"fp>'
		});
	}
	
	function validJson(resultdata){
		if(resultdata.status==1){
			return resultdata.data;
		}else{
			alert("系统等待："+resultdata.info);
			return 0;
		}
		
	}
});