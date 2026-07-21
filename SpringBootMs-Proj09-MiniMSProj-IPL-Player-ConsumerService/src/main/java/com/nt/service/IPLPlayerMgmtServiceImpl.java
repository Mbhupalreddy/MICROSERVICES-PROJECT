package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nt.advice.PlayerNotFoundException;
import com.nt.entity.IPLPlayer;
import com.nt.entity.IPLTeam;
import com.nt.repository.IPLPlayerRepository;
import com.nt.repository.IPLTeamRepository;
import com.nt.vo.IPLPlayerVO;
import com.nt.vo.IPLTeamVO;

@Service("playerService")
public class IPLPlayerMgmtServiceImpl implements IPLPlayerMgmtService {
	@Autowired
	private IPLPlayerRepository playerRepo;
	@Autowired
	private IPLTeamRepository teamRepo;
	
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String registerPlayer(IPLPlayerVO playervo) {
		IPLPlayer playerEntity=new IPLPlayer();
		BeanUtils.copyProperties(playervo, playerEntity);
		
		IPLTeamVO teamvo = playervo.getTeam();
		IPLTeam teamEntity=teamRepo.findById(teamvo.getTeamid()).orElseThrow(()->new IllegalArgumentException("Invalid Team Id! "));
		BeanUtils.copyProperties(teamvo, teamEntity);
		
		playerEntity.setTeam(teamEntity);
		int idVal = playerRepo.save(playerEntity).getPid();
		
		
		return "Player is Registered with the Id::  "+idVal;
	}

	@Override
	public IPLPlayerVO findPlayerById(int id) {
		
		Optional<IPLPlayer> opt = playerRepo.findById(id);
		if(opt.isPresent()) {
			IPLPlayer playerEntity=opt.get();
			IPLTeam teamEntity=playerEntity.getTeam();
			
			
			IPLTeamVO teamvo=new IPLTeamVO();
			IPLPlayerVO playervo = new IPLPlayerVO();
			
			BeanUtils.copyProperties(teamEntity, teamvo);
			BeanUtils.copyProperties(playerEntity, playervo);
			
			playervo.setTeam(teamvo);
			return playervo;
			
			
		}
		throw new PlayerNotFoundException("Player Not Found!!");
	
	}

	@Override
	public List<IPLPlayerVO> findAllPlayers() {
		List<IPLPlayer>  listPlayerEntities = playerRepo.findAll();
		List<IPLPlayerVO> listPlayerVo=new ArrayList();
		
		listPlayerEntities.forEach(playerEntity->{
			IPLTeam teamEntity = playerEntity.getTeam();
			IPLPlayerVO playerVO = new IPLPlayerVO();
			IPLTeamVO teamVO = new IPLTeamVO();
			BeanUtils.copyProperties(teamEntity, teamVO);
			BeanUtils.copyProperties(playerEntity, playerVO);
			
			playerVO.setTeam(teamVO);
			listPlayerVo.add(playerVO);
			
		});
		
		return listPlayerVo;
	}

}
