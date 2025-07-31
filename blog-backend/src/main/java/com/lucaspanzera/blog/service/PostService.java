package com.lucaspanzera.blog.service;

import com.lucaspanzera.blog.model.Post;
import com.lucaspanzera.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // Criar novo post
    public Post criarPost(Post post) {
        post.setDataCriacao(LocalDateTime.now());
        post.setDataAtualizacao(LocalDateTime.now());
        return postRepository.save(post);
    }

    // Listar todos os posts (para admin)
    public List<Post> listarTodos() {
        return postRepository.findAllByOrderByDataCriacaoDesc();
    }

    // Listar apenas posts publicados (para visitantes)
    public List<Post> listarPublicados() {
        return postRepository.findByPublicadoTrueOrderByDataCriacaoDesc();
    }

    // Buscar post por ID
    public Optional<Post> buscarPorId(Long id) {
        return postRepository.findById(id);
    }

    // Atualizar post
    public Post atualizarPost(Long id, Post postAtualizado) {
        Optional<Post> postExistente = postRepository.findById(id);

        if (postExistente.isPresent()) {
            Post post = postExistente.get();
            post.setTitulo(postAtualizado.getTitulo());
            post.setConteudo(postAtualizado.getConteudo());
            post.setResumo(postAtualizado.getResumo());
            post.setPublicado(postAtualizado.getPublicado());
            post.setDataAtualizacao(LocalDateTime.now());

            return postRepository.save(post);
        }

        throw new RuntimeException("Post não encontrado com ID: " + id);
    }

    // Deletar post
    public void deletarPost(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        } else {
            throw new RuntimeException("Post não encontrado com ID: " + id);
        }
    }

    // Buscar posts por título
    public List<Post> buscarPorTitulo(String titulo) {
        return postRepository.findByTituloContainingAndPublicadoTrue(titulo);
    }
}
