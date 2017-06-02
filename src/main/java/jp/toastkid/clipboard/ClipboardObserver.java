package jp.toastkid.clipboard;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;

import java.util.concurrent.TimeUnit;

/**
 * Clipboard observer.
 *
 * @author toastkidjp
 */
public class ClipboardObserver {

    /** For use in RxJava's mapping. */
    private static final Image EMPTY_IMAGE = new WritableImage(1, 1);

    /** Clipboard API. */
    private Clipboard clipboard;

    /**
     * Get system default clipboard.
     */
    public ClipboardObserver() {
        clipboard = Clipboard.getSystemClipboard();
    }

    /**
     * Subscribe with string and image consumer.
     * @param stringConsumer
     * @param imageConsumer
     */
    public void subscribe(
            final Consumer<String> stringConsumer,
            final Consumer<Image> imageConsumer
    ) {
        final Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);

        interval.observeOn(JavaFxScheduler.platform())
                .map(l -> getStringOrEmpty())
                .filter(str -> !str.isEmpty())
                .subscribe(stringConsumer, Throwable::printStackTrace);

        interval.observeOn(JavaFxScheduler.platform())
                .map(l -> getImageOrEmpty())
                .filter(img -> img != EMPTY_IMAGE)
                .subscribe(imageConsumer, Throwable::printStackTrace);
    }

    /**
     * Return string or empty.
     * @return string or empty
     */
    private String getStringOrEmpty() {
        final String string = clipboard.getString();
        return string != null ? string : "";
    }

    /**
     * Return image or empty object.
     * @return image or empty object
     */
    private Image getImageOrEmpty() {
        final Image image = clipboard.getImage();
        return image != null ? image : EMPTY_IMAGE;
    }
}
