import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class SvgCircle {    
    private double x, y, r;
    private String c;
    
    public SvgCircle(double _x, double _y, double _r, String _c) {
        x = _x;
        y = _y;
        r = _r;
        c = _c;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<SvgCircle> circs = new ArrayList<SvgCircle>();
        
        System.out.print("Digite altura e largura da tela: ");
        int altura = in.nextInt();
        int largura = in.nextInt();

        lerCirculos(altura, largura, circs);
        geraArquivoSvg(altura, largura, circs);
    }
    
    private static void lerCirculos(int altura, int largura, ArrayList<SvgCircle> circs) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        String colors[] = {"fuchsia","blue","yellow","aqua","lime","black","red","white"};

        System.out.println("Digite, nessa ordem, a posicao x, y, raio e cor de cada circulo: ");
        do {
            circs.add(new SvgCircle(in.nextDouble(), in.nextDouble(), in.nextDouble(), in.next()));
            System.out.print("Deseja inserir um novo circulo? (s/n) ");
        } while (in.next().charAt(0) != 'n');

        System.out.print("Inserir mais circulos randomicamente? Digite a quantidade: ");
        int totalRandom = in.nextInt();
        for (int i = 0; i < totalRandom; i++)
            circs.add(new SvgCircle(rand.nextInt(largura), rand.nextInt(altura), rand.nextInt(largura/5), colors[rand.nextInt(colors.length)]));
    }

    private static void geraArquivoSvg(int altura, int largura, ArrayList<SvgCircle> circs) {
        try {
            File file = new File("svg.txt");
            if (!file.exists())
                file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            
            pw.println("<svg height=\"" + altura + "\" width=\"" + largura + "\">");
            for (SvgCircle circ : circs)
                pw.println("  <circle cx=\"" + circ.x + "\" cy=\"" + circ.y + "\" r=\"" + circ.r + "\" stroke=\"black\" stroke-width=\"3\" fill=\"" + circ.c + "\" />");
            pw.println("</svg>");
                        
            pw.close();
            System.out.println("Arquivo gerado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
