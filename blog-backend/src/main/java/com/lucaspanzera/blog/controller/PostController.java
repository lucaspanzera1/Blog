package com.lucaspanzera.blog.controller;

import com.lucaspanzera.blog.model.Post;
import com.lucaspanzera.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:3000") // Para permitir requisições do React
public class PostController {

    @Autowired
    private PostService postService;

    // GET /api/posts - Listar posts publicados (para visitantes)
    @GetMapping
    public ResponseEntity<List<Post>> listarPostsPublicados() {
        List<Post> posts = postService.listarPublicados();
        return ResponseEntity.ok(posts);
    }

    // GET /api/posts/admin - Listar todos os posts (para admin)
    @GetMapping("/admin")
    public ResponseEntity<List<Post>> listarTodosOsPosts() {
        List<Post> posts = postService.listarTodos();
        return ResponseEntity.ok(posts);
    }

    // GET /api/posts/{id} - Buscar post específico
    @GetMapping("/{id}")
    public ResponseEntity<Post> buscarPostPorId(@PathVariable Long id) {
        Optional<Post> post = postService.buscarPorId(id);
        return post.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/posts - Criar novo post
    @PostMapping
    public ResponseEntity<Post> criarPost(@Valid @RequestBody Post post) {
        try {
            Post novoPost = postService.criarPost(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPost);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /api/posts/{id} - Atualizar post
    @PutMapping("/{id}")
    public ResponseEntity<Post> atualizarPost(@PathVariable Long id, @Valid @RequestBody Post post) {
        try {
            Post postAtualizado = postService.atualizarPost(id, post);
            return ResponseEntity.ok(postAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/posts/{id} - Deletar post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable Long id) {
        try {
            postService.deletarPost(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET /api/posts/buscar?titulo=texto - Buscar posts por título
    @GetMapping("/buscar")
    public ResponseEntity<List<Post>> buscarPostsPorTitulo(@RequestParam String titulo) {
        List<Post> posts = postService.buscarPorTitulo(titulo);
        return ResponseEntity.ok(posts);
    }
}
