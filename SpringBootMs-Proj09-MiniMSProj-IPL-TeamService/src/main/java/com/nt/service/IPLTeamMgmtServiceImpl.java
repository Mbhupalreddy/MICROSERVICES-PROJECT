package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.SpringBootMsProj09MiniMsProjIplTeamServiceApplication;
import com.nt.advice.TeamNotFoundException;
import com.nt.entity.IPLTeam;
import com.nt.repository.IPLTeamRepository;
import com.nt.vo.IPLTeamVo;

@Service("IPLYeamService")
public class IPLTeamMgmtServiceImpl implements IPLTeamMgmtService {

    private final SpringBootMsProj09MiniMsProjIplTeamServiceApplication springBootMsProj09MiniMsProjIplTeamServiceApplication;
	
	@Autowired
	private IPLTeamRepository teamRepo;

    IPLTeamMgmtServiceImpl(SpringBootMsProj09MiniMsProjIplTeamServiceApplication springBootMsProj09MiniMsProjIplTeamServiceApplication) {
        this.springBootMsProj09MiniMsProjIplTeamServiceApplication = springBootMsProj09MiniMsProjIplTeamServiceApplication;
    }

	@Override
	public String registerIPLTeam(IPLTeamVo teamVo) {
	IPLTeam teamEntity = new IPLTeam();
	teamEntity.setCreatedBy(System.getProperty("user.name"));
	BeanUtils.copyProperties(teamVo, teamEntity);
	int idval = teamRepo.save(teamEntity).getTeamid();
	return "IPLTEAM IS SAVED WITH ID VALUE:  "+idval;
	}

	@Override
	public List<IPLTeamVo> getAllTeams() {
		Iterable<IPLTeam> listEntites = teamRepo.findAll();
		List<IPLTeamVo> listVO = new ArrayList<IPLTeamVo>();
		listEntites.forEach(entity->{
			IPLTeamVo teamVo=new IPLTeamVo();
			BeanUtils.copyProperties(entity, teamVo);
			listVO.add(teamVo);
		});
		return listVO;
	}

	@Override
	public IPLTeamVo getTeamById(int teamid) throws Exception {
		IPLTeam entity=teamRepo.findById(teamid).orElseThrow(()->new TeamNotFoundException("Invalid Team id!"));
		IPLTeamVo teamVo = new IPLTeamVo();
		BeanUtils.copyProperties(entity, teamVo);
		return teamVo;
	}

}
