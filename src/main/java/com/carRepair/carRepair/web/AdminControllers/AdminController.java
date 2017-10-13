package com.carRepair.carRepair.web.AdminControllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin" ,  method = RequestMethod.GET)
    public String home(){

        //List<Service> services = findByDate();
        //ServicesForm()
        return "admin/home";
    }


    // Selida Owner(Customer)

    @RequestMapping(value = "/admin/customers" ,  method = RequestMethod.GET)
    public String customer(){

        //List<User> users = findAll(); // Users
        //CustomerForm()
        return "admin/customers";
    }

    @RequestMapping(value = "/admin/new-customer" ,  method = RequestMethod.GET)
    public String createCustomer(){

        //CreateUserForm()
        return "admin/new_customer";
    }

    @RequestMapping(value = "/admin/new-customer" ,  method = RequestMethod.POST)
    public String createCustomerPost(){

        //save(User user); // Users
        return "admin/new_customer";
    }

    @RequestMapping(value = "/admin/delete-customer" ,  method = RequestMethod.GET)
    public String deleteCustomer(){

        //DeleteUserForm()
        return "admin/delete_customer";
    }

    @RequestMapping(value = "/admin/delete-customer" ,  method = RequestMethod.POST)
    public String deleteCustomerPost(){

        //delete(Long id); // Users
        return "admin/delete_customer";
    }

    @RequestMapping(value = "/admin/update-customer" ,  method = RequestMethod.GET)
    public String updateCustomer(){

        //UpdateUserForm()
        return "admin/update_customer";
    }

    @RequestMapping(value = "/admin/update-customer" ,  method = RequestMethod.POST)
    public String updateCustomerPost(){

        //save(Long id , User user); // Users
        return "admin/update_customer";
    }

    @RequestMapping(value = "/admin/search-customer" ,  method = RequestMethod.GET)
    public String searchCustomer(){

        //SearchUserForm()
        return "admin/search_customer";
    }

    @RequestMapping(value = "/admin/search-customer" ,  method = RequestMethod.POST)
    public String searchCustomerPost(){

        //User user = {user}Service.findOne(User user); // Users
        return "admin/search_customer";
    }


}
