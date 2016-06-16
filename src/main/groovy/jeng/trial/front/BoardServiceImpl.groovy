package jeng.trial.front

import groovy.util.logging.Log4j
import jeng.trial.data.BoardRepository
import jeng.trial.model.Board
import jeng.trial.model.Cell
import jeng.trial.model.Coordinate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Created by abelsr on 11/06/2016.
 * NOTES:
 * @ResponseBody is not recognized
 *
 */
@RestController
@RequestMapping("/board")
@Log4j
class BoardServiceImpl {

    @Autowired
    BoardRepository boardRepository

    /**
     * Test method that creates a simple random board
     */
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    @ResponseBody
    String test() {
        Random r = new Random()
        List<Coordinate> aliveCells = [
                [0, 0] as Coordinate, [0, 1] as Coordinate, [0, 2] as Coordinate,
                [1, 0] as Coordinate, [1, 1] as Coordinate, [1, 2] as Coordinate,
                [2, 0] as Coordinate, [2, 1] as Coordinate, [2, 2] as Coordinate,
        ]
        boardRepository.createBoard(5 + r.nextInt(10), 5 + r.nextInt(10), aliveCells)
    }

    /**
     * Create empty board and returns id
     *
     * Note: could pass a Board object also instead of split parameters
     */
    @RequestMapping(path = "/", method = RequestMethod.POST)
    @ResponseBody
    String createBoard(
            @RequestParam(value = "height") Integer height,
            @RequestParam(value = "width") Integer width,
            @RequestParam(value = "cells") List<Coordinate> aliveCells) {
        boardRepository.createBoard(height, width, aliveCells)
    }

    @RequestMapping(path = "/{boardId}", method = RequestMethod.GET)
    @ResponseBody
    Board getBoard(@PathVariable("boardId") String boardId) {
        boardRepository.findBoardById(boardId)
    }
    /**
     * Returns the list of alive cells
     */
    @RequestMapping(path = "/{boardId}/cells", method = RequestMethod.GET)
    @ResponseBody
    List<Coordinate> getAliveCells(@PathVariable("boardId") String boardId) {
        boardRepository.findBoardById(boardId).cells
    }

    @RequestMapping(path = "/state", method = RequestMethod.GET)
    @ResponseBody
    Board status(String boardId) {
        Board b = boardRepository.findBoardById(boardId)
        if (b) b
        else throw new BoardNotFoundException("Board with id '$boardId' not found")
    }

    /**
     * Note: should not be GET according to REST 'best practices' but I prefer to keep this simple and usable
     */
    @RequestMapping(path = "/next", method = RequestMethod.GET)
    @ResponseBody
    List<Cell> nextStep(String boardId) {
        boardRepository.next(boardId)
    }


}
