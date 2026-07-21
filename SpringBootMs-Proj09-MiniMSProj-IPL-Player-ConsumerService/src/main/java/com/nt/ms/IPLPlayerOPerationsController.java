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

import com.nt.client.IPLTeamServiceMSClient;
import com.nt.service.IPLPlayerMgmtService;
import com.nt.service.IPLPlayerMgmtServiceImpl;
import com.nt.vo.IPLPlayerVO;
import com.nt.vo.IPLTeamVO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/player-api")
public class IPLPlayerOPerationsController {

    private final IPLPlayerMgmtServiceImpl playerService_1;
	
	@Autowired
	private IPLPlayerMgmtService playerService;
	@Autowired
	private IPLTeamServiceMSClient client;


    IPLPlayerOPerationsController(IPLPlayerMgmtServiceImpl playerService_1) {
        this.playerService_1 = playerService_1;
    }
	
	
	@PostMapping("/save")
	@CircuitBreaker(name = "IPLPLAYER-SERVICE",fallbackMethod = "doFallbackForIPLTeam")
	public ResponseEntity<String>registerPlayer(@RequestBody IPLPlayerVO playervo){
		
		//get player,s team id
		int tid = playervo.getTeam().getTeamid();
		
		//get iplTeam object from target ms(iplteamms)
		IPLTeamVO teamvo = client.getTeamById(tid);//ms intra communication
		
		//set team object to player object
		playervo.setTeam(teamvo);
		
		//use service to save player and his team info
		String msg = playerService.registerPlayer(playervo);
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	
	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<IPLPlayerVO>>showAllPlayeres(){
		//use service
		List<IPLPlayerVO>listVo=playerService.findAllPlayers();
		
		return new ResponseEntity<List<IPLPlayerVO>>(listVo,HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public    ResponseEntity<IPLPlayerVO>  showPlayerById(@PathVariable int id){
			//use service
			IPLPlayerVO playerVO=playerService.findPlayerById(id);
			return  new ResponseEntity<IPLPlayerVO>(playerVO,HttpStatus.OK);
	}
	
	
	//circuit Breaker Fall Back method
	public    ResponseEntity<String>   doFallbackForIPLTeam(Exception e){
		return new ResponseEntity<String>("Problm in  IPLTeam Communication"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
