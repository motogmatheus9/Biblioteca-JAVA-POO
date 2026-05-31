package biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;

import biblioteca.padroes.MultaStrategy;

/**
 * Classe base de usuários da biblioteca.
 */
public abstract class Usuario {
    private String nome;
    private String matricula;
    private String email;
    private List<Emprestimo> emprestimosAtivos;

    public Usuario() {
        this("", "", "");
    }

    public Usuario(String nome, String matricula, String email) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.emprestimosAtivos = new ArrayList<>();
    }

    public abstract int calcularPrazoMaximo();

    public abstract int getLimiteEmprestimos();

    public abstract MultaStrategy getMultaStrategy();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        return emprestimosAtivos;
    }

    public void setEmprestimosAtivos(List<Emprestimo> emprestimosAtivos) {
        this.emprestimosAtivos = emprestimosAtivos;
    }

    /**
     * Verifica se o usuário possui atraso em algum empréstimo ativo.
     *
     * @return true se existir atraso
     */
    public boolean possuiAtraso() {
        for (Emprestimo emprestimo : emprestimosAtivos) {
            if (emprestimo.estaAtrasado()) {
                return true;
            }
        }
        return false;
    }
}