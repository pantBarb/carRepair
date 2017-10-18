package com.carRepair.carRepair.Web.AdminControllers;


import com.carRepair.carRepair.Converters.UserConverter;
import com.carRepair.carRepair.Domain.User;
import com.carRepair.carRepair.Forms.UserForm;
import com.carRepair.carRepair.Services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AdminController {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AdminController.class);

    private static final String USER_FORM = "userForm";
    private static final String BASE_URL = "/admin";

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/admin/home", "/admin"}, method = RequestMethod.GET)
    String getAdminView(){
        return "admin/home";
    }

    @RequestMapping(value = "/admin/create-user", method = RequestMethod.GET)
    String getCreateUserView(Model model){

        if(!model.containsAttribute(USER_FORM)){
            model.addAttribute(USER_FORM, new UserForm());
        }

        return "/admin/user/create-user";
    }

    @RequestMapping(name = "/admin/create-user", method = RequestMethod.POST)
    public String createUser(Model model, @Valid @ModelAttribute(name = USER_FORM) UserForm userForm,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userForm",bindingResult);
            redirectAttributes.addFlashAttribute(USER_FORM, userForm);
            redirectAttributes.addFlashAttribute("message", "Please fill the fields again");
            redirectAttributes.addFlashAttribute("errorMessage", "Create user failed!");
            return "redirect:/admin/create-user";
        }

        //TODO service <-new user exists

        User user = UserConverter.buildUserObjecr(userForm);
        try {
            userService.insertUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("message", "New user is created :"+userForm.getFirstname());
        return "redirect:/admin/create-user";
    }
//    @RequestMapping(value = {"/admin/home", "/admin"}, method = RequestMethod.GET)
//    String getAdminView(){
//        return "admin/home";
//    }
//
//    @RequestMapping(value = "/admin/create-user", method = RequestMethod.GET)
//    String getCreateUserView(Model model){
//
//        if(!model.containsAttribute(USER_FORM)){
//            model.addAttribute(USER_FORM, new UserForm());
//        }
//
//        return "/admin/user/new_user";
//    }
//
//    @RequestMapping(name = "/admin/create-user", method = RequestMethod.POST)
//    public String createUser(Model model, @Valid @ModelAttribute(name = USER_FORM) UserForm userForm,
//                             BindingResult bindingResult, RedirectAttributes redirectAttributes){
//
//        if(bindingResult.hasErrors()){
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userForm",bindingResult);
//            redirectAttributes.addFlashAttribute(USER_FORM, userForm);
//            redirectAttributes.addFlashAttribute("message", "Please fill the fields again");
//            redirectAttributes.addFlashAttribute("errorMessage", "Create user failed!");
//            return "redirect:/admin/create-user";
//        }
//
//        //TODO service <-new user exists
//        redirectAttributes.addFlashAttribute("message", "New user :"+userForm.getFirstname());
//        return "redirect:/admin/new_user";
//    }

}
