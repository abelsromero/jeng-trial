package jeng.trial.data

import groovy.util.logging.Log4j
import jeng.trial.model.Board
import jeng.trial.model.BoardDelegator
import jeng.trial.model.Cell
import jeng.trial.model.Coordinate
import org.springframework.stereotype.Service

/**
 * Simple memory persistence of created boards
 *
 * Created by abelsr on 11/06/2016.
 */
@Service
@Log4j
class MemoryBackend implements BoardRepository {

    // Data
    Map<String, Board> boards = [:]


    @Override
    String createBoard(Integer height, Integer width, ArrayList<Coordinate> coordinates) {
        Board b = new Board(height, width)
        b.cells = coordinates
        update(generateId(), b)
        return b.id
    }

    @Override
    Board findBoardById(String boardId) {
        boards[boardId]
    }

    /**
     * Update the current instance if exists or creates a new one otherwise
     *
     * TODO: update historic
     */
    @Override
    void update(String boardId, Board board) {
        if (!board.id)
            board.id = boardId
        // prevents lots of issues
        if (!board.cells)
            board.cells = []
        boards[boardId] = board
    }

    @Override
    List<Cell> next(String boardId) {
        Board current = findBoardById(boardId)
        BoardDelegator bd = new BoardDelegator(current)
        bd.nextState()
        // This is not necessary because this is a map-memory implementation, but in a real scenario we will need to
        // deal with retrieve-and-update, clone and also concurrency issues
        update(boardId, bd.board)
        return bd.board.cells
    }

    /**
    * Helper method to generate IDs
    */
    private String generateId() {
        UUID.randomUUID().toString()
    }

}
