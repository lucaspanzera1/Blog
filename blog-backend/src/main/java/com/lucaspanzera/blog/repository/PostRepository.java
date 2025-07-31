package com.lucaspanzera.blog.repository;

import com.lucaspanzera.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Buscar apenas posts publicados (para visitantes)
    List<Post> findByPublicadoTrueOrderByDataCriacaoDesc();

    // Buscar todos os posts ordenados por data (para admin)
    List<Post> findAllByOrderByDataCriacaoDesc();

    // Buscar posts por título (busca)
    @Query("SELECT p FROM Post p WHERE p.titulo LIKE %?1% AND p.publicado = true")
    List<Post> findByTituloContainingAndPublicadoTrue(String titulo);
}
