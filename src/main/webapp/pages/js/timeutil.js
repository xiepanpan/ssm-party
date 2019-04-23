 function duibi(a, b) {
		var d1 = new Date(a.replace(/\-/g, "\/"));  
	    var d2 = new Date(b.replace(/\-/g, "\/"));  
		if(d1 >= d2) {
			return false;
	    }
	    else{
	        return true;
	    }
	}
function comptime(beginTime,endTime) {
	var beginTimes = beginTime.substring(0, 10).split('-');
    var endTimes = endTime.substring(0, 10).split('-');
    beginTime = beginTimes[1] + '-' + beginTimes[2] + '-' + beginTimes[0] + ' ' + beginTime.substring(10, 16);
    endTime = endTimes[1] + '-' + endTimes[2] + '-' + endTimes[0] + ' ' + endTime.substring(10, 16);
    var a = (Date.parse(endTime.replace(/-/g,"/")) - Date.parse(beginTime.replace(/-/g,"/"))) / 3600 / 1000;
    if (a <=0) {
       return false;
    } else {
        return true;
    }
}
