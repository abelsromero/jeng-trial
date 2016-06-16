package jeng.trial.model
/**
 * Keeps business logic for {@link jeng.trial.model.Board} to avoid mixing logic with presentation object
 *
 * Note: as an alternative the methods could be added to the Board class using extensions or the metaclass. But imho,
 * these adds unnecessary complexity.
 *
 * Created by abelsr on 11/06/2016.
 */
class BoardDelegator {

    @Delegate
    Board board

    public BoardDelegator(Board board) {
        this.board = board
    }

    /**
     * Updates the state of the cells of the Board
     */
    public void nextState() {
        List<Cell> newState = []
        cells.each { c ->
            List<Coordinate> candidates = calculateNeighbours(c.x, c.y)
            def neighbours = candidates.intersect(cells)
            /*
            Any live cell with fewer than two live neighbours dies, as if caused by under-population.
            Any live cell with two or three live neighbours lives on to the next generation.
            Any live cell with more than three live neighbours dies, as if by over - population.
            */
            if (neighbours.size() == 2 || neighbours.size() == 3) {
                newState << c
            }
        }
        /*
        Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
        */
        for (x in 0..width) {
            for (y in 0..width) {
                List<Coordinate> candidates = calculateNeighbours(x, y)
                def neighbours = candidates.intersect(cells)
                if (!isAliveCell(x, y) && neighbours.size() == 3) {
                    newState << new Coordinate(x, y)
                }
            }
        }
        // replace all list of alive cells
        cells = newState
    }

    boolean isAliveCell(int x, int y) {
        board.cells.contains(new Coordinate(x, y))
    }

    /**
     * Returns the list of coordinate neighbours of a specific coordinate
     */
    List<Coordinate> calculateNeighbours(Coordinate coordinate) {
        calculateNeighbours(coordinate.x, coordinate.y)
    }

    /**
     * Returns number of neighbours of a specific coordinate
     * Note: done for performance
     */
    List<Coordinate> calculateNeighbours(Integer xx, Integer yy) {
        if (xx >= width || yy >= height)
            return []

        def xCandidates = (xx - 1..xx + 1).findAll { it >= 0 && it < width }
        def yCandidates = (yy - 1..yy + 1).findAll { it >= 0 && it < height }

        def values = []
        for (x in xCandidates) {
            for (y in yCandidates) {
                if (xx != x || yy != y)
                    values << new Coordinate(x, y)
            }
        }
        return values
    }

    def countNeighbours(int x, int y) {
        calculateNeighbours(x, y).size()
    }
}
