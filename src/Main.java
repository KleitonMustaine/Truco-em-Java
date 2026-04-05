import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        Jogo jogo = new Jogo();

        int op = 0;

        System.out.println("MENU");
        System.out.println("\n1 - Jogar\n");
        System.out.println("2 - Entrar\n");
        System.out.println("3 - Host");
        op = s.nextInt();

        switch(op){
            case 1:
                jogo.CriarJogador();
                while(!jogo.EndGame()){
                jogo.iniciar();
                }
            case 2:
                System.out.println("Calma pae tamo fazendo ainda");  
                break;
            case 3:
                System.out.println("Calma pae tamo fazendo ainda");  
                break;
            default:
                System.out.println("PARA DE SER ESTRANHO");
                    

        }

        

    }
}
