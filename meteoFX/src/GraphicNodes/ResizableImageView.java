package GraphicNodes;

import javafx.scene.image.ImageView;

/**
 * This class is responsible of resizing automatically an image inside it.
 */
public class ResizableImageView extends ImageView {
    @Override
    public double minWidth(double height) {
        return 100;
    }

    @Override
    public double minHeight(double width) {
        return 100;
    }

    @Override
    public double maxWidth(double height) {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public double maxHeight(double width) {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public boolean isResizable()
    {
        return true;
    }

    /**
     * This method is called when the element is resized.
     */
    @Override
    public void resize(double width, double height)
    {
        setFitWidth(width);
        setFitHeight(height);
    }

}
