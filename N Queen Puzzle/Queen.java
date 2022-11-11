import java.util.ArrayList;
import java.util.List;

public class Queen 
{
    public List<List<Integer>> solutions;

    public Queen(int queenAmount)
    {
        solutions = new ArrayList<>();

        solveNQueens(queenAmount, 0, new ArrayList<Integer>(), solutions);
    }

    private static void solveNQueens(int n, int row, List<Integer> colPlacements, List<List<Integer>> result)
    {
        if(row == n)
        {
            result.add(new ArrayList<>(colPlacements));
        }
        else
        {
            for(int col = 0; col < n; col++)
            {
                colPlacements.add(col);

                if(isValid(colPlacements))
                {
                    solveNQueens(n, row + 1, colPlacements, result);
                }

                colPlacements.remove(colPlacements.size() - 1);
            }
        }
    }
    private static boolean isValid(List<Integer> colPlacements)
    {
        int rowId = colPlacements.size() - 1;

        for(int i = 0; i < rowId; i++)
        {
            int diff = Math.abs(colPlacements.get(i) - colPlacements.get(rowId));

            if(diff == 0 || diff == rowId - i)
            {
                return false;
            }
        }

        return true;
    }
}
