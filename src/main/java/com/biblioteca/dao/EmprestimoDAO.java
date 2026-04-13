package com.biblioteca.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.biblioteca.model.Emprestimo;

public class EmprestimoDAO {

    private final EntityManager em;

    public EmprestimoDAO(EntityManager em) {
        this.em = em;
    }

    public void salvar(Emprestimo emprestimo) {
        em.getTransaction().begin();
        em.persist(emprestimo);
        em.getTransaction().commit();
    }

    public Emprestimo buscar(int id) {
        return em.find(Emprestimo.class, id);
    }

    public void atualizar(Emprestimo emprestimo) {
        em.getTransaction().begin();
        em.merge(emprestimo);
        em.getTransaction().commit();
    }

    public void deletar(int id) {
        Emprestimo emprestimo = buscar(id);
        if (emprestimo != null) {
            em.getTransaction().begin();
            em.remove(emprestimo);
            em.getTransaction().commit();
        }
    }

    public List<Emprestimo> listar() {
        return em.createQuery("SELECT e FROM Emprestimo e", Emprestimo.class).getResultList();
    }

    public List<Emprestimo> buscarPorNomeAluno(String nome) {
        return em.createQuery(
                "SELECT e FROM Emprestimo e WHERE LOWER(e.aluno.nome) = LOWER(:nome)",
                Emprestimo.class)
                .setParameter("nome", nome)
                .getResultList();
    }
}
