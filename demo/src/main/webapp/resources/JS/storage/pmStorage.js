$(document).ready(function(){
	$.ajax({
		type: "POST",
        url: "/get.ps",
        dataType: "json",
        success: function (PSdata) {
        	var commodity = document.getElementById("commodity");
        	var expire_day = document.getElementById("expire_day");
        	var storage_status = document.getElementById("storage_status");
        	commodity.value= PSdata["commodity_id"];
        	expire_day.value = PSdata["expire_date"];
        	var storage_capacity = (PSdata["max_storage"]/1073741824).toFixed(0);
        	var storage_charged = (PSdata["storage_status"]/1073741824).toFixed(2);
          	storage_status.value = storage_capacity + "/" + storage_charged;
        }
		
	});
	

	
});
