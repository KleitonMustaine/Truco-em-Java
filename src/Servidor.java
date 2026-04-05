import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    public static void servidor(String[] args) {
        final int PORTA = 21124;

        try {
            ServerSocket servidor = new ServerSocket(PORTA);
            System.out.println("Esperando a conexÃ£o do cliente.");
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado.");

}
