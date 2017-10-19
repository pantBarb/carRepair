package com.carRepair.carRepair.Web.AdminControllers;


import com.carRepair.carRepair.Converters.UserConverter;
import com.carRepair.carRepair.Domain.Member;
import com.carRepair.carRepair.Domain.Service;
import com.carRepair.carRepair.Domain.User;
import com.carRepair.carRepair.Exceptions.UserNotFoundException;
import com.carRepair.carRepair.Forms.SearchForm;
import com.carRepair.carRepair.Forms.UserForm;
import com.carRepair.carRepair.Services.RepairService;
import com.carRepair.carRepair.Services.SearchService;
import com.carRepair.carRepair.Services.UserService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AdminController.class);

    private static final String USER_FORM = "userForm";
    private static final String BASE_URL = "/admin";
    private static final String SEARCH_FORM = "searchForm";


    @Autowired
    private UserService userService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private RepairService repairService;

    @RequestMapping(value = {"/admin/home", "/admin"}, method = RequestMethod.GET)
    String getAdminView(Model model){

        List<Service> services = repairService.getDailyServices();
        model.addAttribute("services" , services);

        return "admin/home";
    }

    @RequestMapping(value = "/admin/create-user", method = RequestMethod.GET)
    String getCreateUserView(Model model){

        if(!model.containsAttribute(USER_FORM)){
            model.addAttribute(USER_FORM, new UserForm());
        }

        return "/admin/user/create-user";
    }

    @RequestMapping(value = "/admin/create-user", method = RequestMethod.POST)
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

    @RequestMapping(value = "/admin/search-user", method = RequestMethod.GET)
    public String searchUser(Model model){


        model.addAttribute(SEARCH_FORM, new SearchForm());
        return "/admin/user/search_user";
    }

    @RequestMapping(value = "/admin/search-user", method = RequestMethod.POST)
    public String searchUserPost(Model model , @ModelAttribute(SEARCH_FORM) SearchForm searchForm){
        Member member = null;
        try {
            member = searchService.getMemberByVatOrMail(searchForm.getVat(), searchForm.getEmail());
        }catch(UserNotFoundException userNotFound){
            System.out.println("User not Found controller" + userNotFound);

        }
        model.addAttribute("member",member);
        return "/admin/user/update_user";
    }


}
