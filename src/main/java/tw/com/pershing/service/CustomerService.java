package tw.com.pershing.service;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.com.pershing.domain.Customer;
import tw.com.pershing.domain.MemberRequest;
import tw.com.pershing.domain.MemberResponse;
import tw.com.pershing.domain.VerificationToken;
import tw.com.pershing.repository.CustomerRepo;
import tw.com.pershing.repository.VerificationTokenRepository;
import tw.com.pershing.web.error.PasswordNotMatchException;
import tw.com.pershing.web.error.UserAlreadyExistException;
import tw.com.pershing.web.error.UserNotFoundException;

@Service
public class CustomerService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String URL = "http://203.70.195.201:8090/MemberService.svc/Member/UserCheckExistByUID";
	private static final String PLATFORM = "Expo1";
	private static final String PLATFORM_KEY = "";
	
	@Autowired
	CustomerRepo repository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Customer registerNewUserAccount(final Customer customer) {
        if (emailExist(customer.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that e-mail adress: " + customer.getEmail());
        } else if (accountIdExist(customer.getAccountId())) {
            throw new UserAlreadyExistException("There is an account with that account id: " + customer.getAccountId());
        }
        final Customer cust = new Customer();

        cust.setAccountId(customer.getAccountId());
        cust.setUsername(customer.getUsername());
        cust.setPassword(passwordEncoder.encode(customer.getPassword()));
        cust.setEmail(customer.getEmail());
        cust.setIdNo(customer.getIdNo());
        cust.setGender(customer.getGender());
        cust.setMobile(customer.getMobile());
        cust.setBirthdate(customer.getBirthdate());
        cust.setAge(customer.getAge());
        cust.setOccupation(customer.getOccupation());
        cust.setMarriage(customer.getMarriage());
        cust.setNumbersofchildren(customer.getNumbersofchildren());
        cust.setCreateAt(new Date());
        cust.setLastRequestAt(new Date());
        cust.setUpdateAt(new Date());
        
        MemberResponse isEslite = this.isEsliteCustomer(cust.getIdNo());
        cust.setIsMember(Byte.valueOf(isEslite.getCode()));
        cust.setCardNo(isEslite.getCardNo());
        
        final Customer returnCustomer = repository.saveUser(cust);
        returnCustomer.setPassword(null);
        return returnCustomer;
    }
	
	public VerificationToken login(final Customer customer) {
        List<Customer> resultList = repository.findCustomerByEmailOrAccountId(customer.getEmail());
        if (resultList.isEmpty()) {
        	throw new UserNotFoundException("There is not exists an account with that email adress: " + customer.getEmail());
        }
        if (passwordEncoder.matches(customer.getPassword(), resultList.get(0).getPassword())) {
        	VerificationToken token = tokenRepository.findByCustomer(resultList.get(0));
        	token.setCustomer(null);
        	return token;
        } else {
        	throw new PasswordNotMatchException("Password is not match");
        }
    }
	
	public VerificationToken createVerificationTokenForUser(final Customer customer, final String token) {
        final VerificationToken myToken = new VerificationToken(token, customer);
        VerificationToken returnToken = tokenRepository.save(myToken);
        returnToken.setCustomer(null);
        return returnToken;
    }
	
	public boolean emailExist(final String email) {
        return repository.findCustomerByEmail(email).size() > 0;
    }
	
	public boolean accountIdExist(final String username) {
        return repository.findCustomerByAccountId(username).size() > 0;
    }
	
	public Customer getUser(String verificationToken){
		final VerificationToken token = tokenRepository.findByToken(verificationToken);
		if (token != null) {
            return token.getCustomer();
        }
        return null;
	}
	
	public MemberResponse isEsliteCustomer(final String idNo) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		String temp = PLATFORM + PLATFORM_KEY + timestamp.getTime() + idNo;
		MessageDigest md;
		byte[] digest = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(temp.getBytes());
			digest = md.digest();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		}
		String accessKey = DatatypeConverter.printHexBinary(digest).toLowerCase();
		
		MemberRequest member = new MemberRequest();
		member.setAccessKey(accessKey);
		member.setPlatform(PLATFORM);
		member.setTimestamp(String.valueOf(timestamp.getTime()));
		member.setUid(idNo);
		ObjectMapper mapper = new ObjectMapper();
		String payload = null;
		try {
			payload = mapper.writeValueAsString(member);
			logger.info("JSON CONVERT: {}", payload);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
		}
		StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(URL);
		request.setHeader("Content-type", "application/json");
		request.setEntity(entity);
		
		HttpResponse response;
		MemberResponse[] resArr = null;
		try {
			response = httpClient.execute(request);
			HttpEntity resEntity = response.getEntity();
			String content = EntityUtils.toString(resEntity, "UTF-8");
			resArr = mapper.readValue(content, MemberResponse[].class);
			logger.info(String.valueOf(response.getStatusLine().getStatusCode()));
			logger.info(content);
			logger.info("IdNumber: [{}], CardNumber: [{}]", idNo, resArr[0].getCardNo());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return resArr[0];
	}
}
