package BookDonation.demo.presentation.Controller;

import BookDonation.demo.presentation.DTO.LivroRequestDTO;
import org.springframework.ui.Model;
import BookDonation.demo.Domain.Model.Livro;
import BookDonation.demo.Domain.Service.LivroService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/cadastrar")
    public String mostrarTelaCadastro() {
        return "PainelCadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(LivroRequestDTO dto, RedirectAttributes attributes) {
        try {
            livroService.criarLivro(dto);
            
            attributes.addFlashAttribute("mensagem", "Livro cadastrado com sucesso!");

            return "redirect:/livros/painel"; 
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Erro ao cadastrar: " + e.getMessage());

            return "redirect:/livros/cadastrar"; 
        }
    }

    @GetMapping("/painel")
    public String mostrarPainelAdm(Model model) {
        List<Livro> listaDeLivros = livroService.listarTodosOsLivros();
        
        model.addAttribute("livros", listaDeLivros);
        
        return "PainelADM";
    }
}