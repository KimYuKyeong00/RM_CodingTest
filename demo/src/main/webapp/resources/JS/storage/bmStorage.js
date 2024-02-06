$(document).ready(function(){
	$.ajax({
		type: "POST",
        url: "/get.bs",
        dataType: "json",
        success: function (BSdata) {
        	var commodity = document.getElementById("commodity");
        	var expire_day = document.getElementById("expire_day");
        	var storage_status = document.getElementById("storage_status");
        	commodity.value= BSdata["commodity_id"];
        	expire_day.value = BSdata["expire_date"];
        	var storage_capacity = (BSdata["max_storage"]/1073741824).toFixed(0);
        	var storage_charged = (BSdata["storage_status"]/1073741824).toFixed(2);
          	storage_status.value = storage_capacity + "/" + storage_charged;
        }
		
	});
	

	
});