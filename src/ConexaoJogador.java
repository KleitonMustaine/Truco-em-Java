import java.net.*;
import java.io.*;

public class ConexaoJogador{
    private Socket socket;
    private PrintWriter saida; //servidor manda mensagem pro cliente
    private BufferedReader entrada; //servidor recebe mensagem do cliente
    private Jogador jogador;
    
    public ConexaoJogador(Socket socket, Jogador jogador) throws IOException{
        this.socket = socket;
        this.jogador = jogador;
        //comfiguração de canais de comunicação
        this.saida = new PrintWriter(socket.getOutputStream(),true);
        this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
//envia uma mensagem para o jogador
    public void enviar(String mensagem){
        saida.println(mensagem);
    }
    //recebe uma mensagem do jogador
    public String recebe() throws IOException{
        return entrada.readLine();
    }
    public void enviarMao(){
        enviar("\n === SUA MAO ===");
        for(int i = 0; i< jogador.getMao().size();i++){
            enviar("["+i+"]"+jogador.getMao().get(i));
        }
    }
    public Jogador getJogador(){
        return jogador;
    }
    public void fechar() throws IOException {
        socket.close();
    }
}