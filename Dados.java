public class Dado {
    private String data;       // Data e hora da coleta do dado
    private String ordem;      // Identificação alfanumérica do veículo
    private double linha;      // Linha de transporte
    private double latitude;   // Latitude do ônibus na coleta (GPS)
    private double longitude;  // Longitude do ônibus na coleta (GPS)
    private double velocidade; // Velocidade do ônibus na hora do coleta do dado

    public Dado(String dat, String ord, double lin, double lat, double lon, double vel) {
        this.data = dat;
        this.ordem = ord;
        this.linha = lin;
        this.latitude = lat;
        this.longitude = lon;
        this.velocidade = vel;

        System.out.println("Dado adicionado: "+'['+dat+','+ord+','+lin+','+lat+','+lon+','+vel+']');
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
    
    public double getLinha() {
        return this.linha;
    }

    public void setLinha(double linha) {
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
