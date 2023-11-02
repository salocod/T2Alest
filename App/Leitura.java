package App;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;

import Listas.ListaDuplamenteEncadeada;
import Objects.Item;
import Objects.NotaFiscal;

public class Leitura {

    private BufferedReader bw;

    public Leitura() {
        try {
            bw = new BufferedReader(new FileReader("teste.csv"));
            PrintStream pw = new PrintStream(new File("dadosout.txt"));
            System.setOut(pw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();

    public void executa() {
        String linha;
        try {
            bw.readLine();
            while ((linha = bw.readLine()) != null) {
                Scanner sc = new Scanner(linha).useDelimiter("[|]");

                int numero = sc.nextInt();
                Date data = Date.valueOf(sc.next());
                String cliente = sc.next();
                String cpf = sc.next();
                String end = sc.next();
                String cidade = sc.next();
                String estado = sc.next();

                NotaFiscal notaFiscal = new NotaFiscal(numero, data, cliente, cpf, end, cidade, estado);

                lista.adicionar(notaFiscal);
                
                //Nota Fiscal ==> Numero_NF|Data_NF|Cliente|CNPJ_CPF|Endereco|Cidade|Estado
                //Item ==> Numero|Descricao|Qtd|Valor_Unitario
                
                String numeroItem = sc.next();
                String descricao = sc.next();
                int quantidade = sc.nextInt();
                double valorUnitario = sc.nextDouble();

                Item item = new Item(numeroItem, descricao, quantidade, valorUnitario);
                //notaFiscal.listaSimplesmenteEncadeada.adicionar(item);

                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        lista.imprimirLista();
    }

    
}
