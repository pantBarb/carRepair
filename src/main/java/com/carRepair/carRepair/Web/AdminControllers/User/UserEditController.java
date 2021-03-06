package com.carRepair.carRepair.Web.AdminControllers.User;

import com.carRepair.carRepair.Converters.MemberConverter;
import com.carRepair.carRepair.Domain.Member;
import com.carRepair.carRepair.Exceptions.UserExistException;
import com.carRepair.carRepair.Exceptions.UserNotFoundException;
import com.carRepair.carRepair.Forms.User.EditUserForm;
import com.carRepair.carRepair.Services.Member.MemberService;
import com.carRepair.carRepair.Utilities.AppUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

@Controller
public class UserEditController {

    private static final String EDIT_USER_FORM = "editUserForm";

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/admin/edit-user", method = RequestMethod.GET)
    public String getEditUserView(Model model, @RequestParam(name = "v", required = false) String vat,
                                  RedirectAttributes redirectAttributes){
        if(vat != null){
            try {
                Member member = memberService.getMemberByVat(vat);
                EditUserForm editUserForm = new EditUserForm(member); //TODO converter
                String role = (member.getUserType()) ? "admin" : "simple";
                model.addAttribute("editUserForm", editUserForm);
                model.addAttribute(role,"selected");
            } catch (UserNotFoundException e) {
                redirectAttributes.addFlashAttribute("errorMessage",e.getMessage());
                return "redirect:/admin/edit-user";
            }
        }
        return "/admin/user/edit-user-view";
    }

    @RequestMapping(value = "/admin/edit-user", method = RequestMethod.POST)
    public String editUser(Model model, @Valid @ModelAttribute(name = EDIT_USER_FORM) EditUserForm editUserForm, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        String role = (editUserForm.getUserType()) ? "admin" : "simple";
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editUserForm", bindingResult);
            redirectAttributes.addFlashAttribute(EDIT_USER_FORM, editUserForm);
            redirectAttributes.addFlashAttribute(role, "selected");
            return "redirect:/admin/edit-user";
        }

        try {
            Member member = memberService.getMemberById(editUserForm.getUserId());
            String memberPass = member.getPassword();
            String formPass = editUserForm.getPassword();
            String formNewPass = editUserForm.getNewPassword();
            String editPass = AppUtilities.editPassword(memberPass,formPass,formNewPass);

            if(editPass.equals("")){
                redirectAttributes.addFlashAttribute("passwordMessage", "Current and new password must have 8 characters at least");
                return "redirect:/admin/edit-user?v=" + member.getVat();
            }

            editUserForm.setPassword(editPass);
            Member editMember = MemberConverter.buildEditMemberObjecr(editUserForm);
            memberService.insertMember(editMember);
            String message = "Successful update!";
            redirectAttributes.addFlashAttribute("message", message);
        }catch (UserExistException | UserNotFoundException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute(EDIT_USER_FORM, editUserForm);
        }
        return "redirect:/admin/edit-user";
    }
}