import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Point;

public class Piece
{
    public Image piece;

    public Point position;
    public int dimensions;

    public boolean isClicked;

    public String pieceType;

    public Piece(File image, Point point, int dimensions)
    {
        if(image != null)
        {
            try
            {
                piece = ImageIO.read(image);
                
                pieceType = image.getName().substring(1, 2);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        
        this.position = point;
        this.dimensions = dimensions;

        isClicked = false;
    }

    public Piece(Point point, Image image, int dimensions)
    {
        if(image != null)
        {
            piece = image;
        }
        
        this.position = point;
        this.dimensions = dimensions;

        isClicked = false;
    }
}
