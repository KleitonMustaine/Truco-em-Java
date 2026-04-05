import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main (String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket(Configuracao.IP_SERVIDOR, Configuracao.PORTA);

        PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader entrada = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        );
        System.out.println("Conectado ao servidor!");

        //recebe mensagens do servidor ao mesmo tempo que o jogador pode digitar
        Thread recebedor = new Thread(() -> {
            try{
                String mensagem;
                while ((mensagem = entrada.readLine())!=null){
                    System.out.println(mensagem);
                }
            }catch(IOException e){
                System.out.println("Conexao encerrada");
            }
        });
        recebedor.start();
        //enviar ao servidor o que o jogador digitou
        String input;
        while ((input = scanner.nextLine())!=null) {
            saida.println(input);
        }
        socket.close();
        scanner.close();
    }
}
