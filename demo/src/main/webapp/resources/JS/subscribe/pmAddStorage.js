

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



function calculateCost(remain_day,extra_storage,storage_price){
	var extra_cost = document.getElementById("extra_cost");
	var cost = (extra_storage*storage_price)*remain_day;
	extra_cost.value = cost;

}


$(document).ready(async function() {
	
    try {
    	var extraStorage = document.getElementById("extra_storage");
	
        PCL = await fetchData("/get.pcl");
        PSData = await fetchData("/get.ps");
        
        let expire = new Date(PSData["expire_date"]);
        let today = new Date();
        today.setHours(9,0,0,0);
        let remain_day = Math.round((expire - today)/ (1000 * 60 * 60 * 24));
        console.log(remain_day);
    	var storage_price;
    	
    	for(var i=0; i<PCL.length; i++){
        	if(PCL[i]['commodity_id'] == PSData['commodity_id']){
    			storage_price = PCL[i]['storage_price']/30;
    			break;
        	}
        }
        
    	calculateCost(remain_day,extraStorage.value,storage_price);
    	console.log(remain_day);
        extraStorage.addEventListener('change',function(){
        	calculateCost(remain_day,extraStorage.value,storage_price);
        });
        

        
    } catch (error) {
        console.error("데이터를 가져오는 중 에러 발생:", error);
    }
});
	
