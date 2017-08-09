package assignment.user.controllers;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import assignment.user.common.MailUtils;
import assignment.user.models.User;
import assignment.user.models.UserLoginToken;
import assignment.user.repositories.UserLoginTokenRepository;
import assignment.user.repositories.UserRepository;
@RestController
@RequestMapping("/login")
public class LoginRestController {

     	@Autowired
     	MailUtils utils;
		@Autowired
        private UserRepository repository;
        @Autowired
        private UserLoginTokenRepository tokenRepo;
        
        @Value("${login.expires.min}")
    	private String exp;

        @RequestMapping(method = RequestMethod.GET, value = {"/{id}"})
        public ResponseEntity<?> loginToken(@PathVariable Long id) {
        	HttpStatus status=HttpStatus.OK;
        	if(repository.exists(id)){
        		UserLoginToken token=new UserLoginToken();
        		token.setUserId(id);
        		token.setToken(UUID.randomUUID().toString());
        		User user=repository.findOne(id);
        		if(utils.sendLoginMail(user,token.getToken()))
        			tokenRepo.save(token);
        		else
        			status=HttpStatus.SERVICE_UNAVAILABLE;
        	} else
        		status=HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>("", status);
        }

        @RequestMapping(method = RequestMethod.GET, value = {"/go/{token}"})
        public ResponseEntity<User> login(@PathVariable String token) {
        	HttpStatus status=HttpStatus.OK;
        	User usr=null;
        	UserLoginToken usrTok = tokenRepo.findByToken(token);
        	if(usrTok!=null){
        		Date cd=usrTok.getCreatedOn();
        		Long ct=cd.getTime();
        		Long now=System.currentTimeMillis();
        		int min=15;//default
        		if(exp!=null)
        			min=Integer.parseInt(exp);
        					
        		if((now-ct)<(min*60*1000))
        			usr=repository.findOne(usrTok.getUserId());
        		else
        			status=HttpStatus.UNAUTHORIZED;
        			
        		
        	}else
        		status=HttpStatus.BAD_REQUEST;
        	return new ResponseEntity<>(usr, status);
        }
}
