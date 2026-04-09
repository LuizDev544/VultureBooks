package BookDonation.demo.presentation.Controller;

import BookDonation.demo.presentation.DTO.LivroRequestDTO;
import BookDonation.demo.Domain.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping("/cadastrar")
    public String cadastrar(LivroRequestDTO dto, RedirectAttributes attributes) {
        try {
            livroService.criarLivro(dto);
            
            attributes.addFlashAttribute("mensagem", "Livro cadastrado com sucesso!");
            
            return "redirect:/PainelADM";
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Erro ao cadastrar: " + e.getMessage());
            return "redirect:/PainelCadastrar";
        }
    }
}