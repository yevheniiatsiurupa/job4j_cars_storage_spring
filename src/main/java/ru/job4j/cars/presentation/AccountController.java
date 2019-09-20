package ru.job4j.cars.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.cars.models.Account;
import ru.job4j.cars.validation.ErrorException;
import ru.job4j.cars.validation.ValidateService;

@Controller
public class AccountController {
    private ValidateService validateService;

    @Autowired
    public AccountController(ValidateService validateService) {
        this.validateService = validateService;
    }

    @RequestMapping(value = "/account-create", method = RequestMethod.GET)
    public String createAccountPage(Model model) {
        return "account-create";
    }

    @RequestMapping(value = "/account-create", method = RequestMethod.POST)
    public String createAccount(@ModelAttribute Account account, Model model) {
        String response;
        try {
            this.validateService.addOrUpdateAccount(account);
            response = "Аккаунт успешно добавлен.";
        } catch (ErrorException e) {
            response = e.getMessage();
        }
        model.addAttribute("answer", response);
        return "account-createPost";
    }


}
