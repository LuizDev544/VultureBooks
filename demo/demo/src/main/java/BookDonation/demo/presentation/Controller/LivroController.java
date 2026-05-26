package BookDonation.demo.presentation.Controller;

import BookDonation.demo.presentation.DTO.LivroRequestDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import BookDonation.demo.Domain.Model.*;
import BookDonation.demo.Domain.Service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    @Qualifier("livroHistoricoDecorator") // Força o Spring a usar o Decorator com Histórico
    private LivroOperations livroService; // Usando a interface diretamente

    @Autowired
    private BookDonation.demo.Domain.Repository.HistoricoRepository historicoRepository;

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

            // Passa pelo Decorator e gera o log de histórico automaticamente!
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
            // Chamada limpa e segura através da interface
            Livro livro = livroService.buscarPorId(id);
            model.addAttribute("livro", livro);
            return "PainelEditar"; 
            
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("erro", "Não foi possível encontrar um livro com o ID #" + id);
            return "redirect:/livros/painel"; 
        }
    }

    @PostMapping("/editar/{id}")
    public String atualizarLivro(@PathVariable Long id, LivroRequestDTO dto, HttpSession session, RedirectAttributes attributes) {
        try {
            Long idAdminLogado = (Long) session.getAttribute("adminLogadoId");

            if (idAdminLogado == null) {
                return "redirect:/admin/login";
            }

            livroService.atualizarLivro(id, dto, idAdminLogado);
            
            attributes.addFlashAttribute("mensagem", "Livro atualizado com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Erro ao atualizar: " + e.getMessage());
        }
        return "redirect:/livros/painel";
    }

    @GetMapping("/painel")
    public String mostrarPainelAdm(Model model) {
        // Chamada direta corrigida (Sem o casting que quebrava o sistema)
        List<Livro> listaDeLivros = livroService.listarTodosOsLivros();
        
        model.addAttribute("livros", listaDeLivros);
        
        return "PainelADM";
    }

    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable Long id, HttpSession session, RedirectAttributes attributes) {
        try {
            Long idAdminLogado = (Long) session.getAttribute("adminLogadoId");
            
            if (idAdminLogado == null) {
                return "redirect:/admin/login";
            }

            livroService.excluirLivro(id, idAdminLogado);
            
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
        // Chamada corrigida usando a interface
        livroService.alternarDisponibilidade(id);
        attributes.addFlashAttribute("mensagem", "Livro liberado com sucesso!");
        return "redirect:/livros/painel"; 
    }

    // Rota para ver o histórico no front-end
    @GetMapping("/historico")
    public String mostrarTelaHistorico(Model model) {
        // Busca todos os logs salvos pelo Decorator
        List<Historico> listaHistorico = historicoRepository.findAll();
        
        model.addAttribute("historicos", listaHistorico);
        return "PainelHistorico";
    }
}