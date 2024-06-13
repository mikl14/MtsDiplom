package org.example.customerService;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.customerService.entity.BankOperation;
import org.example.customerService.entity.User;
import org.example.customerService.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/customer/api")
public class RestController {

    private final UserService userService;

    public RestController(UserService userService) {
        this.userService = userService;
    }

    /**
     * <b>getUserToken</b> - возвращает токен пользователя
     *
     * @param username
     * @return
     */
    @PostMapping("/getUserToken")
    public String getToken(@RequestBody String username) {

        String token = userService.getUserTokenByUsername(username);
        if (token.equals("none")) {
            return "none";
        }
        return token;
    }

    /**
     * <b>getUserToken</b> - возвращает пароль пользователя
     *
     * @param username
     * @return
     */
    @PostMapping("/getPassword")
    public String getPassword(@RequestBody String username) {

        User user = userService.getUser(username);
        if (user == null) {
            return "";
        }
        return user.getPassword();
    }

    /**
     * <b>getUserToken</b> - возвращает сумму на счете пользователя
     *
     * @param username
     * @return
     */
    @PostMapping("/getSum")
    public String getSum(@RequestBody String username) {

        User user = userService.getUser(username);
        if (user == null) {
            return "";
        }
        return user.getBankAccount().getSum().toString();
    }

    /**
     * <b>getUserToken</b> - изменяет сумму на счету пользователя
     *
     * @param body
     * @return
     */
    @PostMapping("/setSum")
    public String setSum(@RequestBody String body) {
        String[] userData = body.split(":");
        User user = userService.getUser(userData[0]);
        if (user == null) {
            return "";
        }
        Long sum = Long.parseLong(userData[1]) + user.getBankAccount().getSum();
        user.getBankAccount().setSum(sum);
        userService.SaveUser(user);
        return user.getBankAccount().getSum().toString();
    }

    /**
     * <b>getHistory</b> - возвращает историю операций со счетом пользователя
     *
     * @param username
     * @return
     */
    @PostMapping("/getHistory")
    public String getHistory(@RequestBody String username) throws JsonProcessingException {
        User user = userService.getUser(username);
        if (user == null) {
            return "";
        }
        return userService.userHistoryListToJson(user.getBankAccount().getOperations());
    }

    /**
     * <b>getHistory</b> - изменяет историю операций со счетом пользователя
     *
     * @param body
     * @return
     */
    @PostMapping("/setHistory")
    public String setHistory(@RequestBody String body) throws JsonProcessingException {
        String[] userData = body.split("`");
        User user = userService.getUser(userData[0]);
        if (user == null) {
            return "";
        }

        List<BankOperation> bankOperationList = userService.JsonToOperationsList(userData[1]);

        for (BankOperation operation : bankOperationList) {
            operation.setBankAccount(user.getBankAccount());
        }
        user.getBankAccount().getOperations().addAll(bankOperationList);

        userService.SaveUser(user);
        return "success";
    }
}
