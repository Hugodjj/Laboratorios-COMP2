package controle;

import excecao.DevolucaoInvalidaException;
import excecao.LimiteEmprestimosExcedidoException;
import excecao.UsuarioNaoCadastradoException;
import modelo.Livro;
import modelo.Usuario;

import java.util.HashMap;
import java.util.Map;

public class Biblioteca {

    private Map<Long, Usuario> mapausuariobyCPF;
    private Map<Livro, Integer> mapalivrobyQuantidade;
    private Map<Usuario, Integer> livrosemprestadosByUsuario;


    /**
     * quantidade mínima de unidades de um livro que precisam existir nas estantes da biblioteca para
     * que o livro possa ser emprestado
     */
    public static final int MIN_COPIAS_PARA_PODER_EMPRESTAR = 2;

    /**
     * quantidade máxima de livros da biblioteca que podem estar emprestados, a qualquer tempo, a um mesmo usuário
     */
    public static final int MAX_LIVROS_DEVIDOS = 3;

    public Biblioteca() {
        mapausuariobyCPF = new HashMap<>();
        mapalivrobyQuantidade = new HashMap<>();
        livrosemprestadosByUsuario = new HashMap<>();
    }

    /**
     * Cadastra um usuário. Caso o usuário já exista, atualiza seu nome e seu endereço.
     *
     * @param usuario o usuário a ser inserido/atualizado.
     */
    public void cadastrarUsuario(Usuario usuario) {
        if (mapausuariobyCPF.containsKey(usuario.getCpf())) {
            mapausuariobyCPF.remove(usuario.getCpf());
            mapausuariobyCPF.put(usuario.getCpf(), usuario);
        }
        mapausuariobyCPF.put(usuario.getCpf(), usuario);
        livrosemprestadosByUsuario.put(usuario, 0);
    }

    /**
     * Retorna um usuário previamente cadastrado, a partir do CPF informado.
     *
     * @param cpf o CPF do usuário desejado
     * @return o usuário, caso exista; ou null, caso não exista nenhum usuário cadastrado com aquele CPF
     */
    public Usuario getUsuario(long cpf) {
        return mapausuariobyCPF.get(cpf);
    }

    /**
     * @return o número total de usuários cadastrados na biblioteca
     */
    public int getTotalDeUsuariosCadastrados() {
        return mapausuariobyCPF.size();
    }

    /**
     * Adquire `quantidade` cópias do livro informado e as inclui no acervo da biblioteca.
     *
     * @param livro      o livro sendo adquirido
     * @param quantidade o número de cópias do livro sendo adquiridas
     */
    public void incluirLivroNoAcervo(Livro livro, int quantidade) {

        if (!mapalivrobyQuantidade.containsKey(livro)) {
            mapalivrobyQuantidade.put(livro, quantidade);
        } else {
            mapalivrobyQuantidade.replace(livro, mapalivrobyQuantidade.get(livro));
        }
    }

    /**
     * Empresta um livro para um usuário da biblioteca.
     *
     * @param livro   o livro a ser emprestado
     * @param usuario o usuário que está pegando emprestado o livro
     * @return true, se o empréstimo foi bem-sucedido;
     * false, se o livro não está disponível para empréstimo
     * (IMPORTANTE: um livro é considerado disponível para empréstimo se há pelo menos
     * controle.Biblioteca.MIN_COPIAS_PARA_PODER_EMPRESTAR cópias daquele livro nas estantes da biblioteca;
     * isto é, as estantes da biblioteca jamais poderão ficar com menos do que
     * controle.Biblioteca.MIN_COPIAS_PARA_PODER_EMPRESTAR-1 cópias de qualquer livro de seu acervo)
     * @throws UsuarioNaoCadastradoException      se o usuário informado não está cadastrado na biblioteca
     * @throws LimiteEmprestimosExcedidoException se o usuário já está com muitos livros emprestados no momento
     */
    public boolean emprestarLivro(Livro livro, Usuario usuario) throws UsuarioNaoCadastradoException, LimiteEmprestimosExcedidoException {
        if (!mapausuariobyCPF.containsKey(usuario.getCpf())) {
            throw new UsuarioNaoCadastradoException();

        } else if (!mapalivrobyQuantidade.containsKey(livro)) {
            return false;
        } else if (livrosemprestadosByUsuario.get(usuario) >= MAX_LIVROS_DEVIDOS) {
            throw new LimiteEmprestimosExcedidoException();

        } else if (mapalivrobyQuantidade.get(livro) >= MIN_COPIAS_PARA_PODER_EMPRESTAR) {
            mapalivrobyQuantidade.replace(livro, mapalivrobyQuantidade.get(livro) - 1);
            livrosemprestadosByUsuario.replace(usuario, livrosemprestadosByUsuario.get(usuario) + 1);
            usuario.emprestarLivro(livro);
            return true;
        }
        return false;
    }

    /**
     * Recebe de volta um livro que havia sido emprestado.
     *
     * @param livro   o livro sendo devolvido
     * @param usuario o usuário que havia tomado emprestado aquele livro
     * @throws DevolucaoInvalidaException se o livro informado não se encontra emprestado para o usuário informado
     */
    public void receberDevolucaoLivro(Livro livro, Usuario usuario) throws DevolucaoInvalidaException {

        if (!usuario.possuiObjeto(livro)) {
            throw new DevolucaoInvalidaException();

        }

        if (usuario.possuiObjeto(livro)) {
            usuario.devolverLivro(livro);
            livrosemprestadosByUsuario.replace(usuario, livrosemprestadosByUsuario.get(usuario) - 1);
            mapalivrobyQuantidade.replace(livro, mapalivrobyQuantidade.get(livro) + 1);
        }
    }

    /**
     * Retorna a quantidade de livros emprestados ao usuário informado.
     *
     * @param usuario o usuário desejado
     * @return a quantidade de livros emprestados àquele usuário; caso o usuário não esteja devendo nenhum livro,
     * ou não seja nem mesmo um usuário cadastrado na biblioteca, retorna zero, não deve nada
     */
    public int getQuantidadeDeLivrosDevidos(Usuario usuario) {

        if (livrosemprestadosByUsuario.containsKey(usuario) && livrosemprestadosByUsuario.get(usuario) > 0) {
            return livrosemprestadosByUsuario.get(usuario);
        }
        return 0;
    }

    /**
     * @return a quantidade total de livros nas estantes da biblioteca (isto é, a soma das quantidades de cópias
     * não-emprestadas de todos os livros do acervo da biblioteca).
     */
    public int getQuantidadeDeLivrosNaEstante() {

        int contador = 0;

        for (Livro livro : mapalivrobyQuantidade.keySet()) {
            contador += mapalivrobyQuantidade.get(livro);
        }

        return contador;
    }

    /**
     * Retorna o número de cópias do livro informado que existem na estante da biblioteca
     * (isto é, que não estão emprestados).
     *
     * @param livro o livro desejado
     * @return o número de cópias não-emprestadas daquele livro
     */
    public int getQuantidadeDeLivrosNaEstante(Livro livro) {

        if (mapalivrobyQuantidade.containsKey(livro)) {
            return mapalivrobyQuantidade.get(livro);
        }
        return 0;
    }
}
