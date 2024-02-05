

let PCL;
let PSData;

function fetchData(url) {
    return new Promise(function(resolve, reject) {
        $.ajax({
            type: "POST",
            url: url,
            dataType: "json",
            success: function(data) {
                resolve(data);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                reject(errorThrown);
            }
        });
    });
}


function formatDate(date) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

function calculateExpire(expire,period){
	var extended_date = document.getElementById("extended");
	var extended = new Date(expire);
	extended.setDate(expire.getDate() + 30*period);
	extended_date.value = formatDate(extended);
}

function calculateCost(PCL,PSData,period){
	var extension_cost = document.getElementById("extension_cost");
	var storage = PSData["max_storage"]/ 1099511627776;
	var service_price;
	var storage_price;
	
	for(var i=0; i<PCL.length; i++){
    	if(PCL[i]['commodity_id'] == PSData['commodity_id']){
    		service_price = PCL[i]['service_price'];
			storage_price = PCL[i]['storage_price'];
			break;
    	}
    }
	
	var cost = (service_price+storage*storage_price)*period;
	extension_cost.value = cost;
}


$(document).ready(async function() {
	
    try {
    	var selectExtension = document.getElementById("private_extension");
    	var expire_date = document.getElementById("expire");
    	
    	
        PCL = await fetchData("/get.pcl");
        PSData = await fetchData("/get.ps");
        
        let expire = new Date(PSData["expire_date"]);
        calculateExpire(expire,selectExtension.value);
        calculateCost(PCL,PSData,selectExtension.value);
        expire_date.value = PSData["expire_date"];
        
        selectExtension.addEventListener('change',function(){
        	var period = selectExtension.value;
        	calculateExpire(expire,period);
        	calculateCost(PCL,PSData,period);
        });
        

        
    } catch (error) {
        console.error("데이터를 가져오는 중 에러 발생:", error);
    }
});
	
