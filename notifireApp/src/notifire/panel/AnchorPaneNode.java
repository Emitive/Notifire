package notifire.panel;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import notifire.ThisUser;

/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;
    private AnchorPaneNode ap;
    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        this.setMinSize(100, 82);
        this.setMaxSize(100, 82);
        this.setColor("#1c1c1c","#ff3419");
        this.setOnMouseClicked(e ->{ ThisUser.setDate(this.date);});
        this.setOnMouseEntered(e -> this.highlight());
        this.setOnMouseExited(e -> this.setColor("#1c1c1c","#ff3419"));

    }
    
     private void linkTo(String s) throws IOException {
        Scene sc = ap.getScene();
        Parent root = FXMLLoader.load(getClass().getResource(s +".fxml"));
        sc.setRoot(root);
    }

    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public void setColor(String bg,String bd){
        this.setStyle("-fx-background-color: " + bg +"; -fx-border-color: "+ bd);

    }
    public void highlight(){
        this.setStyle("-fx-background-color: #555555;");
    }

}
