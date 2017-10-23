package com.carRepair.carRepair.Web.AdminControllers.Vehicle;

import com.carRepair.carRepair.Forms.Repair.RepairSearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VehicleSearchController {

    @RequestMapping(value = "/admin/search-vehicle", method = RequestMethod.GET)
    public String searchRepair(Model model){


        return "/admin/vehicle/search-vehicle-view";
    }

}