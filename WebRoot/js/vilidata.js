String.prototype.isTel = function()
{
    //"兼容格式: 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"
    //return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));
    return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(this));
}
String.prototype.trim = function() 
{ 
	return this.replace(/(^\s*)|(\s*$)/g, ""); 
} 

String.prototype.isMail = function() 
{ 
	return (/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(this));
} 

String.prototype.isPostalCode = function() 
{ 
	return (/^[0-9]{6}$/.test(this));
}

String.prototype.isPhoneNum = function() 
{ 
	return (/^[1][3,5,8,7][0-9]{9}$/.test(this));
}

function validataForm(obj){
	var chils = document.getElementById(obj).getElementsByTagName("input");
	return vilidataInput(chils);
}


function validataTableForm(){
	var chils = [];
	tds = getClass("td", "tdEditContent");
	for(var j=0;j<tds.length;j++){
		var inputs = tds[j].getElementsByTagName("input");
		if(inputs.length>0 && inputs[0])
			chils[chils.length] = inputs[0];
	}
	return vilidataInput(chils);
}

function vilidataInput(chils){
	for(var i=0;i<chils.length;i++){
	    if (chils[i].getAttribute("notNull") == "true") {
	        if(chils[i].value.trim() == ""){
	        	alert(chils[i].getAttribute("label")+"不可为空");
	        	chils[i].focus();
	        	return false;
	        }
	    }
	    if (chils[i].getAttribute("isNum")) {
	        if(isNaN(chils[i].value)){
	        	alert(chils[i].getAttribute("label")+"请输入数字");
	        	chils[i].focus();
	        	return false;
	        }
	    }
	    if (chils[i].getAttribute("isMail")=="true") {
        	if(!chils[i].value.isMail()){
              	alert("请输入有效的E_mail！");
              	chils[i].focus();
	        	return false;
        	}
	    }
	  	if(chils[i].getAttribute("isPostalCode")=="true"){
	  		if(!chils[i].value.isPostalCode()){
              	alert("请输入有效的邮编!");
              	chils[i].focus();
	        	return false;
    		}
	  	}
	  	if(chils[i].getAttribute("isPhoneNum")=="true"){
	  		if(!chils[i].value.isPhoneNum()){
              	alert("请输入有效的手机号码!");
              	chils[i].focus();
	        	return false;
    		}
	  	}
	  	if(chils[i].getAttribute("isTelNum")=="true"){
	  		if(!chils[i].value.isTel()){
	  			alert("请输入有效的电话号码");
	  			chils[i].focus();
	  			return false;
	  		}
	  	}
	}
	return true
}

function getClass(tagname, className) { //tagname指元素，className指class的值
    //判断浏览器是否支持getElementsByClassName，如果支持就直接的用
    
       if (document.getElementsByClassName) { 
           return document.getElementsByClassName(className);
       }
       else {    //当浏览器不支持getElementsByClassName的时候用下面的方法
           var tagname = document.getElementsByTagName(tagname);  //获取指定元素
           var tagnameAll = [];     //这个数组用于存储所有符合条件的元素
           for (var i = 0; i < tagname.length; i++) {     //遍历获得的元素
               if (tagname[i].className == className) {     //如果获得的元素中的class的值等于指定的类名，就赋值给tagnameAll
                   tagnameAll[tagnameAll.length] = tagname[i];
               }
           }
           return tagnameAll;
       }
   }
