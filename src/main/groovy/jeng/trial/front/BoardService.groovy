package jeng.trial.front

import groovy.util.logging.Log4j
import jeng.trial.data.BoardRepository
import jeng.trial.model.Board
import jeng.trial.model.Cell
import jeng.trial.model.Coordinate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by abelsr on 11/06/2016.
 */
@RestController
@RequestMapping("/board")
@Log4j
class BoardService {

    @Autowired
    BoardRepository boardRepository

    /**
     * Create empty board and returns id
     */
    @RequestMapping(path = "/", method = RequestMethod.POST)
    String createBoard(Integer height, Integer width) {
        boardRepository.createBoard(height, width)
    }

    /**
     * Test method that creates a simple random board
     */
    @RequestMapping(path = "/test", method = RequestMethod.GET)
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
     */
    @RequestMapping(path = "/", method = RequestMethod.POST)
    String createBoard(
            @RequestParam(value = "width") Integer height,
            @RequestParam(value = "width") Integer width,
            @RequestParam(value = "cells") List<Coordinate> aliveCells) {
        boardRepository.createBoard(height, width, aliveCells)
    }

    @RequestMapping(path = "/state", method = RequestMethod.GET)
    Board status(String boardId) {
        Board b = boardRepository.findBoardById(boardId)
        if (b) b
        else throw new BoardNotFoundException("Board with id '$boardId' not found")
    }

    /**
     * Note: should not be GET according to REST 'best practices' but I prefer to keep this simple and usable
     */
    @RequestMapping(path = "/", method = RequestMethod.GET)
    List<Cell> nextStep(String boardId) {
        boardRepository.next(boardId)
    }

    /**
     * Returns the list of alive cells
     */
    @RequestMapping(method = RequestMethod.GET)
    List<Coordinate> aliveCells(String boardId) {
        boardRepository.findBoardById(boardId).cells
    }

}
