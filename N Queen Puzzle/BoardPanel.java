import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class BoardPanel extends JPanel implements KeyListener
{
    private final int QUEENAMOUNT = 8;

    private Color[][] colorBoard;
    private Color darkSquareColor;
    private Color lightSquareColor;

    private Piece[][] board;

    private int dimensions;
    private int squareSize;

    public int displayIndex = 0;

    public Queen queen;
    
    public BoardPanel(int dimension)
    {
        board = new Piece[8][8];

        colorBoard = new Color[8][8];

        darkSquareColor = new Color(169, 122, 101);
        lightSquareColor = new Color(241, 217, 192);

        dimensions = dimension;
        squareSize = (dimensions / board.length) - 5;

        queen = new Queen(QUEENAMOUNT);

        initBoard();
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponents(g);

        drawColorBoard(g);

        updateBoard();

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[j][i].piece != null)
                {
                    g.drawImage(board[j][i].piece, board[j][i].position.x, board[j][i].position.y, board[j][i].dimensions, board[j][i].dimensions, this);
                }
            }
        }
    }

    private void updateBoard()
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(j == queen.solutions.get(displayIndex).get(i) && i == queen.solutions.get(displayIndex).indexOf(j))
                {
                    board[j][i] = new Piece(new File("bq.png"), new Point(j * squareSize, i * squareSize), squareSize);
                }
                else
                {
                    board[j][i] = new Piece(null, new Point(j * squareSize, i * squareSize), squareSize);
                }
            }
        }

        System.out.println("Display: " + (displayIndex + 1) + " out of " + queen.solutions.size() + " solutions.");
    }

    private void drawColorBoard(Graphics g)
    {
        int count = 0; 

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(count % 2 == 0)
                {
                    g.setColor(lightSquareColor);

                    colorBoard[j][i] = lightSquareColor;
                }
                else
                {
                    g.setColor(darkSquareColor);

                    colorBoard[j][i] = darkSquareColor;
                }

                g.fillRect(j * squareSize, i * squareSize, squareSize, squareSize);

                count++;
            }

            count++;
        }
    }

    private void initBoard()
    {
        int count = 0;

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(count < QUEENAMOUNT)
                {
                    board[j][i] = new Piece(new File("bq.png"), new Point(j * squareSize, i * squareSize), squareSize);
                }
                else
                {
                    board[j][i] = new Piece(null, new Point(j * squareSize, i * squareSize), squareSize);
                }

                count++;
            }
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if(displayIndex > 0)
            {
                displayIndex--;
            }
            else
            {
                displayIndex = queen.solutions.size() - 1;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(displayIndex < queen.solutions.size() - 1)
            {
                displayIndex++;
            }
            else
            {
                displayIndex = 0;
            }
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        // TODO Auto-generated method stub
        
    }
}


