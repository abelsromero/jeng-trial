package jeng.trial.front

import jeng.trial.data.BoardRepository
import jeng.trial.model.Board
import jeng.trial.model.Coordinate
import jeng.trial.model.State
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * Created by abelsr on 11/06/2016.
 *
 * Not implemented
 */
@RestController
@RequestMapping("/cell")
class CellService {

    @Autowired
    BoardRepository boardRepository

    @RequestMapping("/greeting")
    @ResponseBody
    State getState (String boardId, Integer x, Integer y) {
        Board board = boardRepository.findBoardById(boardId)
        if (board.getCells().contains(new Coordinate(x,y)))
            return State.ALIVE
        else
            return State.DEAD
    }

}
