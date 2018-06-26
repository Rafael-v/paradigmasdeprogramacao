import java.util.List;
import javafx.beans.property.SimpleStringProperty;

public class Dado {
    private final SimpleStringProperty data;       // Data e hora da coleta do dado
    private final SimpleStringProperty ordem;      // Identificação alfanumérica do veículo
    private final SimpleStringProperty linha;      // Linha de transporte
    private final SimpleStringProperty latitude;   // Latitude do ônibus na coleta (GPS)
    private final SimpleStringProperty longitude;  // Longitude do ônibus na coleta (GPS)
    private final SimpleStringProperty velocidade; // Velocidade do ônibus na hora do coleta do dado

    private static final int DATAHORA = 0;
    private static final int ORDEM = 1;
    private static final int LINHA = 2;
    private static final int LATITUDE = 3;
    private static final int LONGITUDE = 4;
    private static final int VELOCIDADE = 5;

    public Dado(List dado) {
        this.data = new SimpleStringProperty((String)dado.get(DATAHORA));
        this.ordem = new SimpleStringProperty((String)dado.get(ORDEM));
        this.linha = new SimpleStringProperty(String.valueOf(dado.get(LINHA)));
        this.latitude = new SimpleStringProperty(String.valueOf(dado.get(LATITUDE)));
        this.longitude = new SimpleStringProperty(String.valueOf(dado.get(LONGITUDE)));
        this.velocidade = new SimpleStringProperty(String.valueOf(dado.get(VELOCIDADE)));
    }

    // DataHora
    public SimpleStringProperty dataProperty() {
        return data;
    }
    public String getData() {
        return data.get();
    }
    public void setData(String data) {
        this.data.set(data);
    }

    // Ordem
    public SimpleStringProperty ordemProperty() {
        return ordem;
    }
    public String getOrdem() {
        return ordem.get();
    }
    public void setOrdem(String ordem) {
        this.ordem.set(ordem);
    }

    // Linha
    public SimpleStringProperty linhaProperty() {
        return linha;
    }
    public String getLinha() {
        return linha.get();
    }
    public void setLinha(String linha) {
        this.linha.set(linha);
    }

    // Latitude
    public SimpleStringProperty latitudeProperty() {
        return latitude;
    }
    public double getLatitude() {
        return Double.parseDouble(latitude.get());
    }
    public void setLatitude(String latitude) {
        this.latitude.set(latitude);
    }

    // Longitude
    public SimpleStringProperty longitudeProperty() {
        return longitude;
    }
    public double getLongitude() {
        return Double.parseDouble(longitude.get());
    }
    public void setLongitude(String longitude) {
        this.longitude.set(longitude);
    }

    // Velocidade
    public SimpleStringProperty velocidadeProperty() {
        return velocidade;
    }
    public double getVelocidade() {
        return Double.parseDouble(velocidade.get());
    }
    public void setVelocidade(String velocidade) {
        this.velocidade.set(velocidade);
    }
}
