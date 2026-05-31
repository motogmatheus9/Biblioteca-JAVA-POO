package biblioteca.modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Representa o empréstimo de um livro para um usuário.
 */
public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;

    public Emprestimo(Livro livro, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    /**
     * Verifica se o empréstimo está atrasado.
     *
     * @return true quando passou da data prevista e ainda não foi devolvido
     */
    public boolean estaAtrasado() {
        return dataDevolucaoReal == null && LocalDate.now().isAfter(dataDevolucaoPrevista);
    }

    /**
     * Calcula dias de atraso na devolução.
     *
     * @return quantidade de dias em atraso
     */
    public int calcularDiasAtraso() {
        LocalDate referencia = (dataDevolucaoReal != null) ? dataDevolucaoReal : LocalDate.now();
        if (!referencia.isAfter(dataDevolucaoPrevista)) {
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(dataDevolucaoPrevista, referencia);
    }

    @Override
    public String toString() {
        return String.format("Livro: %s | Usuário: %s | Emp: %s | Prev: %s | Real: %s",
                livro.getTitulo(),
                usuario.getNome(),
                dataEmprestimo,
                dataDevolucaoPrevista,
                dataDevolucaoReal == null ? "-" : dataDevolucaoReal.toString());
    }
}