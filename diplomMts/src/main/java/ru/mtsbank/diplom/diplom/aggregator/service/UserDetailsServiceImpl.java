package ru.mtsbank.diplom.diplom.aggregator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private CustomerRestService customerRestService;

    public UserDetailsServiceImpl(CustomerRestService customerRestService) {
        this.customerRestService = customerRestService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String pass = "";
        customerRestService.getAccountInfo().setUsername(username);
        try {
            pass = customerRestService.getUserPassword();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (pass == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Long sum;
        try {
            sum = Long.parseLong(customerRestService.getUserSum());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        customerRestService.getAccountInfo().setPassword(pass);
        customerRestService.getAccountInfo().setSum(sum);
        return new User(username, pass, getAuthorities("USER"));
    }

    private List<GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}