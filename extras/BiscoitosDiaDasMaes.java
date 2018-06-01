import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/* Trabalho 6 de Paradigmas de Programacao 2017/1
Descricao completa em https://github.com/AndreaInfUFSM/elc117-2017a/tree/master/trabalhos/t6 */

/* $ javac BiscoitosDiaDasMaes.java
   $ java -cp . BiscoitosDiaDasMaes */

/* Classificacao dos biscoitos:
Tamanho     Preco            Faixa
GRANDE:     RS 0.30/und.     6 < area
MEDIO:      RS 0.20/und.     3 < area <= 6
PEQUENO:    RS 0.10/und.         area <= 3 */

abstract class Biscoito {
    protected double area;

    public double getArea() {
        return area;
    }
}

class BiscoitoCircular extends Biscoito {
    private double raio;

    public BiscoitoCircular(double r) {
        raio = r;
        area = 3.14159*raio*raio;
    }
}

class BiscoitoRetangular extends Biscoito {
    private double largura;
    private double altura;

    public BiscoitoRetangular(double l, double h) {
        largura = l;
        altura = h;
        area = altura*largura;
    }
}

class BiscoitoTriangular extends Biscoito {
    private double base;
    private double altura;

    public BiscoitoTriangular(double b, double h) {
        base = b;
        altura = h;
        area = (base*altura)/2;
    }
}

class BiscoitosDiaDasMaes {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Biscoito> biscoitos;

        System.out.print("Quantidade total de biscoitos: ");
        int n = in.nextInt();

        biscoitos = geraBiscoitos(n);
        resultados(biscoitos);
    }

    private static void resultados(ArrayList<Biscoito> biscoitos) {
        ArrayList<Double> tamanhosBiscoitos = new ArrayList<Double>();
        int tamG = 0, tamM = 0, tamP = 0;

        for (Biscoito b : biscoitos)
            tamanhosBiscoitos.add(b.getArea());
        Collections.sort(tamanhosBiscoitos);
        Collections.reverse(tamanhosBiscoitos);
        
        System.out.println("Tamanho de todos os biscoitos produzidos:");
        for (Double d : tamanhosBiscoitos) {
            System.out.printf("%.2fcm2   ", d);

            if (d <= 3.0) tamP++;
            else if (d <= 6.0) tamM++;
            else tamG++;
        }
        System.out.print("\n\n");

        System.out.print("Quantidade: ");
        System.out.print(tamP + " biscoitos pequenos, ");
        System.out.print(tamM + " medios e ");
        System.out.println(tamG + " grandes.");
        System.out.print('\n');

        System.out.println("Expectativa de arrecadacao:");
        System.out.printf("%.2f reais com biscoitos pequenos\n", tamP*0.1);
        System.out.printf("%.2f reais com biscoitos medios\n", tamM*0.2);
        System.out.printf("%.2f reais com biscoitos grandes\n", tamG*0.3);
        System.out.printf("Total: %.2f reais", (tamP*0.1)+(tamM*0.2)+(tamG*0.3));
    }

    private static ArrayList<Biscoito> geraBiscoitos(int n) {
        Random rand = new Random();
        ArrayList<Biscoito> biscoitos = new ArrayList<Biscoito>();
        int larg, alt;

        for (int i = 0; i < n; i++) {
            switch (rand.nextInt(3)) {
                // Gera biscoitos com formato e area aleatoria
                // Calcula o intervalo para cada formato de biscoito, para
                // que a area de cada um se mantenha entre 0.1 e 9 cm.
                case 0:
                    biscoitos.add(new BiscoitoCircular(geraTamanho(0.1, 1.7)));
                    break;
                case 1:
                    biscoitos.add(new BiscoitoRetangular(geraTamanho(0.1, 3.0), geraTamanho(0.1, 3.0)));
                    break;
                case 2:
                    biscoitos.add(new BiscoitoTriangular(geraTamanho(0.1, 4.25), geraTamanho(0.1, 4.25)));
            }
        }

        return biscoitos;
    }

    private static double geraTamanho(double min, double max) {
        Random rand = new Random();
        return (rand.nextDouble()*(max-min) + min);
    }
}
