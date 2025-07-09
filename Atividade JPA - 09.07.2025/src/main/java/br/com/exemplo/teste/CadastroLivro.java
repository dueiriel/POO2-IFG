package br.com.exemplo.teste;

import br.com.exemplo.modelo.Autor;
import br.com.exemplo.modelo.Editora;
import br.com.exemplo.modelo.Livro;
import br.com.exemplo.modelo.TipoPublicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroLivro {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca-jpa");
        EntityManager em = emf.createEntityManager();

        Autor autor = new Autor("J.R.R. Tolkien", "Sul-Africano");
        Editora editora = new Editora("HarperCollins", "São Paulo");
        
        Livro livro = new Livro(
                "O Senhor dos Anéis",
                1954,
                "978-8595084759",
                new BigDecimal("149.90"),
                TipoPublicacao.IMPRESSO,
                autor,
                editora
        );

        em.getTransaction().begin();
        em.persist(livro);
        em.getTransaction().commit();
        
        em.close();
        emf.close();
    }
}