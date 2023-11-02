package Listas;

import Objects.NotaFiscal;

public class ListaNotaFiscal {
    class Nodo {
        NotaFiscal item;
        Nodo anterior;
        Nodo proximo;

        public Nodo(NotaFiscal notaFiscal) {
            item = notaFiscal;
        }
    }

    private Nodo inicio;
    private Nodo fim;
    private int quantidade = 0;

    public ListaNotaFiscal() {
        quantidade = 0;
        inicio = new Nodo(null);
        fim = new Nodo(null);
        inicio.anterior = null;
        inicio.proximo = fim;

        fim.proximo = null;
        fim.anterior = inicio;
    }

    public void removerDoFim() {
        if (fim != null) {
            if (fim.anterior != null) {
                fim = fim.anterior;
                fim.proximo = null;
            } else {
                inicio = null;
                fim = null;
            }
        }
        quantidade--;
    }

    public int buscar(NotaFiscal notaFiscal) {
        Nodo aux = inicio.proximo;
        int p = 0;
        while (aux != fim) {
            if (aux.item.equals(notaFiscal)) return p;
            aux = aux.proximo;
            p++;
        }
        return -1;
    }

    public void adicionar(NotaFiscal item) {
            Nodo n = new Nodo(item);
            fim.anterior.proximo = n;
            n.proximo = fim;
            n.anterior = fim.anterior;
            fim.anterior = n;
            quantidade++;
    }
    
    @Override
    public String toString() {
        String r = "";
        for(Nodo n = inicio.proximo; n!=fim; n=n.proximo) {
            r = r + "Nota: " + n.item.getNumero() +
             "\nData: " + n.item.getData() +
             "\nCliente: " + n.item.getCliente() +
             "\nCPF: " + n.item.getCnpjCpf() +
             "\nEndereco: " + n.item.getEndereco() +
             "\nCidade: " + n.item.getCidade() +
             "\nEstado: " + n.item.getEstado() +
              "\nLista: \n" + n.item.getItens().toString() +
               "\n\n";
        }
        return r + "Quantidade: " + quantidade;
    }

    public boolean estaVazia() {
        return (quantidade == 0);
    }

    public int getQuantidade() {return quantidade;}
}
