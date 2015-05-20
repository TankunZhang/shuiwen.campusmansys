$("document").ready(function() {
	$('.zhekou').mask('99%');
	$('.keshi').mask('99');
	var huancun = $('#huancun').val();
	huancun=eval('('+huancun+')');
	$("#editkemutitle").html(huancun.km_mingzi+",单科优惠设置");
	
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
                              $(this).html("<input type=\"text\" style=\"width: 40px;\" value=\""+huancun.danjia+"\" >");  
                              break;  
                          case(3):  
                              $(this).html(huancun.morenkeshi*huancun.danjia);  
                              break; 
                          case(4):  
                              $(this).html(huancun.morenkeshi*i);  
                              break; 
                          case(7):  
                              $(this).className="zhekou";
                          alert($(this).className);
                              break; 
                          
                    }//end switch                          
	     });//end children.each  
	   
	    //把克隆好的tr追加原来的tr后面  
//	    clonedTr.append($("#editkemutbody"));  
	    $("#editkemutbody").append(clonedTr);
	 }//end $each  
//	$("#editkemutr").hide();
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
	
});