$(document).ready(function(){
	
	
	function calculatePrice(pcl){
		var cost = document.getElementById('cost');
		var commodity_id = document.getElementById('service_id').value;
		var storage_max = document.getElementById('storage_capacity').value;
		var period = document.getElementById('period').value;
		
		var service_price;
		var storage_price;
		for(var i=0; i<pcl.length; i++){
			if(commodity_id==pcl[i]['commodity_id']){
				service_price = pcl[i]['service_price'];
				storage_price = pcl[i]['storage_price'];
				break;
			}
		}		
		cost.value = (storage_max*storage_price+service_price)*period;
			
	}
	
	
	$.ajax({
		type: "POST",
        url: "/get.pcl",
        dataType: "json",
        success: function (pcl) {
            console.log(pcl);
            var pcl_len = pcl.length;
            var selectService = document.getElementById('service_id');
            var selectCapacity = document.getElementById('storage_capacity');
            var selectPeriod = document.getElementById('period');
            
            for(var i=0; i<pcl_len; i++){
            	var optionElement = document.createElement('option');
            	optionElement.value = pcl[i]["commodity_id"]; 
                optionElement.textContent = pcl[i]["commodity_id"];
                selectService.appendChild(optionElement);
            }
            
            calculatePrice(pcl);
            
            selectService.addEventListener('change', function(){
            	calculatePrice(pcl);
            });
            
            selectCapacity.addEventListener('change', function(){
            	calculatePrice(pcl);
            });
            
            selectPeriod.addEventListener('change', function(){
            	calculatePrice(pcl);
            });
            
            
        },
        error: function (error) {
            console.error("Error:", error);
        }
	});
});