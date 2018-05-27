import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphEditor extends Application {

    private Graph graph;

    public GraphEditor() {
        graph = new Graph();
    }

    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        Pane graphPane = new Pane();
        graphPane.setStyle("-fx-background-color: #f0f0f0;");
        borderPane.setCenter(graphPane);
        
        Menu menu = new Menu();
        menu.createButtons(graphPane, graph);
        borderPane.setBottom(menu.getHBox());

        Scene scene = new Scene(borderPane, 700, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
