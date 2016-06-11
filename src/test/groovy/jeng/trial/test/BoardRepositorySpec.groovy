package jeng.trial.test

import jeng.trial.data.BoardRepository
import jeng.trial.data.MemoryBackend
import jeng.trial.model.Board
import jeng.trial.model.Coordinate
import jeng.trial.model.InvalidCellException

/**
 * Created by abelsr on 11/06/2016.
 */
class BoardRepositorySpec extends spock.lang.Specification {


    def "should create a board"() {
        given:
        BoardRepository repo = new MemoryBackend()

        when:
        String id = repo.createBoard(20, 10, null)
        Board board = repo.findBoardById(id)

        then:
        id != null
        board != null

        board.height == 20
        board.width == 10
        board.cells == []
    }

    def "should throw exception when cell position is not in the board"() {
        given:
        BoardRepository repo = new MemoryBackend()

        when:
        String id = repo.createBoard(1, 1, null)
        Board board = repo.findBoardById(id)
        board.cells = [
                [1, 1] as Coordinate
        ]

        then:
        thrown(InvalidCellException)
    }

    def "should not find board that does not exists"() {
        given:
        BoardRepository repo = new MemoryBackend()

        when:
        String id = repo.createBoard(1, 1, null)
        Board board = repo.findBoardById('aaaaa')

        then:
        board == null
    }

    def "should iterate the board and kill all cells"() {
        given:
        BoardRepository repo = new MemoryBackend()

        when:
        String id = repo.createBoard(1, 1, null)
        Board board = repo.findBoardById(id)
        board.cells = [
                [0, 0] as Coordinate
        ]

        then:
        repo.next(id).isEmpty()
        repo.findBoardById(id).cells.isEmpty()
    }


    def "should iterate the board and creates 10 new cells"() {
        given:
        BoardRepository repo = new MemoryBackend()

        when:
        def cells = [
                [1, 4] as Coordinate,
                [1, 5] as Coordinate,
                [2, 5] as Coordinate,

                [4, 1] as Coordinate,
                [5, 1] as Coordinate,
                [5, 2] as Coordinate,
        ]
        String id = repo.createBoard(6, 6, cells)
        Board board = repo.findBoardById(id)

        then:
        board != null
        repo.next(id).collect { [it.x, it.y] }.containsAll([
                [3, 0], [4, 0],
                [5, 1], [5, 2],
                [3, 2],

                [2, 3],
                [0, 4], [0, 5],
                [1, 5], [2, 5]
        ])
        repo.findBoardById(id).cells.size() == 10
    }

}