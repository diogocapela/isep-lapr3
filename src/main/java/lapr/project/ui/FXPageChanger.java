package lapr.project.ui;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lapr.project.utils.Logger;

import java.io.IOException;

/**
 * This class loads a FXML page and injects it inside the main pane of the app.
 */
public class FXPageChanger {

    private static FXPageChanger staticInstance;
    private BorderPane mainPane;

    FXPageChanger(BorderPane mainPane) {
        this.mainPane = mainPane;
        staticInstance = this;
    }

    public static FXPageChanger getStaticInstance() {
        return staticInstance;
    }

    public void showPage(String pageName) {
        Pane loadedPage = null;
        try {
            // load the page
            loadedPage = FXMLLoader.load(getClass().getResource("/views/" + pageName));
            // set padding around the loaded page
            loadedPage.setPadding(new Insets(10, 10, 10, 10));
        } catch (IOException e) {
            Logger.log(e.getLocalizedMessage());
        }
        mainPane.setCenter(loadedPage);
    }

}
