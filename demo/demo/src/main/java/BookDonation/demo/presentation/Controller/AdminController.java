package BookDonation.demo.presentation.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession; 
import BookDonation.demo.Domain.Service.AdminService;
import BookDonation.demo.Domain.Model.Admin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/login")
    public String loginPage() {
        // LINHA TEMPORÁRIA: Gerar o hash exato do seu ambiente
        System.out.println("HASH_GERADO_AQUI: " + new BCryptPasswordEncoder().encode("12345678"));
        return "LoginAdm"; 
    }

    @PostMapping("/login")
    public String realizarLogin(@RequestParam(value = "email", required = false) String email, 
                                @RequestParam(value = "senha", required = false) String senha, 
                                HttpSession session, 
                                Model model) {
        
        if (email == null || senha == null || !this.adminService.validarAcesso(email, senha)) {
            model.addAttribute("erro", "Email ou senha incorretos!");
            return "LoginAdm";
        }
        
        Admin adminLogado = this.adminService.buscarPorEmail(email);
        session.setAttribute("adminLogadoId", adminLogado.getId());
        
        return "PainelAdm";
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