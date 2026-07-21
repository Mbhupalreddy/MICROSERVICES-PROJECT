package com.nt.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nt.vo.IPLTeamVO;

@FeignClient("IPLTEAM-SERVICE")
public interface IPLTeamServiceMSClient {

	 @GetMapping("/team-api/find/{id}")
	 public  IPLTeamVO   getTeamById(@PathVariable int id);
}
