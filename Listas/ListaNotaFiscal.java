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
            inicio.proximo = n;
            n.anterior = inicio;
            n.proximo = fim;
            fim.anterior = n;
        } else {
            n.proximo = fim;
            n.anterior = fim.anterior;
            fim.anterior.proximo = n;
            fim.anterior = n;
        }
        quantidade++;
    }

    public Nodo sort() {
        if (inicio.proximo == fim) {
            return inicio;
        }
    
        inicio.proximo = mergeSort(inicio.proximo);
        Nodo atual = inicio.proximo;
        while (atual.proximo != null) {
            atual = atual.proximo;
        }
        inicio.proximo.anterior = inicio;
        fim.anterior = atual;
        atual.proximo = fim;
    
        return inicio.proximo;
    }
    

    public Nodo mergeSort(Nodo dNodo) {
        if (dNodo == null || dNodo.proximo == null) {
            return dNodo;
        }

        Nodo meio = getMidNodo(dNodo);
        Nodo proximo = meio.proximo;
        meio.proximo = null;

        Nodo l1 = mergeSort(dNodo);
        Nodo l2 = mergeSort(proximo);

        return merge(l1, l2);
    }

    private Nodo merge(Nodo list1, Nodo list2) {
        Nodo suporte = new Nodo(null);
        Nodo atual = suporte;
    
        while (list1 != null && list2 != null) {
            if (list1.item == null) {
                list1 = list1.proximo;
                continue;
            }
            if (list2.item == null) {
                list2 = list2.proximo;
                continue;
            }
    
            if (list1.item.getData().compareTo(list2.item.getData()) < 0) {
                atual.proximo = list1;
                list1.anterior = atual;
                list1 = list1.proximo;
            } else {
                atual.proximo = list2;
                list2.anterior = atual;
                list2 = list2.proximo;
            }
            atual = atual.proximo;
        }
    
        atual.proximo = (list1 == null) ? list2 : list1;
        if (atual.proximo != null) {
            atual.proximo.anterior = atual;
        }
    
        return suporte.proximo;
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

    public void consultarNota(int notaFiscal) {
        Nodo suporte = inicio;
        while(suporte != null) {
            if(notaFiscal == Integer.parseInt(suporte.item.getNumero())) {
                System.out.println("Nota: " + suporte.item.getNumero() +
                 "\nData: " + suporte.item.getData() +
                 "\nCliente: " + suporte.item.getCliente() +
                 "\nCPF: " + suporte.item.getCnpjCpf() +
                 "\nEndereco: " + suporte.item.getEndereco() +
                 "\nCidade: " + suporte.item.getCidade() +
                 "\nEstado: " + suporte.item.getEstado() +
                  "\nLista: \n" + suporte.item.getItens().toString());
                  return;
            }
            suporte = suporte.proximo;
        }
        System.out.println("Numero nao encontrado!");
    }

    public NotaFiscal getPrimeiraNota() {return inicio.item;}
    public void imprimir() {System.out.println(toString());}

}
