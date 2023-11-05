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

    public Nodo sort() {
        if (inicio.proximo == fim) {
            return inicio;
        }
    
        // Start from the first real node.
        inicio.proximo = mergeSort(inicio.proximo);
        // After sorting, make sure to re-establish the sentinel nodes correctly.
        // Find the new start and end of the list and connect them to the sentinel nodes.
        Nodo current = inicio.proximo;
        while (current.proximo != null) {
            current = current.proximo;
        }
        inicio.proximo.anterior = inicio;
        fim.anterior = current;
        current.proximo = fim;
    
        return inicio.proximo;
    }
    

    public Nodo mergeSort(Nodo dNodo) {
        if (dNodo == null || dNodo.proximo == null) {
            return dNodo;
        }

        Nodo mid = getMidNodo(dNodo);
        Nodo proximo = mid.proximo;
        mid.proximo = null;

        Nodo l1 = mergeSort(dNodo);
        Nodo l2 = mergeSort(proximo);

        return merge(l1, l2);
    }

    private Nodo merge(Nodo list1, Nodo list2) {
        // Dummy node to help with the merge process.
        Nodo dummy = new Nodo(null);
        Nodo current = dummy;
    
        while (list1 != null && list2 != null) {
            // Skip sentinel nodes or nodes with null items.
            if (list1.item == null) {
                list1 = list1.proximo;
                continue;
            }
            if (list2.item == null) {
                list2 = list2.proximo;
                continue;
            }
    
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
    
        // Attach the remaining part of the list, if any.
        current.proximo = (list1 == null) ? list2 : list1;
        if (current.proximo != null) {
            current.proximo.anterior = current;
        }
    
        // Return the sorted list, skipping the dummy node.
        return dummy.proximo;
    }
    

    public Nodo getMidNodo(Nodo Nodo) {
        Nodo slow = Nodo;
        Nodo fast = Nodo;
        while (fast.proximo != null && fast.proximo.proximo != null) {
            slow = slow.proximo;
            fast = fast.proximo.proximo;
        }
        return slow;
    }
    

    @Override
    public String toString() {
        String r = "";
        for (Nodo n = inicio.proximo; n != fim; n = n.proximo) {
            if (n.item != null) {
                r = r + "Nota: " + n.item.getNumero() +
                 "\nData: " + n.item.getData() +
                   "\n\n";
            }
        }
        return r + "Quantidade: " + quantidade;
    }
    

    public void imprimir() {System.out.println(toString());}

}
