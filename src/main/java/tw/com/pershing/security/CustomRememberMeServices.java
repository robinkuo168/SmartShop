package tw.com.pershing.security;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import tw.com.pershing.domain.Customer;
import tw.com.pershing.repository.CustomerRepo;

public class CustomRememberMeServices extends PersistentTokenBasedRememberMeServices {
	@Autowired
    private CustomerRepo customerRepository;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();
    private PersistentTokenRepository tokenRepository = new InMemoryTokenRepositoryImpl();
    private String key;

    public CustomRememberMeServices(String key, UserDetailsService userDetailsService, PersistentTokenRepository tokenRepository) {
        super(key, userDetailsService, tokenRepository);
        this.tokenRepository = tokenRepository;
        this.key = key;
    }

    @Override
    protected void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication) {
        String username = ((Customer) successfulAuthentication.getPrincipal()).getEmail();
        logger.debug("Creating new persistent login for user " + username);
        PersistentRememberMeToken persistentToken = new PersistentRememberMeToken(username, generateSeriesData(), generateTokenData(), new Date());
        try {
            tokenRepository.createNewToken(persistentToken);
            addCookie(persistentToken, request, response);
        } catch (Exception e) {
            logger.error("Failed to save persistent token ", e);
        }
    }

    @Override
    protected Authentication createSuccessfulAuthentication(HttpServletRequest request, UserDetails user) {
        List<Customer> auser = customerRepository.findCustomerByEmail(user.getUsername());
        RememberMeAuthenticationToken auth = new RememberMeAuthenticationToken(key, auser.get(0), authoritiesMapper.mapAuthorities(user.getAuthorities()));
        auth.setDetails(authenticationDetailsSource.buildDetails(request));
        return auth;
    }

    private void addCookie(PersistentRememberMeToken token, HttpServletRequest request, HttpServletResponse response) {
        setCookie(new String[] { token.getSeries(), token.getTokenValue() }, getTokenValiditySeconds(), request, response);
    }
}
