package Objects;

import java.util.Date;
import Listas.ListaItemNotaFiscal;

public class NotaFiscal {
    private String numero;
    private Date data;
    private String cliente;
    private String cnpjCpf;
    private String endereco;
    private String cidade;
    private String estado;
    private ListaItemNotaFiscal itens;
    public NotaFiscal proximo;
    public NotaFiscal anterior;

    public NotaFiscal() {}

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ListaItemNotaFiscal getItens() {
        return itens;
    }

    public void setItens(ListaItemNotaFiscal itens) {
        this.itens = itens;
    }
}
