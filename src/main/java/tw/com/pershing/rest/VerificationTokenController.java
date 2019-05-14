package tw.com.pershing.rest;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.pershing.domain.Customer;
import tw.com.pershing.domain.VerificationToken;
import tw.com.pershing.repository.VerificationTokenRepository;

@RestController
@RequestMapping("/verifytoken")
public class VerificationTokenController {
	
	@Autowired
	VerificationTokenRepository verificationTokenRepository;
	
	@RequestMapping(value = "/token")
	public VerificationToken byToken(@RequestParam(value = "token") String token) {
		return verificationTokenRepository.findByToken(token);
	}
	
	@RequestMapping(value = "/customer")
	public VerificationToken byCustomer(@RequestBody Customer customer) {
		return verificationTokenRepository.findByCustomer(customer);
	}
	
	@RequestMapping(value = "/expiredtoken")
	public void expireToken(@RequestParam(value = "expiryDate") String now) {
		verificationTokenRepository.deleteAllExpiredSince(Date.valueOf(now));
	}
}
