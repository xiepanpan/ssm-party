//小弹窗 只有确定按钮，点击按钮执行callback
function bootboxDialog(message,size,callback){
	bootbox.alert({
	    title: '提醒',
	    message: message,
	    size: size || 'small',
	    callback:callback || function(){}
	});
}

//弹窗 带确定和取消按钮 点击确定按钮执行successfn
function successDialog(title,message,successfn){
	var dialog = bootbox.dialog({
	    title: title||'提醒',
	    message: message,
	    size: 'small',
	    buttons: {
	        cancel: {
	            label: '<i class="fa fa-times"></i> 取消'
	        },
	        confirm: {
	            label: '<i class="fa fa-check"></i> 确定',
	    	    callback: successfn || function(){}
	        }
	    }
	});
}


//返回
function backcheck() {
	if (window.top == window.self) {
		window.close();
	}

	window.history.go(-1)
}