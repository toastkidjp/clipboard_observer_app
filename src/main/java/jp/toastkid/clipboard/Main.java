package jp.toastkid.clipboard;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main of clipboard app.
 *
 * @author toastkidjp
 */
public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(Main.class);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        final VBox box = new VBox();
        initContent(box);

        stage.setTitle("Clipboard sample");
        stage.setScene(new Scene(box));
        stage.show();
    }

    private void initContent(final Pane box) {
        final ClipboardApplier  applier  = new ClipboardApplier(box);
        final ClipboardObserver observer = new ClipboardObserver();
        observer.subscribe(applier::makeClickableLabel, applier::makeClickableImageView);
    }


}