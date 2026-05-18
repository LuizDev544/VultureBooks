package BookDonation.demo.presentation.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession; 
import BookDonation.demo.Domain.Service.AdminService;
import BookDonation.demo.Domain.Model.Admin;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "LoginAdm"; 
    }

    @PostMapping("/login")
    public String realizarLogin(@RequestParam String email, 
                                @RequestParam String senha, 
                                HttpSession session, 
                                RedirectAttributes attributes) {
        
        if (!this.adminService.validarAcesso(email, senha)) {
            attributes.addFlashAttribute("erro", "Email ou senha incorretos!");
            return "redirect:/admin/login";
        }
        
        Admin adminLogado = this.adminService.buscarPorEmail(email);
        session.setAttribute("adminLogadoId", adminLogado.getId());
        
        return "redirect:/livros/painel";
    }

    @GetMapping("/painel")
    public String painelPage() {
        return "PainelAdm"; 
    }
    
    @GetMapping("/logout")
    public String realizarLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
}