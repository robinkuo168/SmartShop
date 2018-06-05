package tw.com.pershing.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tw.com.pershing.domain.Customer;
import tw.com.pershing.domain.VerificationToken;
import tw.com.pershing.repository.CustomerRepo;
import tw.com.pershing.repository.VerificationTokenRepository;
import tw.com.pershing.web.error.PasswordNotMatchException;
import tw.com.pershing.web.error.UserAlreadyExistException;
import tw.com.pershing.web.error.UserNotFoundException;

@Service
public class CustomerService {
	
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
        cust.setMobile(customer.getMobile());
        cust.setBirthdate(customer.getBirthdate());
        cust.setAge(customer.getAge());
        cust.setOccupation(customer.getOccupation());
        cust.setMarriage(customer.getMarriage());
        cust.setNumbersofchildren(customer.getNumbersofchildren());
        cust.setCreateAt(new Date());
        cust.setLastRequestAt(new Date());
        cust.setUpdateAt(new Date());
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
}
