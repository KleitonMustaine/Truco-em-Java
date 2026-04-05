import java.util.*;

public class Jogador {
    private String nome;
    private List<Carta> mao = new ArrayList<>();

    public Jogador(String nome) {
        this.nome = nome;
    }

    public void receberCarta(Carta carta) {
        mao.add(carta);
    }

    public Carta jogarCarta(int index) {
        return mao.remove(index);
    }

    public void mostrarMao() {
        for (int i = 0; i < mao.size(); i++) {
            System.out.println(1+i + " - " + mao.get(i));
        }
    }
    
    public void limparMao(){
        mao.clear();
    }

    public String getNome() {
        return nome;
    }
}