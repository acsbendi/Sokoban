import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

interface Displayable {
    /**
     * Getter method for the image (visual representation) of the object implementing {@code Displayable} interface.
     * @return The image (visual representation) of the object.
     */
    BufferedImage getImage();
    /**
     * Static method for combining 2 images. Where the second image contains a non-empty pixel, it overwrites the first image's pixel.
     * @param b1 The first image.
     * @param b2 The second image.
     * @return The combined image.
     */
    static BufferedImage combineImages(BufferedImage b1, BufferedImage b2){
        BufferedImage bi1 = new BufferedImage(b1.getWidth(), b1.getHeight(), BufferedImage.TYPE_INT_ARGB);
        bi1.getGraphics().drawImage(b1, 0, 0, null);
        BufferedImage bi2 = new BufferedImage(b2.getWidth(), b2.getHeight(), BufferedImage.TYPE_INT_ARGB);
        bi2.getGraphics().drawImage(b2, 0, 0, null);

        BufferedImage result = new BufferedImage(b1.getWidth(),b1.getHeight(),BufferedImage.TYPE_INT_ARGB);
        result.getGraphics().drawImage(bi1,0,0,null);

        int[] pixels2=((DataBufferInt) bi2.getRaster().getDataBuffer()).getData();
        int[] pixelsTgt=((DataBufferInt) result.getRaster().getDataBuffer()).getData();
        for(int a = 0; a < pixels2.length; ++a)
        {
            if(pixels2[a] != 0) pixelsTgt[a] = pixels2[a];
            //overwrite pixels of the lower layer image,
            //where the pixels of the upper layer image are not empty
        }

        return result;
    }
}
