package com.example.winyourlife.authentication.domain;

import com.example.winyourlife.authentication.dto.PasswordResetRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/api/auth")
public record PasswordFormController(PasswordResetService passwordResetService) {

    @GetMapping("/change-password/{token}")
    public String showChangePasswordForm(Model model, @PathVariable String token) {
        model.addAttribute("token", token);
        return "change-password";
    }

    @PostMapping("/update-password")
    public String updatePassword(@ModelAttribute PasswordResetRequest request, Model model) {

        try {
            passwordResetService.resetPassword(request);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "changePassword";
        }

        model.addAttribute("success", "Password changed successfully!");
        return "passwordChangeSuccess";
    }
}
