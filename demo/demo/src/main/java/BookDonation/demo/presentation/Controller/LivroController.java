package BookDonation.demo.presentation.Controller;

import BookDonation.demo.presentation.DTO.LivroRequestDTO;
import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;
import BookDonation.demo.Domain.Model.*;
import BookDonation.demo.Domain.Repository.AdminRepository;
import BookDonation.demo.Domain.Service.*;

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

    // Exibe a tela de formulário para novo cadastro
    @GetMapping("/cadastrar")
    public String mostrarTelaCadastro(Model model) {
        return "PainelCadastrar";
    }

    // Processa a criação do livro vinculado ao admin logado
    @PostMapping("/cadastrar")
    public String cadastrar(LivroRequestDTO dto, HttpSession session, RedirectAttributes attributes) {
        try {
            Long idAdminLogado = (Long) session.getAttribute("adminLogadoId");

            if (idAdminLogado == null) {
                return "redirect:/admin/login";
            }

            livroService.criarLivro(dto, idAdminLogado);
            
            attributes.addFlashAttribute("mensagem", "Livro cadastrado com sucesso!");
            return "redirect:/livros/painel"; 
            
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Erro ao cadastrar: " + e.getMessage());
            return "redirect:/livros/cadastrar"; 
        }
    }

    // Carrega dados de um livro para a tela de edição
    @GetMapping("/editar/{id}")
    public String mostrarTelaEditar(@PathVariable Long id, Model model, RedirectAttributes attributes) {
        try {
            Livro livro = livroService.buscarPorId(id);
            model.addAttribute("livro", livro);
            return "PainelEditar"; 
            
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("erro", "Não foi possível encontrar um livro com o ID #" + id);
            return "redirect:/livros/painel"; 
        }
    }

    // Salva as alterações de um livro existente
    @PostMapping("/editar/{id}")
    public String atualizarLivro(@PathVariable Long id, LivroRequestDTO dto, RedirectAttributes attributes) {
        try {
            livroService.atualizarLivro(id, dto);
            
            attributes.addFlashAttribute("mensagem", "Livro atualizado com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Erro ao atualizar: " + e.getMessage());
        }
        return "redirect:/livros/painel";
    }

    // Lista todos os livros no painel de administração
    @GetMapping("/painel")
    public String mostrarPainelAdm(Model model) {
        List<Livro> listaDeLivros = livroService.listarTodosOsLivros();
        
        model.addAttribute("livros", listaDeLivros);
        
        return "PainelADM";
    }

    // Remove um livro por ID com tratamento de erro
    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            livroService.excluirLivro(id);
            
            attributes.addFlashAttribute("mensagem", "Livro excluído com sucesso!");
            
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("erro", "Erro ao excluir: Não foi possível encontrar o livro #" + id);
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Erro interno ao tentar excluir o livro.");
        }
        
        return "redirect:/livros/painel"; 
    }

    // Alterna a disponibilidade (status) do livro
    @PostMapping("/disponibilizar/{id}") 
    public String disponibilizar(@PathVariable Long id, RedirectAttributes attributes) {
        livroService.alternarDisponibilidade(id);
        attributes.addFlashAttribute("mensagem", "Livro liberado com sucesso!");
        return "redirect:/livros/painel"; 
    }
}