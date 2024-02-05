package com.example.demo.bsub;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bm.BusinessMember;
import com.example.demo.bs.BusinessStorage;

@Service
public class BsubDAO {
	
	@Autowired
	private BsubMapper bsubMapper;
	

	public List<BusinessCommodityList> getBCL(){		
		try {
			return bsubMapper.getBusinessCommodity();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public BusinessCommodityList getBC(String commodity_id) {
		try {
			BusinessCommodityList bc = bsubMapper.getBusinessCommodityById(commodity_id).get(0);
			return bc;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public BusinessCommodityList getBC(HttpServletRequest req) {
		try {
			BusinessMember bm = (BusinessMember) req.getSession().getAttribute("LoginMember");
			BusinessStorage bs = bsubMapper.getBS(bm).get(0);
			BusinessCommodityList bc = bsubMapper.getBusinessCommodityById(bs.getCommodity_id()).get(0);
			
			return bc;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	public boolean businessSubscribe(HttpServletRequest req) {
		
		try {
			BusinessMember bm = (BusinessMember) req.getSession().getAttribute("LoginMember");

			String commodity = req.getParameter("commodity");
			String capacity = req.getParameter("capacity");
			String period = req.getParameter("period");
			int countMember = Integer.parseInt(req.getParameter("countMember"));
			String bm_id = bm.getId();
				
			BusinessCommodityList bc = getBC(commodity);
			int service_price= bc.getService_price();
			int storage_price= bc.getStorage_price();
			
			int cost = ((service_price*countMember)+(storage_price*Integer.parseInt(capacity)))*Integer.parseInt(period);
			long max_storage = Long.parseLong(capacity) * 1099511627776L;
			
			BusinessSubscribeOrder bso = new BusinessSubscribeOrder();
			bso.setCommodity(commodity);
			bso.setCapacity(capacity);
			bso.setOrder_kind("new_subscribe");
			bso.setPeriod(period);
			bso.setTotal_pay(cost);
			bso.setBm_id(bm_id);
			bso.setNumber_of_emp(countMember);
			
			BusinessStorage bs = new BusinessStorage();
			bs.setCommodity_id(commodity);
			bs.setMax_storage(max_storage);
			bs.setStorage_status(0);
			bs.setBm_id(bm_id);
			bs.setMax_user(countMember);
			
			LocalDate ld_now = LocalDate.now();
			LocalDate ld_expire = ld_now.plusDays(Integer.parseInt(period)*30);
			Date expire_date = Date.valueOf(ld_expire);
			
			bs.setExpire_date(expire_date);
			
			if(bsubMapper.insertBusinessSubscribeOrder(bso)==1&&bsubMapper.insertBusinessStorage(bs)==1) {
				bsubMapper.updateSubscribeOne(bm);
				bsubMapper.addSubscriber(commodity);
				req.getSession().setAttribute("LoginMember", bsubMapper.getBM(bm).get(0));
				return true;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	
	public boolean bmUpgradeCommodity(HttpServletRequest req) {
		
		try {
			BusinessMember bm = (BusinessMember) req.getSession().getAttribute("LoginMember");
			BusinessStorage bs = bsubMapper.getBS(bm).get(0);
			String upgrade_commodity = req.getParameter("upgrade_commodity");
			List<BusinessCommodityList> bcl = getBCL();
			
			Date expire_date = bs.getExpire_date(); 
	        LocalDate local_expire_date = expire_date.toLocalDate();
	        LocalDateTime ldt_expire = local_expire_date.atStartOfDay();
	        LocalDateTime ldt_today = LocalDate.now().atStartOfDay();
	        
	        long remainSecond = ChronoUnit.SECONDS.between(ldt_today, ldt_expire);
	        long remain = remainSecond / (60 * 60 * 24);
			
			double service_before = 0;
			double storage_before = 0;
			double service_after = 0;
			double storage_after = 0;
			
			for(BusinessCommodityList bc:bcl) {
				if(bc.getCommodity_id().equals(bs.getCommodity_id())) {
					service_before = bc.getService_price()/30;
					storage_before = bc.getStorage_price()/30;
				}
				if(bc.getCommodity_id().equals(upgrade_commodity)) {
					service_after = bc.getService_price()/30;
					storage_after = bc.getStorage_price()/30;
				}
			}
			
			
			long storage = bs.getMax_storage()/1099511627776L;
			double cost_before = (service_before*bs.getMax_user()+storage*storage_before)*(remain);
			double cost_after = (service_after*bs.getMax_user()+storage*storage_after)*(remain);
	        double extra_cost = cost_after-cost_before;
			
	        if(extra_cost<=0) {
				extra_cost = 0;
			}
	        
	        BusinessSubscribeOrder bso = new BusinessSubscribeOrder();	  
	        bso.setOrder_kind("upgrade_commodity");
	        bso.setBm_id(bm.getBm_id());
	        bso.setCommodity(upgrade_commodity);
	        bso.setCapacity("-");
	        bso.setPeriod("-");
			bso.setTotal_pay((int) extra_cost);
			bso.setNumber_of_emp(0);
			
			bsubMapper.insertBusinessSubscribeOrder(bso);
			bsubMapper.upgradeBS(bso);
			bsubMapper.addSubscriber(upgrade_commodity);
			bsubMapper.drawSubscriber(bs.getCommodity_id());
			
			return true;
			
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	
	public boolean pmExtension(HttpServletRequest req) {
		try {
			BusinessMember bm = (BusinessMember) req.getSession().getAttribute("LoginMember");
			int extension_period = Integer.parseInt(req.getParameter("extension_period"));
			BusinessStorage bs = bsubMapper.getBS(bm).get(0);
			BusinessCommodityList bc = getBC(bs.getCommodity_id());
			int service_price= bc.getService_price();
			int storage_price= bc.getStorage_price();
			
			int storage = (int) (bs.getMax_storage()/ 1099511627776L);
			int cost = (service_price*bs.getMax_user()+storage_price*storage)*(extension_period);
			
			LocalDate expire = bs.getExpire_date().toLocalDate();
			LocalDate extended = expire.plusDays(30*extension_period);
			Date extended_date = Date.valueOf(extended);
			
			BusinessStorage extended_storage = new BusinessStorage();
			extended_storage.setBm_id(bm.getBm_id());
			extended_storage.setExpire_date(extended_date);
			
			BusinessSubscribeOrder bso = new BusinessSubscribeOrder();
			bso.setBm_id(bm.getBm_id());
			bso.setOrder_kind("extense_period");
			bso.setCommodity(bs.getCommodity_id());
			bso.setCapacity("-");
			bso.setPeriod(String.valueOf(extension_period));
			bso.setTotal_pay(cost);
			bso.setNumber_of_emp(0);
			
			bsubMapper.insertBusinessSubscribeOrder(bso);
			bsubMapper.extenseBS(extended_storage);
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	
	public boolean pmAddStorage(HttpServletRequest req) {
		try {
			
			int extra_storage = Integer.parseInt(req.getParameter("extra_storage"));
			BusinessMember bm = (BusinessMember)req.getSession().getAttribute("LoginMember");
			BusinessStorage bs = bsubMapper.getBS(bm).get(0);
			BusinessSubscribeOrder bso = new BusinessSubscribeOrder();
			bso.setBm_id(bm.getBm_id());
			bso.setCommodity(bs.getCommodity_id());
			bso.setNumber_of_emp(bs.getMax_user());
			bso.setOrder_kind("expand_storage");
			bso.setPeriod("-");
			bso.setCapacity(req.getParameter("extra_storage"));
			
			Date expire_date = bs.getExpire_date(); 
	        LocalDate local_expire_date = expire_date.toLocalDate();
	        LocalDateTime ldt_expire = local_expire_date.atStartOfDay();
	        LocalDateTime ldt_today = LocalDate.now().atStartOfDay();
	        
	        long remainSecond = ChronoUnit.SECONDS.between(ldt_today, ldt_expire);
	        long remain = remainSecond / (60 * 60 * 24);
			
			BusinessCommodityList bc = getBC(bs.getCommodity_id());
			int cost = (int)(((double)(bc.getStorage_price()/30)*extra_storage)*(remain));
			bso.setTotal_pay(cost);
			long storage_before = bs.getMax_storage();
			long storage_after = storage_before + extra_storage*1099511627776L;
			
			
			BusinessStorage expanded_storage = new BusinessStorage();
			expanded_storage.setBm_id(bm.getBm_id());
			expanded_storage.setMax_storage(storage_after);
			
			bsubMapper.insertBusinessSubscribeOrder(bso);
			bsubMapper.expandStorage(expanded_storage);
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

}
