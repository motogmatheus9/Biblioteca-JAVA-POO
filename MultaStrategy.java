package biblioteca.modelo;

import biblioteca.padroes.MultaStrategy;
import biblioteca.padroes.ProfessorMultaStrategy;

/**
 * Usuário do tipo professor.
 */
public class Professor extends Usuario {
    private String departamento;
    private final MultaStrategy multaStrategy;

    public Professor() {
        this("", "", "", "");
    }

    public Professor(String nome, String matricula, String email, String departamento) {
        super(nome, matricula, email);
        this.departamento = departamento;
        this.multaStrategy = new ProfessorMultaStrategy();
    }

    @Override
    public int calcularPrazoMaximo() {
        return 30;
    }

    @Override
    public int getLimiteEmprestimos() {
        return 5;
    }

    @Override
    public MultaStrategy getMultaStrategy() {
        return multaStrategy;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "nome='" + getNome() + '\'' +
                ", matricula='" + getMatricula() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}