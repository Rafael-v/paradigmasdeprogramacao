import java.util.List;

public class Dado {
    private String data;       // Data e hora da coleta do dado
    private String ordem;      // Identificação alfanumérica do veículo
    private int linha;         // Linha de transporte
    private double latitude;   // Latitude do ônibus na coleta (GPS)
    private double longitude;  // Longitude do ônibus na coleta (GPS)
    private double velocidade; // Velocidade do ônibus na hora do coleta do dado

    public Dado(List d) {
        this.data = (String)d.get(0);
        this.ordem = (String)d.get(1);
        this.linha = 0; //d.get(2);
        this.latitude = (double)d.get(3);
        this.longitude = (double)d.get(4);
        this.velocidade = (double)d.get(5);

        System.out.println("Novo dado: " + toString());
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public String getOrdem() {
        return this.ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }
    
    public int getLinha() {
        return this.linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }
    
    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getVelocidade() {
        return this.velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }
}
