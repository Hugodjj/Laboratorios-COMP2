import java.util.Comparator;

public class ComparadorDeJogadoresPorNome implements Comparator<Jogador> {

    @Override
    public int compare(Jogador o1, Jogador o2) {
        return o1.getNome().compareTo(o2.getNome());
    }
}
