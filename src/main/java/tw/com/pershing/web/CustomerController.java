package tw.com.pershing.web;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.pershing.domain.Customer;
import tw.com.pershing.domain.VerificationToken;
import tw.com.pershing.repository.CustomerRepo;
import tw.com.pershing.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CustomerRepo customerMananger;
	
	@Autowired
	CustomerService customerService;
	

	@RequestMapping(value = "/customer")
	public List<Customer> customerList(@RequestParam(value = "name") String username) {
		return customerMananger.findCustomerByAccountId(username);
	}

	@RequestMapping(value = "/save")
	public Customer saveCust(@RequestParam(value = "name") String username) {
		Customer customer = new Customer();
		customer.setUsername(username);
		return customerMananger.saveUser(customer);
	}
	
	@RequestMapping(value = "/registration")
	public VerificationToken registerCustomer(@RequestBody Customer customer) {
		final Customer registCustomer = customerService.registerNewUserAccount(customer);
		String token = UUID.randomUUID().toString();
		VerificationToken returnToken = customerService.createVerificationTokenForUser(registCustomer, token);
		return returnToken;
	}
	
	@RequestMapping(value = "/login")
	public VerificationToken login(@RequestBody Customer customer) {
		final VerificationToken token = customerService.login(customer);
//		String token = UUID.randomUUID().toString();
//		customerService.createVerificationTokenForUser(registedCustomer, token);
		return token;
	}

	@RequestMapping(value = "/save2")
	public Customer saveCust2(Customer customer) {
		return customerMananger.saveUser(customer);
	}
	
	@RequestMapping(value = "/savepost", method=RequestMethod.POST)
	public Customer saveCustPost(@RequestBody Customer customer) {
		LOGGER.debug("Registering user account with information: {}", customer);
		return customerMananger.saveUser(customer);
	}

	@RequestMapping(value = "/remove")
	public Boolean deleteCust(@RequestParam(value = "name") String username) {
		Customer customer = new Customer();
		customer.setUsername(username);
		customerMananger.deleteUser(customer);
		return true;
	}
}
