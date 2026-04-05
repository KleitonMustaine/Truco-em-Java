import java.util.*;

public class Jogo {
    private Baralho baralho;
    private Jogador j1,j2,j3,j4;
    private int pontos1,pontos2;
    private int Rodadas = 0;
    private String manilha;
    private int rodadasTime1 = 0;
    private int rodadasTime2 = 0;
    private int valorMao = 1;
    int timeQuePediu = 1; 
    private boolean trucoJaPedido = false;
    int op;

    Scanner s = new Scanner(System.in);


    
    public void adicionarPontos1(int pontos1) {
        this.pontos1 += pontos1;
    }
    public void adicionarPontos2(int pontos2) {
        this.pontos2 += pontos2;
    }

    public int getPontos1() {
        return pontos1;
    }
    
    public int getPontos2() {
        return pontos2;
    }

    private String proximacarta(String valor){
        String[] ordem = {"4","5","6","7","Q","J","K","A","2","3"};

        for(int i = 0; i < ordem.length; i++){
            if(ordem[i].equals(valor)){
                return ordem[(i+ 1) % ordem.length];
            }
        }
        return "";
    }

    private int compararCartas(Carta c1, Carta c2){
        boolean c1Manilha = c1.getValor().equals(manilha);
        boolean c2Manilha = c2.getValor().equals(manilha);

        if(c1Manilha && c2Manilha){
            return c1.getValorNaipe() - c2.getValorNaipe();
        }

        if (c1Manilha) return 1;
        if (c2Manilha) return -1;

        return c1.getValorBase() - c2.getValorBase();
    }


    public Jogo() {
        baralho = new Baralho();
    }

    public void CriarJogador(){

        System.out.print("Nome do Jogador 1: ");
        j1 = new Jogador(s.nextLine());

        System.out.print("Nome do Jogador 2: ");
        j2 = new Jogador(s.nextLine());

        System.out.print("Nome do Jogador 3: ");
        j3 = new Jogador(s.nextLine());

        System.out.print("Nome do Jogador 4: ");
        j4 = new Jogador(s.nextLine());

    }

    public void iniciar() {

        j1.limparMao();
        j2.limparMao();
        j3.limparMao();
        j4.limparMao();

        baralho = new Baralho();
        baralho.embaralhar();

        for (int i = 0; i < 3; i++) {
            j1.receberCarta(baralho.comprar());
            j2.receberCarta(baralho.comprar());
            j3.receberCarta(baralho.comprar());
            j4.receberCarta(baralho.comprar());
        }

        Carta vira = baralho.comprar();
        System.out.println("\n=== NOVA MÃO ===");
        System.out.println("Vira: " + vira);

        manilha = proximacarta(vira.getValor());
        System.out.println("Manilha: " + manilha);

        System.out.println("\n=== TIMES ===");
        System.out.println("Time 1: " + j1.getNome() + " e " + j2.getNome());
        System.out.println("Time 2: " + j3.getNome() + " e " + j4.getNome());

        rodadasTime1 = 0;
        rodadasTime2 = 0;
        valorMao = 1;

        rodada();
    }

    private boolean pedirTruco(int timeQuePediu) {
    
    if (valorMao == 12) return false;

    System.out.println("Deseja pedir truco? (s/n)");
    String resp = s.next().trim();

    if (resp.equalsIgnoreCase("s")) {

        trucoJaPedido = true;

        if (valorMao == 1) valorMao = 3;
        else if (valorMao == 3) valorMao = 6;
        else if (valorMao == 6) valorMao = 9;
        else if (valorMao == 9) valorMao = 12;
        System.out.println("Truco valendo " + valorMao + " pontos!");

        System.out.println("Time adversário aceita? (s/n)");
        String resp2 = s.next().trim();

        if (resp2.equalsIgnoreCase("n")) {
            System.out.println("Time correu!");

            if (timeQuePediu == 1) {
                pontos1 += valorMao;
            } else {
                pontos2 += valorMao;
            }

            return true;
        }
    }

    return false;
}
    private int lerJogadaHost() {
        int escolha = s.nextInt() - 1;
        s.nextLine();
        return escolha;
    }

    private void rodada() {

        System.out.println("--NOVA RODADA--\n");

        for (int i = 0; i < 3; i++) {
            System.out.println("\n" + j1.getNome());
            j1.mostrarMao();
            if (pedirTruco(1)) return;
            System.out.print("Escolha carta: ");
            Carta c1 = j1.jogarCarta(s.nextInt()-1);

            System.out.println("\n" + j2.getNome());
            j2.mostrarMao();
            if (pedirTruco(1)) return;
            System.out.print("Escolha carta: ");
            Carta c2 = j2.jogarCarta(s.nextInt()-1);

            System.out.println("\n" + j3.getNome());
            j3.mostrarMao();
            if (pedirTruco(2)) return;
            System.out.print("Escolha carta: ");
            Carta c3 = j3.jogarCarta(s.nextInt()-1);

            System.out.println("\n" + j4.getNome());
            j4.mostrarMao();
            if (pedirTruco(2)) return;
            System.out.print("Escolha carta: ");
            Carta c4 = j4.jogarCarta(s.nextInt()-1);

            System.out.println(j1.getNome() + " jogou: " + c1);
            System.out.println(j2.getNome() + " jogou: " + c2);
            System.out.println(j3.getNome() + " jogou: " + c3);
            System.out.println(j4.getNome() + " jogou: " + c4);

            Carta[] cartas = {c1,c2,c3,c4};
            int vencedor = 0;

            for(int k = 1; k < cartas.length; k++){
                if(compararCartas(cartas[k], cartas[vencedor]) > 0){
                    vencedor = k;
                }
            }

            if (vencedor == 0 || vencedor == 1) {
                rodadasTime1++;
                System.out.println("Time 1 ganhou a rodada!");
            } else {
                rodadasTime2++;
                System.out.println("Time 2 ganhou a rodada!");
            }
            System.out.println("\n----------------------");

            if (rodadasTime1 == 2) {
            pontos1 += valorMao;
            System.out.println("Time 1 ganhou a mão!");
            break;
        }

        if (rodadasTime2 == 2) {
            pontos2 += valorMao;
            System.out.println("Time 2 ganhou a mão!");
            break;
        }
        
        }
        if (rodadasTime1 == rodadasTime2) {
            System.out.println("Empate na mão!");
        }

        System.out.println("Placar -> Time 1: " + pontos1 + " | Time 2: " + pontos2);
    }

    public void AddRodadas(){
    this.Rodadas++;
    

    }

    public boolean EndGame(){
        return pontos1 >= 12 || pontos2 >= 12;
    }
}