import java.util.ArrayList;

public class ContaInvestimento extends Conta{

    private String tipoInvestimento;
    private float taxaJuros;
    private ArrayList<ContaInvestimento> investimentos;

    public ContaInvestimento (int numeroDaConta, Correntista correntista, String tipoInvestimento, Float taxaJuros) {
        super(numeroDaConta, correntista);
        this.tipoInvestimento = tipoInvestimento;
        this.taxaJuros = taxaJuros;


          for (Conta conta : Conta.ContasDoBanco) {
              if (conta.getCorrentista().equals(correntista) && conta != this) {
                  return;
              }
          }
              throw new RuntimeException("Correntista sem conta corrente!");
        }

    public float aplicarJuros(){

        float auxiliar = this.getSaldoEmReais();
        receberDepositoEmDinheiro(this.getSaldoEmReais()*taxaJuros);
        this.sacar(auxiliar);

        return this.getSaldoEmReais();
    }
    public void resgatarTotal(Conta contadestino){

        if (this.getSaldoEmReais() < 0){
            return;
        }
        if (contadestino.getCorrentista().equals(this.getCorrentista())){
            this.efetuarTransferecia(contadestino, this.getSaldoEmReais());
        }
        else {
            throw new RuntimeException("Correntista diferente!");
        }
    }
}