package com.nt.service;

import java.util.List;

import com.nt.vo.IPLPlayerVO;

public interface IPLPlayerMgmtService {
	
	public String registerPlayer(IPLPlayerVO playervo);
	public IPLPlayerVO findPlayerById(int id);
	public List<IPLPlayerVO> findAllPlayers();
}
