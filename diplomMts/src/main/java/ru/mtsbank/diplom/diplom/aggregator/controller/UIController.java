package ru.mtsbank.diplom.diplom.aggregator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mtsbank.diplom.diplom.aggregator.entity.*;
import ru.mtsbank.diplom.diplom.aggregator.service.CustomerRestService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UIController {

    private final CustomerRestService customerRestService;

    public UIController(CustomerRestService customerRestService) {
        this.customerRestService = customerRestService;
    }

    /**
     * <b>basic</b> - отправляет пользователя на страницу index
     * @param model
     * @return
     */
    @GetMapping("/")
    public String basic(Model model) {
        return "redirect:/index";
    }

    /**
     * <b>index</b> - вызывает страницу index передает нужный атрибут для приветсвия пользователя
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("Name", "Здравствуйте! " + customerRestService.getAccountInfo().getUsername());
        return "index";
    }

    /**
     * <b>account</b>
     * -Метод который будет вызван при переходе на страницу account делает запрос текущего баланса
     *
     * @param model
     * @return
     * @throws IOException
     */
    @GetMapping("/account")
    public String account(Model model) throws IOException {
        customerRestService.getHistory();
        model.addAttribute("userInfo", new AccountInfo());
        model.addAttribute("accountSum", customerRestService.getAccountInfo().getSum().toString());
        model.addAttribute("bankOperationList", customerRestService.getAccountInfo().getOperationList());
        if (customerRestService.getAccountInfo().getSum() == 0) {
            model.addAttribute("error", "Пополните баланс для дальнейших операций!");
        }

        return "account";
    }

    /**
     * <b>bankAccountPlus</b>
     * Вызываеться при нажатии на кнопку пополнения баланса.
     *
     * @param userInfo
     * @param bindingResult
     * @param model
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/sum", params = "action=plus")
    public String bankAccountPlus(@ModelAttribute("userInfo") AccountInfo userInfo, BindingResult bindingResult, Model model) throws JsonProcessingException {
        if (bindingResult.hasErrors()) {
            return "account";
        }
        if (userInfo.getSum().longValue() > 0) {
            customerRestService.setUserSum(userInfo.getSum().longValue());
        }
        customerRestService.getAccountInfo().addOperationList(new BankOperation(BigDecimal.valueOf(userInfo.getSum().longValue()), "Пополнение", LocalDate.now()));
        customerRestService.setHistory();
        model.addAttribute("accountSum", customerRestService.getAccountInfo().getSum().toString());

        // Обработка данных
        return "redirect:/account";
    }

    /**
     * <b>bankAccountMinus</b> - Вызываеться при нажатии на кнопку снятия со счета.
     *
     * @param userInfo
     * @param bindingResult
     * @param model
     * @return
     * @throws IOException
     */

    @PostMapping(value = "/sum", params = "action=minus")
    public String bankAccountMinus(@ModelAttribute("userInfo") AccountInfo userInfo, BindingResult bindingResult, Model model) throws IOException {

        if (customerRestService.getAccountInfo().getSum() < userInfo.getSum()) {
            userInfo.setSum(customerRestService.getAccountInfo().getSum());
        }

        if (bindingResult.hasErrors()) {
            return "account";
        }
        if (userInfo.getSum().longValue() > 0) {
            customerRestService.setUserSum(userInfo.getSum().longValue() * -1);
        }
        customerRestService.getAccountInfo().addOperationList(new BankOperation(BigDecimal.valueOf(userInfo.getSum().longValue()), "Списание", LocalDate.now()));
        customerRestService.setHistory();
        model.addAttribute("accountSum", customerRestService.getAccountInfo().getSum().toString());
        // Обработка данных

        return "redirect:/account";
    }

    /**
     * <b>cancel</b> - по нажатию на кнопку cancel возвращает пользователя на страницу index
     *
     * @param model
     * @return
     * @throws IOException
     */

    @PostMapping(value = "/sum", params = "action=cancel")
    public String cancel(Model model) throws IOException {

        return "redirect:/index";
    }

    /**
     * <b>accountPost</b> -метод вызывается на странице account доавляет нужные объекты в модель для вывода в UI такие как список операций и информацию о счете
     *
     * @param model
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/account")
    public String accountPost(Model model) throws JsonProcessingException {
        customerRestService.getHistory();
        model.addAttribute("userInfo", new AccountInfo());
        model.addAttribute("accountSum", customerRestService.getAccountInfo().getSum().toString());
        model.addAttribute("bankOperationList", customerRestService.getAccountInfo().getOperationList());

        return "account";
    }

    /**
     * <b>contribution</b> - вызываеться на странице со вкладами образаеться к сервису Deposits для получения информации о вкладах пользователя
     *
     * @param model
     * @return
     * @throws JsonProcessingException
     */

    @GetMapping("/contribution")
    public String contribution(Model model) throws JsonProcessingException {

        model.addAttribute("accountSum", customerRestService.getAccountInfo().getSum().toString());

        List<Contribution> allDepositsList = customerRestService.getDeposits();

        List<Contribution> closedDepositsList = new ArrayList<>();

        List<Contribution> actualDepositsList = new ArrayList<>();

        for (Contribution contribution : allDepositsList) {
            if (contribution.getReturnCode() == null || contribution.getReturnCode().isEmpty()) {
                actualDepositsList.add(contribution);
            } else {
                closedDepositsList.add(contribution);
            }


        }

        model.addAttribute("depositList", actualDepositsList);
        model.addAttribute("closedDepositList", closedDepositsList);

        return "contribution";
    }

    /**
     * <b>contribution</b> - вызываеться на странице со вкладами образаеться к сервису Deposits для получения информации о вкладах пользователя. Вызываеться при нажатии на кнопку для перехода
     *
     * @param model
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/contribution")
    public String contributionPost(Model model) throws JsonProcessingException {

        model.addAttribute("accountSum", customerRestService.getAccountInfo().getSum().toString());
        List<Contribution> allDepositsList = customerRestService.getDeposits();

        List<Contribution> closedDepositsList = new ArrayList<>();

        List<Contribution> actualDepositsList = new ArrayList<>();

        for (Contribution contribution : allDepositsList) {
            if (contribution.getReturnCode() == null || contribution.getReturnCode().isEmpty()) {
                actualDepositsList.add(contribution);
            } else {
                closedDepositsList.add(contribution);
            }


        }

        model.addAttribute("depositList", actualDepositsList);
        model.addAttribute("closedDepositList", closedDepositsList);

        return "contribution";
    }

    /**
     * <b>createContribution</b> -вызывает страницу создания нового вклада предварительно добавив в модель объект для сохранения данных
     *
     * @param model
     * @return
     */
    @PostMapping("/create_contribution")
    @GetMapping("/create_contribution")
    public String createContribution(Model model) {
        model.addAttribute("inputData", new InputData());
        return "create_contribution";
    }

    /**
     * <b>submit</b> - вызываеться при нажатии на кнопку подтверждения на странице создания новго вклада проверяет что баланс позволяет открыть вклад
     *
     * @param inputData
     * @param model
     * @return
     * @throws JsonProcessingException
     */

    @PostMapping("/submit")
    public String submit(@ModelAttribute InputData inputData, Model model) throws JsonProcessingException {

        long userSum = customerRestService.getAccountInfo().getSum();
        long currentSum = Long.parseLong(inputData.getText());
        if (userSum <= currentSum) {
            customerRestService.setContribution(new Contribution("test-code", customerRestService.getAccountInfo().getUsername(), "Недостаточно средств", "Месяц", LocalDate.now(), LocalDate.now(), LocalDate.now().plusDays(30), new ContributionType("Домашний"), Long.parseLong(inputData.getText()), 12));
            return "redirect:/contribution";
        }
        customerRestService.getAccountInfo().setNeededSum(currentSum);
        return "confirm";
    }

    /**
     * <b>confirm</b> - вызывает страницу с подтверждением по смс
     *
     * @param model
     * @return
     */
    @PostMapping("/confirm")
    public String confirm(Model model) {
        return "confirm";
    }

    /**
     * <b>submitSMS</b> - Вызываеться по нажатию кнопки подверждения по смс проверяет введенный код на соответсвие условиям
     *
     * @param inputData
     * @param model
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/submitSMS")
    public String submitSMS(@ModelAttribute InputData inputData, Model model) throws JsonProcessingException {
        if (inputData.getCodeSMS().length() == 4 && inputData.getCodeSMS().startsWith("1")) {
            customerRestService.setUserSum(customerRestService.getAccountInfo().getNeededSum() * -1);
            customerRestService.setContribution(new Contribution("test-code", customerRestService.getAccountInfo().getUsername(), "", "Месяц", LocalDate.now(), LocalDate.now(), LocalDate.now().plusDays(30), new ContributionType("Домашний"), customerRestService.getAccountInfo().getNeededSum(), 12));
            return "redirect:/contribution";
        } else {
            return "redirect:/contribution";
        }
    }

    /**
     * <b>updateContribution</b> - вызывает страницу обновления вклада
     * @param model
     * @return
     */
    @GetMapping("/update_contribution")
    public String updateContribution(Model model) {
        return "update_contribution";
    }
}
