$(document).ready(function(){
	
	
	function calculatePrice(bcl){
		var cost = document.getElementById('cost');
		var commodity_id = document.getElementById('service_id').value;
		var storage_max = document.getElementById('storage_capacity').value;
		var period = document.getElementById('period').value;
		var countMember = document.getElementById('countMember').value;
		
		var service_price;
		var storage_price;
		for(var i=0; i<bcl.length; i++){
			if(commodity_id==bcl[i]['commodity_id']){
				service_price = bcl[i]['service_price'];
				storage_price = bcl[i]['storage_price'];
				break;
			}
		}		
		cost.value = (storage_max*storage_price+service_price*countMember)*period;
			
	}
	
	
	$.ajax({
		type: "POST",
        url: "/get.bcl",
        dataType: "json",
        success: function (bcl) {
            var bcl_len = bcl.length;
            var selectService = document.getElementById('service_id');
            var selectCapacity = document.getElementById('storage_capacity');
            var selectPeriod = document.getElementById('period');
            var countMember = document.getElementById('countMember');
            
            for(var i=0; i<bcl_len; i++){
            	var optionElement = document.createElement('option');
            	optionElement.value = bcl[i]["commodity_id"]; 
                optionElement.textContent = bcl[i]["commodity_id"];
                selectService.appendChild(optionElement);
            }
            
            calculatePrice(bcl);
            
            selectService.addEventListener('change', function(){
            	calculatePrice(bcl);
            });
            
            selectCapacity.addEventListener('change', function(){
            	calculatePrice(bcl);
            });
            
            selectPeriod.addEventListener('change', function(){
            	calculatePrice(bcl);
            });
            
            countMember.addEventListener('change',function(){
            	calculatePrice(bcl);
            });
            
            
        },
        error: function (error) {
            console.error("Error:", error);
        }
	});
});