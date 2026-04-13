package com.biblioteca.test;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.biblioteca.dao.EmprestimoDAO;
import com.biblioteca.model.Aluno;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Publicacao;

public class TesteEmprestimoDAO {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
        EntityManager em = emf.createEntityManager();

        try {
            EmprestimoDAO dao = new EmprestimoDAO(em);

            Aluno aluno = new Aluno();
            aluno.setMatriculaAluno(1);
            aluno.setNome("João");

            Publicacao publicacao = new Publicacao();
            publicacao.setCodigoPub(100);
            publicacao.setTitulo("Java Básico");
            publicacao.setAno(2024);
            publicacao.setAutor("Autor X");
            publicacao.setTipo("Livro");

            em.getTransaction().begin();
            em.persist(aluno);
            em.persist(publicacao);
            em.getTransaction().commit();

            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setAluno(aluno);
            emprestimo.setPublicacao(publicacao);
            emprestimo.setDataEmprestimo(LocalDate.now());
            dao.salvar(emprestimo);

            Emprestimo encontrado = dao.buscar(emprestimo.getId());
            System.out.println("Empréstimo criado com ID: " + encontrado.getId());

            encontrado.setDataDevolucao(LocalDate.now().plusDays(7));
            dao.atualizar(encontrado);
            System.out.println("Empréstimo atualizado. Data de devolução: " + encontrado.getDataDevolucao());

            System.out.println("Consulta por ID:");
            Emprestimo consultaId = dao.buscar(encontrado.getId());
            System.out.println(formatarEmprestimo(consultaId));

            System.out.println("Consulta de todos os empréstimos:");
            List<Emprestimo> todos = dao.listar();
            todos.forEach(e -> System.out.println(formatarEmprestimo(e)));

            System.out.println("Consulta por nome do aluno:");
            List<Emprestimo> porNome = dao.buscarPorNomeAluno("João");
            porNome.forEach(e -> System.out.println(formatarEmprestimo(e)));

            dao.deletar(encontrado.getId());
            System.out.println("Empréstimo removido com sucesso.");
        } finally {
            em.close();
            emf.close();
        }
    }

    private static String formatarEmprestimo(Emprestimo e) {
        return "ID: " + e.getId()
                + " | Aluno: " + e.getAluno().getNome()
                + " | Publicação: " + e.getPublicacao().getTitulo()
                + " | Empréstimo: " + e.getDataEmprestimo()
                + " | Devolução: " + e.getDataDevolucao();
    }
}
