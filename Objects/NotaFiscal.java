package Objects;
import java.util.Date;

import Listas.ListaSimplesmenteEncadeada;

public class NotaFiscal {
    
    private int numero;
    private Date data;
    private String cliente;
    private String cpf;
    private String endereco;
    private String cidade;
    private String estado;
    public ListaSimplesmenteEncadeada listaSimplesmenteEncadeada;
    
    public NotaFiscal(int numero, Date data, String cliente, String cpf, String endereco, String cidade, String estado) {
        this.numero = numero;
        this.data = data;
        this.cliente = cliente;
        this.cpf = cpf;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        listaSimplesmenteEncadeada = new ListaSimplesmenteEncadeada();
    }

    @Override
    public String toString() {
        return String.format("Numero: %d\nData: %s", numero, data.toString());
    }

    public int getNumero() {return numero;}
    public Date getData() {return data;}
    public String getCliente() {return cliente;}
    public String getCpf() {return cpf;}
    public String getEndereco() {return endereco;}
    public String getCidade() {return cidade;}
    public String getEstado() {return estado;}
}
