package com.lucaspanzera.blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 200, message = "Título deve ter no máximo 200 caracteres")
    @Column(nullable = false, length = 200)
    private String titulo;

    @NotBlank(message = "Conteúdo é obrigatório")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String conteudo;

    @Size(max = 500, message = "Resumo deve ter no máximo 500 caracteres")
    @Column(length = 500)
    private String resumo;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "publicado")
    private Boolean publicado = false;

    // Construtores
    public Post() {}

    public Post(String titulo, String conteudo, String resumo) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.resumo = resumo;
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

    public String getResumo() { return resumo; }
    public void setResumo(String resumo) { this.resumo = resumo; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    public Boolean getPublicado() { return publicado; }
    public void setPublicado(Boolean publicado) { this.publicado = publicado; }

    // Método para atualizar data de modificação
    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        if (this.dataCriacao == null) {
            this.dataCriacao = LocalDateTime.now();
        }
        this.dataAtualizacao = LocalDateTime.now();
    }
}