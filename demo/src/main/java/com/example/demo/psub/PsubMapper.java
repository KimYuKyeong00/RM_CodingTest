package com.example.demo.psub;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.pm.PrivateMember;
import com.example.demo.ps.PrivateStorage;

@Mapper
public interface PsubMapper {
	
	abstract List<PrivateCommodityList> getPrivateCommodity();
	abstract List<PrivateCommodityList> getPrivateCommodityById(String commodity_id);
	abstract Integer countPrivateCommodity();
	abstract int insertPrivateSubscribeOrder(PrivateSubscribeOrder pso);
	abstract int insertPrivateStorage(PrivateStorage ps);
	abstract int updateSubscribeZero(PrivateMember pm);
	abstract int updateSubscribeOne(PrivateMember pm);
	abstract List<PrivateMember> getPM(PrivateMember pm);
	abstract List<PrivateStorage> getPS(PrivateMember pm);
	abstract int upgradePS(PrivateSubscribeOrder pso);
	abstract int extensePS(PrivateStorage ps);
	abstract int addSubscriber(String commodity_id);
	abstract int drawSubscriber(String commodity_id);
	abstract int expandStorage(PrivateStorage ps);

}
