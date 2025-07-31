# Backend Blog | Java + SpringBoot
#### O projeto foi criado em MVC + Service e Repository
```bash
Cliente (Postman/React) 
    ↓
Controller (recebe requisições HTTP)
    ↓
Service (regras de negócio)
    ↓
Repository (acesso ao banco)
    ↓
Database (PostgreSQL/Supabase)
```

### @Entity
```bash
@Entity
@Table(name = "posts")
public class Post {
```
#### No SpringBoot, "essa classe é uma tabela no banco de dados"

### @RestController
#### Recebe as chamadas HTTP

### @Autowire
#### No SpringBoot, ele cria uma instância de PostService.

### @GetMapping, @PostMapping, etc.
#### Mapeia as rotas do CRUD.

## Fluxo do Projeto
1- Cliente faz requisição
2- Controller recebe
3- Service processa
4- Repository salva no banco de dados
5- A resposta volta para o Cliente.

## JPA/Hibernate | Dependência SpringBoot
#### **Problema**: Java fala "objetos", banco de dados fala "tabelas"
#### **Solução**: JPA traduz um para o outro
```bash
// Objeto Java
Post post = new Post();
post.setTitulo("Meu Post");

// JPA automaticamente gera:
// INSERT INTO posts (titulo) VALUES ('Meu Post');
```

## application.properties | Config App
#### spring.jpa.hibernate.ddl-auto=create-drop -> Cria as tabelas no SUPABASE (caso não existirem), adiciona colunas novas, e quando a aplicação para de rodar, exclui os dados inseridos.
##### ddl-auto=update -> Para não apagar os dados após a app se encerrar.

## CORS | Requisições
#### O CORS, libera que outras Portas/Domínios realizem chamadas na API. (Futuramente o Front-end)
```bash
@CrossOrigin(origins = "http://localhost:3000")
```

## SUPABASE | Conectividade
#### Ao realizar testes quanto a conectividade do meu PC, ao banco do Supabase, descobri que minhas requisições não chegavam lá...
#### Minha internet, bloqueia portas externas, não libera uma saída IPv6 para a internet. 5432, padrão do PostgreeSQL.
#### Testei com 4G, e o erro que acontecia ao rodar a APP, ou dar um Ping na URL do Banco de Dados parou.
```bash
ping: db.url.supabase.co
ping: connect: A rede está fora de alcance
```

