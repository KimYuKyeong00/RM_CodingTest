package com.example.demo.bsub;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.bm.BusinessMember;
import com.example.demo.bs.BusinessStorage;


@Mapper
public interface BsubMapper {
	abstract List<BusinessCommodityList> getBusinessCommodity();
	abstract List<BusinessCommodityList> getBusinessCommodityById(String commodity_id);
	abstract int insertBusinessSubscribeOrder(BusinessSubscribeOrder bso);
	abstract int insertBusinessStorage(BusinessStorage bs);
	abstract int updateSubscribeZero(BusinessMember bm);
	abstract int updateSubscribeOne(BusinessMember bm);
	abstract List<BusinessMember> getBM(BusinessMember bm);
	abstract List<BusinessStorage> getBS(BusinessMember bm);
	abstract int upgradeBS(BusinessSubscribeOrder bso);
	abstract int extenseBS(BusinessStorage bs);
	abstract int addSubscriber(String commodity_id);
	abstract int drawSubscriber(String commodity_id);
	abstract int expandStorage(BusinessStorage bs);
}
