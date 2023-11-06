package App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.util.Scanner;

import Listas.ListaItemNotaFiscal;
import Listas.ListaNotaFiscal;
import Objects.ItemNotaFiscal;
import Objects.NotaFiscal;

public class Leitura {

    private BufferedReader bw;

    public Leitura() {
        try {
            bw = new BufferedReader(new FileReader("notas_fiscais_1.csv"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executa() {

        String linha;
        ListaNotaFiscal listaNf = new ListaNotaFiscal();
        try {
            bw.readLine();
            Scanner sc = new Scanner(bw.readLine()).useDelimiter("[|]");

            String numero = sc.next();
            Date data = Date.valueOf(sc.next());
            String cliente = sc.next();
            String cpf = sc.next();
            String end = sc.next();
            String cidade = sc.next();
            String estado = sc.next();

            NotaFiscal nf = new NotaFiscal();
            nf.setNumero(numero);
            nf.setData(data);
            nf.setCliente(cliente);
            nf.setCnpjCpf(cpf);
            nf.setEndereco(end);
            nf.setCidade(cidade);
            nf.setEstado(estado);
            listaNf.adicionar(nf);

            String notaAtual = numero;
            String notaAnterior = notaAtual;
            
            ListaItemNotaFiscal lista = new ListaItemNotaFiscal();
            nf.setItens(lista);

            String numeroItem = sc.next();
            String descricao = sc.next();
            int quantidadeItem = sc.nextInt();
            double valorUnitario = sc.nextDouble();
            ItemNotaFiscal item = new ItemNotaFiscal(numeroItem, descricao, quantidadeItem, valorUnitario);
            lista.adicionar(item);

            sc.close();
            while ((linha = bw.readLine()) != null) {
                sc = new Scanner(linha).useDelimiter("[|]");
                numero = sc.next();
                data = Date.valueOf(sc.next());
                cliente = sc.next();
                cpf = sc.next();
                end = sc.next();
                cidade = sc.next();
                estado = sc.next();
                notaAtual = numero;
                if (!notaAnterior.equals(notaAtual)) {
                    nf = new NotaFiscal();
                    nf.setNumero(numero);
                    nf.setData(data);
                    nf.setCliente(cliente);
                    nf.setCnpjCpf(cpf);
                    nf.setEndereco(end);
                    nf.setCidade(cidade);
                    nf.setEstado(estado);
                    lista = new ListaItemNotaFiscal();
                    nf.setItens(lista);
                    listaNf.adicionar(nf);
                    notaAnterior = notaAtual;
                }
                numeroItem = sc.next();
                descricao = sc.next();
                quantidadeItem = sc.nextInt();
                valorUnitario = sc.nextDouble();
                item = new ItemNotaFiscal(numeroItem, descricao, quantidadeItem, valorUnitario);
                lista.adicionar(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        listaNf.sort();
        Scanner sc = new Scanner(System.in);
        menu();
        int opcao = sc.nextInt();
        while(true) {
            switch(opcao) {
                case 0 -> {sc.close();break;}
                case 1 -> {
                    System.out.println("Insira o numero desejado");
                    listaNf.consultarNota(sc.nextInt());
                }
                case 2 -> {
                    System.out.println("\nNota mais cara: " + listaNf.getNotaMaisCara().getNumero());
                    System.out.println("Preco: " + String.format("%.2f", listaNf.getNotaMaisCara().getItens().getSomatorioItens()) + "\n");
                }
                case 3 -> {
                    System.out.println("\nNota mais barata: " + listaNf.getNotaMaisBarata().getNumero());
                    System.out.println("Preco: " + String.format("%.2f", listaNf.getNotaMaisBarata().getItens().getSomatorioItens()) + "\n");
                }
                case 4 -> {
                    System.out.println("\nNota com mais itens: " + listaNf.getNotaMaisItens().getNumero());
                    System.out.println("Quantidade: " + listaNf.getNotaMaisItens().getItens().getQuantidade() + "\n");
                }
                case 5 -> {System.out.println(listaNf.toString());}
            }
            menu();
            opcao = sc.nextInt();
        }

    }

    private void menu() {
        System.out.println("Selecione a opcao desejada: ");
        System.out.println("[1] Consultar dados Nota Fiscal");
        System.out.println("[2] Exibir Nota Fiscal mais cara");
        System.out.println("[3] Exibir Nota Fiscal mais barata");
        System.out.println("[4] Exibir Nota Fiscal com mais itens");
        System.out.println("[5] Exibir todas Notas Fiscais");
        System.out.println("[0] Sair");
    }
    

}
