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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public class Interface {
    private String btnStyle, menuStyle, titleStyle;
    private ArrayList<Button> btn;

    public Interface() {
        btnStyle = "-fx-font: 16px Georgia; -fx-padding: 7 20px 7px 20px; -fx-border-radius: 20;";
        menuStyle = "-fx-background-color: linear-gradient(#3c3c3c, #000000); -fx-padding: 10.0;";
        titleStyle = "-fx-background-color: linear-gradient(#999999, #e6e6e6); -fx-padding: 10.0;";
        btn = new ArrayList<Button>();
    }

    public HBox getTop(String name) {
        Label label = new Label(name);
        label.setStyle("-fx-font: 24px \"Georgia\"; -fx-text-fill: black");

        HBox title = new HBox(label);
        title.setAlignment(Pos.CENTER);
        title.setStyle(titleStyle);
        return title;
    }

    public HBox getBottom() {
        HBox menu = new HBox();
        menu.setSpacing(10);
        menu.setAlignment(Pos.CENTER);
        menu.setStyle(menuStyle);
        for (Button b : btn)
            menu.getChildren().add(b);
        return menu;
    }

    public Label getInfo(int level, double time, int intersections) {
        Label label = new Label("Nivel " + level + '\n' + "Tempo " + getTimeString(time) + '\n' + intersections + " interseccoes restantes");
        label.setStyle("-fx-font: 20px \"Verdana\"; -fx-text-fill: #666666;");
        label.setTranslateX(10);
        label.setTranslateY(620);
        return label;
    }

    private String getTimeString(double time) {
        return "45s";
    }

    public void createButtons(Graph graph) {
        btn.add(buttonShuffle(graph));
        btn.add(buttonLevels());
        btn.add(buttonLeave());
    }

    private Button buttonShuffle(Graph graph) {
        Button b = new Button(" Misturar ");
        b.setStyle("-fx-font: 16px Georgia; -fx-padding: 7 20px 7px 20px;");
        b.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                graph.shuffle();
            }
        });
        return b;
    }

    private Button buttonLevels() {
        Button b = new Button("    Niveis    ");
        b.setStyle("-fx-font: 20px Georgia; -fx-padding: 7 20px 7px 20px;");
        return b;
    }

    private Button buttonLeave() {
        Button b = new Button("   Sair   ");
        b.setStyle("-fx-font: 16px Georgia; -fx-padding: 7 20px 7px 20px;");
        b.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Sair");
                alert.setHeaderText("Sair de Planarity");
                alert.setContentText("Deseja sair?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    System.exit(0);
                }
            }
        });
        return b;
    }
}
