package tw.com.pershing.service;

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
            throw new UserAlreadyExistException("There is an account with that email adress: " + customer.getEmail());
        }
        final Customer cust = new Customer();

        cust.setUsername(customer.getUsername());
        cust.setPassword(passwordEncoder.encode(customer.getPassword()));
        cust.setEmail(customer.getEmail());
        cust.setMobile(customer.getMobile());
        return repository.saveUser(cust);
    }
	
	public Customer login(final Customer customer) {
        List<Customer> resultList = repository.findCustomerByEmail(customer.getEmail());
        if (resultList.isEmpty()) {
        	throw new UserNotFoundException("There is not exists an account with that email adress: " + customer.getEmail());
        }
        if (passwordEncoder.matches(customer.getPassword(), resultList.get(0).getPassword())) {
        	return resultList.get(0);
        } else {
        	throw new PasswordNotMatchException("Password is not match");
        }
    }
	
	public void createVerificationTokenForUser(final Customer customer, final String token) {
        final VerificationToken myToken = new VerificationToken(token, customer);
        tokenRepository.save(myToken);
    }
	
	public boolean emailExist(final String email) {
        return repository.findCustomerByEmail(email).size() > 0;
    }
	
	public Customer getUser(String verificationToken){
		final VerificationToken token = tokenRepository.findByToken(verificationToken);
		if (token != null) {
            return token.getCustomer();
        }
        return null;
	}
}
