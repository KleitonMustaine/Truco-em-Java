import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket(Configuracao.IP_SERVIDOR, Configuracao.PORTA);

        PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        System.out.println("Conectado ao servidor!");

        String mensagem;

        // LOOP ÚNICO: lê mensagem → decide se responde
        while ((mensagem = entrada.readLine()) != null) {
            System.out.println(mensagem);

            // PEDIR TRUCO
            if (mensagem.contains("Deseja pedir truco")) {
                String resp = scanner.nextLine();
                saida.println(resp);
            }

            // ACEITAR TRUCO
            else if (mensagem.contains("aceita o truco")) {
                String resp = scanner.nextLine();
                saida.println(resp);
            }

            // ESCOLHER CARTA
            else if (mensagem.contains("Escolha uma carta")) {
                String escolha = scanner.nextLine();
                saida.println(escolha);
            }
        }

        socket.close();
        scanner.close();
    }
}
