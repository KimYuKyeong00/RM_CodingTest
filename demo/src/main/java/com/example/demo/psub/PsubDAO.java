package com.example.demo.psub;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pm.PrivateMember;
import com.example.demo.ps.PrivateStorage;

@Service
public class PsubDAO {
	
	@Autowired
	private PsubMapper psubMapper;
	

	public List<PrivateCommodityList> getPCL(){		
		try {
			return psubMapper.getPrivateCommodity();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public PrivateCommodityList getPC(String commodity_id) {
		try {
			PrivateCommodityList pc = (PrivateCommodityList) psubMapper.getPrivateCommodityById(commodity_id).get(0);
			return pc;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public PrivateCommodityList getPC(HttpServletRequest req) {
		try {
			PrivateMember pm = (PrivateMember) req.getSession().getAttribute("LoginMember");
			PrivateStorage ps = psubMapper.getPS(pm).get(0);
			PrivateCommodityList pc = psubMapper.getPrivateCommodityById(ps.getCommodity_id()).get(0);
			
			return pc;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	public boolean privateSubscribe(HttpServletRequest req) {
		
		try {
			PrivateMember pm = (PrivateMember) req.getSession().getAttribute("LoginMember");
			String commodity = req.getParameter("commodity");
			String capacity = req.getParameter("capacity");
			String period = req.getParameter("period");
			String pm_id = pm.getId();
				
			PrivateCommodityList pc = getPC(commodity);
			int service_price= pc.getService_price();
			int storage_price= pc.getStorage_price();
			
			int cost = (service_price+(storage_price*Integer.parseInt(capacity)))*Integer.parseInt(period);
			long max_storage = Long.parseLong(capacity) * 1099511627776L;
			
			PrivateSubscribeOrder pso = new PrivateSubscribeOrder();
			pso.setCommodity(commodity);
			pso.setCapacity(capacity);
			pso.setOrder_kind("new_subscribe");
			pso.setPeriod(period);
			pso.setTotal_pay(cost);
			pso.setPm_id(pm_id);
			
			PrivateStorage ps = new PrivateStorage();
			ps.setCommodity_id(commodity);
			ps.setMax_storage(max_storage);
			ps.setStorage_status(0);
			ps.setPm_id(pm_id);
			
			LocalDate ld_now = LocalDate.now();
			LocalDate ld_expire = ld_now.plusDays(Integer.parseInt(period)*30);
			Date expire_date = Date.valueOf(ld_expire);
			
			ps.setExpire_date(expire_date);
			
			if(psubMapper.insertPrivateSubscribeOrder(pso)==1&&psubMapper.insertPrivateStorage(ps)==1){
				//구독 여부(subscribe) 를 1 로 바꾸기
				psubMapper.updateSubscribeOne(pm);
				psubMapper.addSubscriber(commodity);
				req.getSession().setAttribute("LoginMember", psubMapper.getPM(pm).get(0));
				return true;
				
			}else {
				return false;
			}

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	
	public boolean pmUpgradeCommodity(HttpServletRequest req) {
		
		try {
			PrivateMember pm = (PrivateMember)req.getSession().getAttribute("LoginMember");
			PrivateStorage ps = (PrivateStorage) psubMapper.getPS(pm).get(0);
			String upgrade_commodity = req.getParameter("upgrade_commodity");
			Date d = ps.getExpire_date();
			System.out.println(d);
			
			List<PrivateCommodityList> pcl = getPCL();
			
			Date expire_date = ps.getExpire_date(); 
	        LocalDate local_expire_date = expire_date.toLocalDate();
	        LocalDateTime ldt_expire = local_expire_date.atStartOfDay();
	        LocalDateTime ldt_today = LocalDate.now().atStartOfDay();
	        
	        long remainSecond = ChronoUnit.SECONDS.between(ldt_today, ldt_expire);
	        long remain = remainSecond / (60 * 60 * 24);
			
			double service_before = 0;
			double storage_before = 0;
			double service_after = 0;
			double storage_after = 0;
			
			for(PrivateCommodityList pc: pcl) {
				if(pc.getCommodity_id().equals(ps.getCommodity_id())) {
					service_before = pc.getService_price()/30;
					storage_before = pc.getStorage_price()/30;
				}
				
				if(pc.getCommodity_id().equals(upgrade_commodity)) {
					service_after = pc.getService_price()/30;
					storage_after = pc.getStorage_price()/30;
				}
			}
			
			long storage = ps.getMax_storage()/1099511627776L;
			double cost_before = (service_before+storage*storage_before)*(remain);
			double cost_after = (service_after+storage*storage_after)*(remain);
	        double extra_cost = cost_after-cost_before;
			
	        if(extra_cost<=0) {
				extra_cost = 0;
			}
	        
	        PrivateSubscribeOrder pso = new PrivateSubscribeOrder();
	        pso.setOrder_kind("upgrade_commodity");
	        pso.setPm_id(pm.getPm_id());
	        pso.setCommodity(upgrade_commodity);
	        pso.setCapacity("-");
	        pso.setPeriod("-");
			pso.setTotal_pay((int) extra_cost);
			
			psubMapper.insertPrivateSubscribeOrder(pso);
			psubMapper.upgradePS(pso);
			psubMapper.addSubscriber(upgrade_commodity);
			psubMapper.drawSubscriber(ps.getCommodity_id());
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	
	public boolean pmExtension(HttpServletRequest req) {
		try {
			PrivateMember pm = (PrivateMember) req.getSession().getAttribute("LoginMember");
			int extension_period = Integer.parseInt(req.getParameter("extension_period"));
			PrivateStorage ps = psubMapper.getPS(pm).get(0);
			PrivateCommodityList pc = getPC(ps.getCommodity_id());
			int service_price= pc.getService_price();
			int storage_price= pc.getStorage_price();
			
			int storage = (int) (ps.getMax_storage()/ 1099511627776L);
			int cost = (service_price+storage_price*storage)*(extension_period);
			
			LocalDate expire = ps.getExpire_date().toLocalDate();
			LocalDate extended = expire.plusDays(30*extension_period);
			Date extended_date = Date.valueOf(extended);
			
			PrivateStorage extended_storage = new PrivateStorage();
			extended_storage.setPm_id(pm.getPm_id());
			extended_storage.setExpire_date(extended_date);
			
			PrivateSubscribeOrder pso = new PrivateSubscribeOrder();
			pso.setPm_id(pm.getPm_id());
			pso.setOrder_kind("extense_period");
			pso.setCommodity(ps.getCommodity_id());
			pso.setCapacity("-");
			pso.setPeriod(String.valueOf(extension_period));
			pso.setTotal_pay(cost);
			
			psubMapper.insertPrivateSubscribeOrder(pso);
			psubMapper.extensePS(extended_storage);
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	
	public boolean pmAddStorage(HttpServletRequest req) {
		try {
			int extra_storage = Integer.parseInt(req.getParameter("extra_storage"));
			PrivateMember pm = (PrivateMember) req.getSession().getAttribute("LoginMember");
			PrivateStorage ps = psubMapper.getPS(pm).get(0);
			PrivateCommodityList pc = getPC(ps.getCommodity_id());
			PrivateSubscribeOrder pso = new PrivateSubscribeOrder();
			pso.setPm_id(pm.getPm_id());
			pso.setOrder_kind("expand_storage");
			pso.setPeriod("-");
			pso.setCommodity(ps.getCommodity_id());
			pso.setCapacity(req.getParameter("extra_storage"));
			
			Date expire_date = ps.getExpire_date(); 
	        LocalDate local_expire_date = expire_date.toLocalDate();
	        LocalDateTime ldt_expire = local_expire_date.atStartOfDay();
	        LocalDateTime ldt_today = LocalDate.now().atStartOfDay();
	        
	        long remainSecond = ChronoUnit.SECONDS.between(ldt_today, ldt_expire);
	        long remain = remainSecond / (60 * 60 * 24);
	        
	        int cost = (int)(((double)pc.getStorage_price()/30)*remain);
	        
	        pso.setTotal_pay(cost);
			long storage_before = ps.getMax_storage();
			long storage_after = storage_before + extra_storage*1099511627776L;
			
			PrivateStorage expanded_storage = new PrivateStorage();
			expanded_storage.setPm_id(pm.getPm_id());
			expanded_storage.setMax_storage(storage_after);
			
			psubMapper.insertPrivateSubscribeOrder(pso);
			psubMapper.expandStorage(expanded_storage);
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
