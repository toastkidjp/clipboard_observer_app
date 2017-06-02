package jp.toastkid.clipboard;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

/**
 * Make JavaFX node with Clipboard content.
 *
 * @author toastkidjp
 */
public class ClipboardApplier {

    private long lastContentId = -1;

    private final Clipboard clipboard;

    private final Pane box;

    public ClipboardApplier(final Pane box) {
        this.clipboard = Clipboard.getSystemClipboard();
        this.box = box;
    }

    public void makeClickableLabel(final String string) {
        if (string != null && lastContentId != string.hashCode()) {
            lastContentId = string.hashCode();
            final Label label = new Label(string);
            label.setOnMouseClicked(e -> clipboard.setContent(makeContent(DataFormat.PLAIN_TEXT, string)));
            box.getChildren().add(label);
        }
    }

    public void makeClickableImageView(final Image image) {
        if (image != null && lastContentId != -2) {
            lastContentId = -2;
            final ImageView imageView = new ImageView(image);
            imageView.setOnMouseClicked(e -> clipboard.setContent(makeContent(DataFormat.IMAGE, image)));
            box.getChildren().add(imageView);
        }
    }

    private Map<DataFormat, Object> makeContent(final DataFormat df, final Object obj) {
        final Map<DataFormat, Object> content = new HashMap<>(1);
        content.put(df, obj);
        return content;
    }

}
