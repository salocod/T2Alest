package Listas;

import Objects.NotaFiscal;

public class ListaDuplamenteEncadeada {
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

    public ListaDuplamenteEncadeada() {
        quantidade = 0;
        inicio = new Nodo(null);
        fim = new Nodo(null);
        inicio.anterior = null;
        inicio.proximo = fim;
        fim.proximo = null;
        fim.anterior = inicio;
    }

    public void adicionar(int posicao, NotaFiscal item) {
        Nodo aux = inicio.proximo;
        int p = 0;
        while (aux != null) {
            if (posicao == p) {
                Nodo n = new Nodo(item);
                n.proximo = aux;
                n.anterior = aux.anterior;
                aux.anterior.proximo = n;
                aux.anterior = n;
                quantidade++;
                return;
            }
            aux = aux.proximo;
            p++;
        }
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
        if (buscar(item) == -1) {
            fim.anterior.proximo = n;
            n.proximo = fim;
            n.anterior = fim.anterior;
            fim.anterior = n;
            quantidade++;
        }

    }

    public void imprimirLista() {
        Nodo atual = inicio;
        while (atual != null) {
            System.out.println(atual.item);
            atual = atual.proximo;
        }
    }

    public boolean estaVazia() {
        return (quantidade == 0);
    }
}
