import java.util.ArrayList;

public class Correntista extends PessoaFisica {

    private static final int LIMITE_DEFAULT = 100;
    private Conta conta;
    private float limiteChequeEspecial;
    private ArrayList<ContaInvestimento> investimentos;

    public Correntista(String nome, long cpf) {
        super(nome, cpf);
        this.conta = null;
        this.limiteChequeEspecial = LIMITE_DEFAULT;
        this.investimentos= new ArrayList<>();
    }

    public float getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(float limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public Conta getConta() {
        return this.conta;
    }

    public float getTotalInvestido() {
        return 0;  // ToDo IMPLEMENT ME!!
    }
}