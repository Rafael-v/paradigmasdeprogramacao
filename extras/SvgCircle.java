import java.util.Scanner;

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
        
        System.out.print("Digite altura e largura da tela: ");
        double altura = in.nextDouble();
        double largura = in.nextDouble();
        
        System.out.print("Deseja inserir quantos circulos? ");
        int totalCircs = in.nextInt();
        SvgCircle[] circs = new SvgCircle[totalCircs];
        System.out.println("Digite, nessa ordem, a posicao x, y, raio e cor de cada circulo: ");
        for (int i = 0; i < circs.length; i++) {
            System.out.print("Circulo " + (i+1) + ": ");
            circs[i] = new SvgCircle(in.nextDouble(), in.nextDouble(), in.nextDouble(), in.next());
        }
        
        criaCodigoSvg(altura, largura, circs);
    }
    
    private static void criaCodigoSvg(double altura, double largura, SvgCircle[] circs) {
        System.out.println("<svg height=\"" + altura + "\" width=\"" + largura + "\">");
        for (SvgCircle circ : circs) {
            System.out.println("  <circle cx=\"" + circ.x + "\" cy=\"" + circ.y + "\" r=\"" + circ.r + "\" stroke=\"black\" stroke-width=\"3\" fill=\"" + circ.c + "\" />");
        }
        System.out.println("</svg>");
    }
}
