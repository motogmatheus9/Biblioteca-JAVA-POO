package biblioteca.interfaces;

import java.util.List;

import biblioteca.modelo.Livro;

/**
 * Interface para pesquisa de livros na biblioteca.
 */
public interface IPesquisavel {
    /**
     * Busca livros por título.
     *
     * @param titulo termo do título
     * @return lista de livros encontrados
     */
    List<Livro> buscarPorTitulo(String titulo);

    /**
     * Busca livros por autor.
     *
     * @param autor termo do autor
     * @return lista de livros encontrados
     */
    List<Livro> buscarPorAutor(String autor);
}