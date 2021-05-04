import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class NavigatePages {

    void changeToPage(ActionEvent event, String filename) throws IOException {
        Stage newStage = new Stage();
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader();
        String fxmlDocPath = filename;
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
        Pane root = (Pane) loader.load(fxmlStream);

        Scene scene = new Scene(root);
        newStage.setScene(scene);

        newStage.show();
    }
}
