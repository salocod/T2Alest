package Listas;

import Objects.ItemNotaFiscal;

public class ListaItemNotaFiscal {
    private Nodo inicio;
    private Nodo fim;
    private int quantidade;

    class Nodo {
        private ItemNotaFiscal item;
        private Nodo proximo;

        public Nodo(ItemNotaFiscal item) {
            this.item = item;
            this.proximo = null;
        }
    }

    public ListaItemNotaFiscal() {
        quantidade =0;
        inicio = null;
        fim = null;
    }

    public void adicionar(ItemNotaFiscal item) {
        Nodo novoNodo = new Nodo(item);
        if(estaVazia()) {
            inicio = novoNodo;
            fim = novoNodo;
        }
        else {
            fim.proximo = novoNodo;
            fim = novoNodo;
        }
        quantidade++;
    }

    @Override
    public String toString() {
        if(estaVazia()) return "[]";
        String str = "";
        for(Nodo n = this.inicio; n!=null; n = n.proximo) {
            str += "\n  Item: " + n.item.getItemNumero() +
            "\n  Descricao: " + n.item.getDescricao() +
            "\n  Quantidade: " + n.item.getQuantidade() +
            "\n  Valor Unitario: " + String.format("%.2f", n.item.getValorTotalItem()) + "\n";
        }
        return str + "\n  Valor total: " + String.format("%.2f", getSomatorioItens()) +
        "\n  Quantidade itens: " + getQuantidade() + "\n";
    }

    public double getSomatorioItens() {
            double soma = 0.0;
            for(Nodo n = this.inicio; n!=null; n = n.proximo) {
                soma += n.item.getValorTotalItem();
            }
            return soma;
    }

    public int getQuantidade() {return quantidade;}
    public boolean estaVazia() {return quantidade==0;}
}
