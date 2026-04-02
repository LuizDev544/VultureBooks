package BookDonation.demo.presentation.Controller

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;

import java.util.List;

import BookDonation.demo.Domain.Model.Livro;
import BookDonation.demo.Domain.Service.LivroService;

@RestController
@RequestMapping("/api")
public class LivroController {

    public LivroController(LivroService service) {
        this.service = service;
    }

    @GetMapping("/public/livros")
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.ok(service.listar());
    }

     @GetMapping("/admin/livros/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/admin/livros")
    public ResponseEntity<Livro> criar(@RequestBody Livro livro) {
        return ResponseEntity.ok(service.salvar(livro));
    }

    @PutMapping("/admin/livros/{id}")
    public ResponseEntity<Livro> atualizar(
            @PathVariable Long id,
            @RequestBody Livro livro) {

        return ResponseEntity.ok(service.atualizar(id, livro));
    }

    @DeleteMapping("/admin/livros/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}