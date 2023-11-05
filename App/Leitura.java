package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
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
            bw = new BufferedReader(new FileReader("notas_fiscais_10.csv"));
            PrintStream pw = new PrintStream(new File("saida.csv"));
            System.setOut(pw);
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
        listaNf.imprimir();
    }


    

}
