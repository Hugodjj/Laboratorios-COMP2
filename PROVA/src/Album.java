import java.util.HashMap;
import java.util.Map;

/**
 * @Nome: HUGO NASCIMENTO DA SILVA
 * @DRE: 120152161
 */

/**
 * Esta classe implementa um álbum (de figurinhas, selos, etc.) online, permitindo
 * colecionar itens que possuem uma posição específica no álbum.
 *
 * Itens são acrescentados ao álbum por meio de "pacotinhos" contendo uma quantidade
 * fixa, pré-feterminada de itens.
 *
 * Itens já existentes no álbum e recebidos novamente em pacotinhos posteriores são
 * armazenados para eventuais trocas com outro usuários.
 *
 * @param <T> o tipo de objeto colecionável que o álbum irá armazenar
 */
public class Album<T extends Colecionavel> {

    private final int tamanhoDoAlbum;
    private final int quantidadesporpacotinho;

    private Map<Integer,T> quantidadecoladaByfigurinha;
    private Map<Integer,T> quantidadearmazenadaByfigurinha;

    /**
     * Construtor.
     * @param tamanhoDoAlbum O tamanho do álbum sendo criado (note que os itens que serão colecionados
     *                       terão posições entre 1 e o tamanho do álbum)
     * @param quantItensPorPacotinho A quantidade de itens em cada pacotinho adquirido para este álbum
     */
    public Album(int tamanhoDoAlbum, int quantItensPorPacotinho){

        this.tamanhoDoAlbum = tamanhoDoAlbum;
        this.quantidadesporpacotinho = quantItensPorPacotinho;

        this.quantidadearmazenadaByfigurinha = new HashMap<>();
        this.quantidadecoladaByfigurinha = new HashMap<>();

    }

    /**
     * Recebe novos itens que serão imediatamente "colados" ao álbum, se inéditos,
     * ou guardados para troca, se repetidos.
     *
     * @param pacotinho Um pacotinho de itens, que poderão ser inéditos ou repetidos
     *
     * @throws PacotinhoInvalidoException se o pacotinho contiver uma quantidade errada de itens,
     *                                    ou se contiver algum item que não pertença ao álbum
     *                                    (por indicar uma posição menor que 1 ou maior que seu tamanho)
     */
    public void receberNovoPacotinho(Pacotinho<T> pacotinho) throws PacotinhoInvalidoException {

        if (quantidadesporpacotinho > pacotinho.size()){
            throw new PacotinhoInvalidoException();
        }

        for (T colecionavel : pacotinho) {
            if (colecionavel.getPosicao() > tamanhoDoAlbum) {
                throw new PacotinhoInvalidoException();
            }
        }

        for (T colecionavel : pacotinho) {
            if (quantidadecoladaByfigurinha.containsKey(colecionavel.getPosicao())) {
                quantidadearmazenadaByfigurinha.put(colecionavel.getPosicao(), colecionavel);
            }
            quantidadecoladaByfigurinha.put(colecionavel.getPosicao(), colecionavel);
        }
    }

    /**
     * @return a quantidade total de figurinhas que este álbum apresenta quando se encontra completo
     */
    public int getTamanho() {
        return this.tamanhoDoAlbum;
    }

    /**
     * @return a quantidade total de itens que estão "colados" no álbum,
     * isto é, o total de itens distintos que foram recebidos até o momento
     */
    public int getQuantItensColados() {
        return this.quantidadecoladaByfigurinha.size();
    }

    /**
     * @return o total de itens repetidos que foram acumulados até o momento
     */
    public int getQuantItensRepetidos() {
        return quantidadearmazenadaByfigurinha.size();
    }

    public boolean possuiItemColado(int posicao) {
        return quantidadecoladaByfigurinha.containsKey(posicao);
    }

    public boolean possuiItemRepetido(int posicao) {
        return quantidadearmazenadaByfigurinha.containsKey(posicao);
    }

    /**
     * @return a quantidade de itens que faltam para o álbum ficar completo
     */
    public int getQuantItensFaltantes() {

        return tamanhoDoAlbum - quantidadecoladaByfigurinha.size();
    }

    /**
     * @param posicao a posição do iten desejado
     * @return o item que está colado na posição especificada, se houver; null, caso contrário
     */
    public T getItemColado(int posicao) {

        if (quantidadecoladaByfigurinha.containsKey(posicao)){
            return quantidadecoladaByfigurinha.get(posicao);
        }
        return null;
    }
}
