import java.util.ArrayList;

abstract class Bloco {
    private int x, y, z;

    public Bloco () {
        x = y = z = 1;
    }

    public abstract Bloco quebraBloco();
}

class Madeira extends Bloco {
    public Madeira() {
        System.out.println("Agora você tem uma Madeira!");
    }

    public Bloco quebraBloco() {
        System.out.println("Você quebrou uma Madeira!");
        return new Madeira();
    }
}

class Grama extends Bloco {
    public Grama() {
        System.out.println("Agora você tem uma Grama!");
    }

    public Bloco quebraBloco() {
        System.out.println("Você quebrou uma Grama!");
        return new Terra();
    }
}

class Terra extends Bloco {
    public Terra() {
        System.out.println("Agora você tem uma Terra!");
    }

    public Bloco quebraBloco() {
        System.out.println("Você quebrou uma Terra!");
        return new Terra();
    }
}

class Pedra extends Bloco {
    public Pedra() {
        System.out.println("Agora você tem uma Pedra!");
    }

    public Bloco quebraBloco() {
        System.out.println("Você quebrou uma Pedra!");
        return new Pedregulho();
    }
}

class Pedregulho extends Bloco {
    public Pedregulho() {
        System.out.println("Agora você tem uma Pedregulho!");
    }

    public Bloco quebraBloco() {
        System.out.println("Você quebrou um Pedregulho!");
        return new Pedregulho();
    }
}

class Main {
    public static void main(String[] args) {
        ArrayList<Bloco> blocos = new ArrayList<Bloco>();
        System.out.println("- Você ganhou um bloco de cada:");
        blocos.add(new Pedra());
        blocos.add(new Grama());
        blocos.add(new Terra());
        blocos.add(new Madeira());

        System.out.println("\n- Quebrando seus blocos...\n");
        for (Bloco b : blocos) {
            b.quebraBloco();
            System.out.println();
        }

    }
}
