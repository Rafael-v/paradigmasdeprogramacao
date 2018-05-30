import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Rafael Vales
 */
public class Planarity extends Application {

    private Graph graph;
    private int level;
    private double time;

    public Planarity() {
        graph = new Graph();
        level = 1;
        time = 23.0;
    }

    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();

        Interface menu = new Interface();
        menu.createButtons(graph);

        Pane graphPane = new Pane();
        graphPane.setStyle("-fx-background-color: #e6e6e6;");
        graphPane.getChildren().add(menu.getInfo(level, time, graph.getIntersections()));

        borderPane.setTop(menu.getTop("Planarity"));
        borderPane.setCenter(graphPane);
        borderPane.setBottom(menu.getBottom());

        Scene scene = new Scene(borderPane, 900, 800);
        stage.setScene(scene);
        stage.setTitle("Planarity");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
