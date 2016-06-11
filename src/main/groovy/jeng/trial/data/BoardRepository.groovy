package jeng.trial.data

import jeng.trial.model.Board
import jeng.trial.model.Cell
import jeng.trial.model.Coordinate
import org.springframework.stereotype.Service

/**
 * Created by abelsr on 11/06/2016.
 */
interface BoardRepository {

    String createBoard(Integer height, Integer width, ArrayList<Coordinate> coordinates)

    void update(String boardId, Board board)

    Board findBoardById(final String boardId)

    List<Cell> next(String s)
}
