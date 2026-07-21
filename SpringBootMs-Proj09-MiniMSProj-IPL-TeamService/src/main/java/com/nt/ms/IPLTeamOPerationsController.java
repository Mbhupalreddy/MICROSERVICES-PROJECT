package com.nt.ms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.service.IPLTeamMgmtService;
import com.nt.vo.IPLTeamVo;

@RestController
@RequestMapping("/team-api")
public class IPLTeamOPerationsController {
	
	@Autowired
	private IPLTeamMgmtService teamService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerTeeam(@RequestBody IPLTeamVo teamVo){
		String resultMsg = teamService.registerIPLTeam(teamVo);
		return new ResponseEntity<String>(resultMsg,HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<IPLTeamVo>> showAllTeams()throws Exception{
		List<IPLTeamVo> list = teamService.getAllTeams();
		return  new ResponseEntity<List<IPLTeamVo>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<IPLTeamVo> showTeamById(@PathVariable int id)throws Exception{
		IPLTeamVo teamVo = teamService.getTeamById(id);
		return new ResponseEntity<IPLTeamVo>(teamVo,HttpStatus.OK);
	}
	
	
	
}
