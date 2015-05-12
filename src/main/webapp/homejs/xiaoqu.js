$("document").ready(function() {
	
		$.ajax({
			type : "POST" ,
			contentType : "application/json" ,      				
			url : "findallschools",
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=0){
					showingtable(data);
				}
			} 			
		});
	
	function showingtable(data){
			
		var tableShowXiaoqus = $("#tableShowXiaoqus");
		var stuttr = $("#trShowXiaoqu");
		$.each(data, function(index,item){                              
            //克隆tr，每次遍历都可以产生新的tr                              
              var clonedTr = stuttr.clone();  
              var _index = index;  
             
              //循环遍历cloneTr的每一个td元素，并赋值  
              clonedTr.children("td").each(function(inner_index){  
                 
                     //根据索引为每一个td赋值  
                           switch(inner_index){  
                                 case(0):   
                                    $(this).html(item.id);  
                                    break;  
                                 case(1):  
                                    $(this).html(item.xq_mingzi);
                                    break;  
                                case(2):  
                                    $(this).html(item.suoxie);  
                                    break;  
                                case(3):  
                                    $(this).html(item.dizhi);  
                                    break;  
                                case(4):  
                                    $(this).html(item.dianhua);  
                                    break;  
                          }//end switch                          
           });//end children.each  
              
          //把克隆好的tr追加原来的tr后面  
          clonedTr.show();   
          clonedTr.insertAfter(stuttr);  
       });//end $each  
		$("#trShowXiaoqu").hide();
		$("#tableShowXiaoqus").dataTable({
			"bJQueryUI": true,
			"sPaginationType": "full_numbers",
			"sDom": '<""l>t<"F"fp>'
		});
		$("#tableShowXiaoqus").show();
		
	}
	
	$('#submitInsertXiaoqu').click(function() {
		
		var formInsertXiaoqu = JSON.stringify($('#formInsertXiaoqu').serializeArray());
		$.ajax({
			type : "POST" ,
			contentType : "application/json" ,      				
			url : "addschool",
			data: formInsertXiaoqu,
			dataType : "json" ,       					
			success : function(resultdata){
				var data = validJson(resultdata);
				if(data!=0){
					alert("添加成功，页面将重新加载！");
					$('#modalCance').click();
					location.reload();
				}
			}     			
		});
	});
	
//	function AddXiaoqu(data){
//		var stuttr = $("#trShowXiaoqu");
//		var tbodyShowXiaoqu = $("#tbodyShowXiaoqu");
//		
//		 var clonedTr = stuttr.clone();
//        
//         //循环遍历cloneTr的每一个td元素，并赋值  
//         clonedTr.children("td").each(function(inner_index){  
//                //根据索引为每一个td赋值  
//                      switch(inner_index){  
//                            case(0):   
//                               $(this).html(data.id);  
//                               break;  
//                            case(1):  
//                               $(this).html(data.xq_mingzi);
//                               break;  
//                           case(2):  
//                               $(this).html(data.suoxie);  
//                               break;  
//                           case(3):  
//                               $(this).html(data.dizhi);  
//                               break;  
//                           case(4):  
//                               $(this).html(data.dianhua);  
//                               break;  
//                     }//end switch                          
//      });//end children.each  
//         clonedTr.show();
//         clonedTr.insertAfter(tbodyShowXiaoqu);  
//         $("#tableShowXiaoqus").dataTable({
// 			"bJQueryUI": true,
// 			"sPaginationType": "full_numbers",
// 			"sDom": '<""l>t<"F"fp>'
// 		});
// 		$("#tableShowXiaoqus").show();
//		
//	}
	
$('#deleteXiaoqu').click(function(){
	clearTable();
	
//	$("trShowXiaoqu").remove();
//		$("#tbodyShowXiaoqu").dataTable().remove();
//		alert(3);
//	if (start > 0) {
//		$("#tableShowXiaoqus").dataTable().fnPageChange( 'previous', true );
//	}

});
function clearTable(){
//	$("#tableShowXiaoqus").clear();
	$('tr').remove();

//	var datalength = $("#tableShowXiaoqus tr").length-2;
//	for(i=0;i<datalength;i++){
//		$("#tr"+i).dataTable().remove();
//	}
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