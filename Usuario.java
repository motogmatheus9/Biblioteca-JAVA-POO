package biblioteca.modelo;

import biblioteca.padroes.AlunoMultaStrategy;
import biblioteca.padroes.MultaStrategy;

/**
 * Usuário do tipo aluno.
 */
public class Aluno extends Usuario {
    private String curso;
    private final MultaStrategy multaStrategy;

    public Aluno() {
        this("", "", "", "");
    }

    public Aluno(String nome, String matricula, String email, String curso) {
        super(nome, matricula, email);
        this.curso = curso;
        this.multaStrategy = new AlunoMultaStrategy();
    }

    @Override
    public int calcularPrazoMaximo() {
        return 14;
    }

    @Override
    public int getLimiteEmprestimos() {
        return 3;
    }

    @Override
    public MultaStrategy getMultaStrategy() {
        return multaStrategy;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + getNome() + '\'' +
                ", matricula='" + getMatricula() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }
}