

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

function getUpgradeList(commodity_id, PCL){
	var service_price_before;
	var storage_price_before;
	for(var i=0; i<PCL.length; i++){
    	if(PCL[i]['commodity_id'] == PSData['commodity_id']){
    		service_price_before = PCL[i]['service_price'];
			storage_price_before = PCL[i]['storage_price'];
			break;
    	}
    }
	
	var upgradeList = [];
	for(var j=0; j<PCL.length; j++){
		var upgrade ={}
		if((PCL[j]['service_price']+PCL[j]['storage_price'])>(storage_price_before+service_price_before)){
			upgrade.commodity_id = PCL[j]['commodity_id'];
			upgrade.service_price = PCL[j]['service_price']/30;
			upgrade.storage_price = PCL[j]['storage_price']/30;
			upgradeList.push(upgrade);
		}
	}
	
	return upgradeList;
}

$(document).ready(async function() {
    try {
        PCL = await fetchData("/get.pcl");
        PSData = await fetchData("/get.ps");
        
        let expire = new Date(PSData["expire_date"]);
        let today = new Date();
        today.setHours(9,0,0,0);
        let remain_day = Math.round((expire - today)/ (1000 * 60 * 60 * 24));
        let storage = PSData["max_storage"]/ 1099511627776;
        var service_price_before;
		var storage_price_before;
		
		console.log(today);
		console.log(expire);
		
        for(var i=0; i<PCL.length; i++){
        	if(PCL[i]['commodity_id'] == PSData['commodity_id']){
        		service_price_before = PCL[i]['service_price']/30;
				storage_price_before = PCL[i]['storage_price']/30;
				break;
        	}
        }
        
        let table = $("#pmUpgradeCommodity");
        let upgradeList = getUpgradeList(PSData['commodity_id'],PCL);
        var cost_before = Math.round((service_price_before+(storage*storage_price_before))*(remain_day));
        var cost_after;
        
        if(upgradeList.length==0){
        	table.append($("<tr><td>이미 최고등급의 서비스를 구독하고 계십니다.<td></tr>"));
        }
        
        upgradeList.forEach(function(ul){
        	var tr = $("<tr></tr>");
        	var upgradeButton = $("<button></button>").attr('form','pmUpgradeCommodityForm');
        	upgradeButton.text(ul.commodity_id+"로 업그레이드");
        	upgradeButton.attr('value',ul.commodity_id);
        	upgradeButton.attr('name',"upgrade_commodity");
        	var ci = $("<td></td>").text(ul.commodity_id);
        	cost_after = 	Math.round((ul.service_price+(storage*ul.storage_price))*(remain_day));
        	var extra_cost = cost_after-cost_before;
        	if(extra_cost<=0){
        		extra_cost = 0;
        	}
        	var extra_cost = $("<td></td>").text("비용 :"+ extra_cost);
        	tr.append(ci);
        	tr.append(extra_cost);
        	tr.append(upgradeButton);
        	table.append(tr);
        });
                
        
        
        
    } catch (error) {
        console.error("데이터를 가져오는 중 에러 발생:", error);
    }
});
	
