package Listas;

import Objects.Item;

public class ListaSimplesmenteEncadeada {
    private Nodo inicio;
    private Nodo fim;
    private int quantidade;

    class Nodo {
        private Item item;
        private Nodo proximo;

        public Nodo(Item item) {
            this.item = item;
            this.proximo = null;
        }
    }

    public ListaSimplesmenteEncadeada() {
        quantidade =0;
        inicio = null;
        fim = null;
    }

    public void adicionar(Item item) {
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

    public int buscar(String chave) {
        int posicao = 0;
        for(Nodo n = this.inicio; n!=null; n = n.proximo) {
            if(n.item.equals(chave)) return posicao;
            posicao++;
        }
        return -1; //nao existe
    }
    @Override
    public String toString() {
        if(this.quantidade==0) return "[]";
        String str = "[";
        for(Nodo n = this.inicio; n!=null; n = n.proximo) {
            str = str + " " + n.item + " ";
        }
        str = str + "] n = " + this.quantidade + " (inicio = " + this.inicio.item + ") (fim = " + this.fim.item + ")";
        return str;
    }

    public boolean estaVazia() {return quantidade==0;}
}
