package com.nt.service;

import java.util.List;

import com.nt.vo.IPLTeamVo;

public interface IPLTeamMgmtService {
	public String registerIPLTeam(IPLTeamVo teamVo);
	public List<IPLTeamVo> getAllTeams();
	public IPLTeamVo getTeamById(int teamid) throws Exception;

}
