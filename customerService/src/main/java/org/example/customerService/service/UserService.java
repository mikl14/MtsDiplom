package org.example.customerService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.customerService.entity.BankOperation;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.example.customerService.entity.User;
import org.example.customerService.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final TokenGenerator tokenGenerator;
    private ObjectMapper mapper;

    public UserService(@Lazy UserRepository userRepository,@Lazy TokenGenerator tokenGenerator, ObjectMapper mapper) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
        this.mapper = mapper;
    }

    public void SaveUser(User user)
    {
        userRepository.save(user);
    }
    public String getUserTokenByUsername(String username)
    {
         User user = userRepository.findByUsername(username).orElse(null);
         if(user == null)
         {
             return "none";
         }
         else
         {
             return tokenGenerator.generateToken(user);
         }
    }

    public User getUser(String username)
    {
        User user = userRepository.findByUsername(username).orElse(null);
        if(user == null)
        {
            return null;
        }
        else
        {
            return user;
        }
    }

    public String userHistoryListToJson(List<BankOperation> bankOperations) throws JsonProcessingException {
        String json = mapper.writeValueAsString(bankOperations);
        return json;
    }

    public  List<BankOperation> JsonToOperationsList(String body) throws JsonProcessingException {
       return  mapper.readValue(body, new TypeReference<List<BankOperation>>() {});

    }
}
