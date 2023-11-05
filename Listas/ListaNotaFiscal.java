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

    public void adicionar(NotaFiscal item) {
        Nodo n = new Nodo(item);
        if (quantidade == 0) {
            // Se a lista está vazia, o novo nodo é inserido após o início.
            inicio.proximo = n;
            n.anterior = inicio;
            n.proximo = fim; // O novo nodo aponta para o fim.
            fim.anterior = n;
        } else {
            // O novo nodo é inserido antes do fim da lista.
            n.proximo = fim;
            n.anterior = fim.anterior;
            fim.anterior.proximo = n;
            fim.anterior = n;
        }
        quantidade++; // Incrementa a quantidade de nodos na lista.
    }

    public void sort() {
        if (inicio.proximo != fim) { // Check if the list has more than 0 elements.
            inicio.proximo = mergeSort(inicio.proximo, fim);
            Nodo last = inicio;
            while (last.proximo != fim) { // Re-establish the connections with sentinel nodes.
                last.proximo.anterior = last;
                last = last.proximo;
            }
            fim.anterior = last;
            last.proximo = fim;
        }
    }

    private Nodo mergeSort(Nodo start, Nodo end) {
        if (start == end || start.proximo == end) { // If the list is empty or has only one element.
            return start;
        }
        Nodo mid = getMidNodo(start, end);
        Nodo secondHalfStart = mid.proximo;
        mid.proximo = end; // Disconnect the two halves.
    
        Nodo left = mergeSort(start, mid);
        Nodo right = mergeSort(secondHalfStart, end);
    
        return merge(left, right, end);
    }
    private Nodo merge(Nodo list1, Nodo list2, Nodo end) {
        Nodo dummyHead = new Nodo(null);
        Nodo current = dummyHead;
    
        while (list1 != end && list2 != end) {
            if (list1.item.getData().compareTo(list2.item.getData()) < 0) {
                current.proximo = list1;
                list1.anterior = current;
                list1 = list1.proximo;
            } else {
                current.proximo = list2;
                list2.anterior = current;
                list2 = list2.proximo;
            }
            current = current.proximo;
        }
    
        while (list1 != end) {
            current.proximo = list1;
            list1.anterior = current;
            list1 = list1.proximo;
            current = current.proximo;
        }
    
        while (list2 != end) {
            current.proximo = list2;
            list2.anterior = current;
            list2 = list2.proximo;
            current = current.proximo;
        }
    
        current.proximo = end; // Reconnect the last part of the list.
        end.anterior = current;
    
        return dummyHead.proximo;
    }
    
    
    private Nodo getMidNodo(Nodo start, Nodo end) {
        Nodo slow = start;
        Nodo fast = start;
        while (fast != end && fast.proximo != end) {
            slow = slow.proximo;
            fast = fast.proximo.proximo;
        }
        return slow;
    }
    

    @Override
    public String toString() {
        String r = "";
        // Começa do próximo nodo após o início e vai até o nodo anterior ao fim.
        for (Nodo n = inicio.proximo; n != fim; n = n.proximo) {
            if (n.item != null) { // Verifica se o item não é nulo antes de acessar seus métodos.
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
        }
        return r + "Quantidade: " + quantidade;
    }
    

    public void imprimir() {System.out.println(toString());}

}
