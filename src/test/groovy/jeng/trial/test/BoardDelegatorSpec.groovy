package jeng.trial.test

import jeng.trial.model.Board
import jeng.trial.model.BoardDelegator
import jeng.trial.model.Coordinate

/**
 * Created by abelsr on 11/06/2016.
 */

class BoardDelegatorSpec extends spock.lang.Specification {

    def "should find proper neighbours for 1,1"() {
        given:
        Board b = new Board(6, 6)
        BoardDelegator bd = new BoardDelegator(b)

        when:
        def candidates = bd.calculateNeighbours([1, 1] as Coordinate)
        def count = bd.countNeighbours(1, 1)

        then:
        // This transformation makes it easier to compare results
        candidates.collect { [it.x, it.y] }.containsAll([
                [0, 0], [1, 0], [2, 0],
                [0, 1], [2, 1],
                [0, 2], [1, 2], [2, 2]
        ])
        count == 8
    }

    def "should find proper neighbours for 0,0"() {
        given:
        Board b = new Board(6, 6)
        BoardDelegator bd = new BoardDelegator(b)

        when:
        def candidates = bd.calculateNeighbours([0, 0] as Coordinate)
        def count = bd.countNeighbours(0, 0)

        then:
        candidates.collect { [it.x, it.y] }.containsAll([[0, 1], [1, 0], [1, 1]])
        count == 3
    }

    def "should find proper neighbours for 5,5"() {
        given:
        Board b = new Board(6, 6)
        BoardDelegator bd = new BoardDelegator(b)

        when:
        def candidates = bd.calculateNeighbours([5, 5] as Coordinate)
        def count = bd.countNeighbours(5, 5)

        then:
        candidates.collect { [it.x, it.y] }.containsAll([[4, 4], [5, 4], [4, 5]])
        count == 3
    }

    def "should find proper neighbours for 3,3"() {
        given:
        Board b = new Board(6, 6)
        BoardDelegator bd = new BoardDelegator(b)

        when:
        def candidates = bd.calculateNeighbours([3, 3] as Coordinate)
        def count = bd.countNeighbours(3, 3)

        then:
        candidates.collect { [it.x, it.y] }.containsAll([
                [2, 2], [2, 3], [2, 4],
                [3, 2], [3, 4],
                [4, 2], [4, 3], [4, 4]
        ])
        count == 8
    }

    def "should find proper neighbours for 5,3"() {
        given:
        Board b = new Board(6, 6)
        BoardDelegator bd = new BoardDelegator(b)

        when:
        def candidates = bd.calculateNeighbours([5, 3] as Coordinate)
        def count = bd.countNeighbours(5, 3)

        then:
        candidates.collect { [it.x, it.y] }.containsAll([
                [4, 2], [5, 2],
                [4, 3],
                [4, 4], [5, 4]
        ])
        count == 5
    }

    def "should find proper neighbours for -2,-2_error"() {
        given:
        Board b = new Board(6, 6)
        BoardDelegator bd = new BoardDelegator(b)

        when:
        def candidates = bd.calculateNeighbours([-2, -2] as Coordinate)
        def count = bd.countNeighbours(-2, -2)

        then:
        candidates.collect { [it.x, it.y] }.containsAll([])
        count == 0
    }

    def "should find proper neighbours for 6,6_error"() {
        given:
        Board b = new Board(6, 6)
        BoardDelegator bd = new BoardDelegator(b)

        when:
        def candidates = bd.calculateNeighbours([6, 6] as Coordinate)
        def count = bd.countNeighbours(6, 6)

        then:
        candidates.collect { [it.x, it.y] }.containsAll([])
        count == 0
    }

    def "should validate an alive cell"() {
        when:
        Board b = new Board(6, 6)
        b.cells = [
                [1, 1] as Coordinate,
                [2, 2] as Coordinate,
                [3, 3] as Coordinate,
        ]
        BoardDelegator bd = new BoardDelegator(b)

        then:
        bd.isAliveCell(2, 2)
        bd.isAliveCell(1, 1)
        bd.isAliveCell(3, 3)
    }

    def "should validate a dead cell"() {
        when:
        Board b = new Board(6, 6)
        b.cells = [
                [1, 1] as Coordinate,
                [2, 2] as Coordinate,
                [3, 3] as Coordinate,
        ]
        BoardDelegator bd = new BoardDelegator(b)

        then:
        !bd.isAliveCell(1, 0)
        !bd.isAliveCell(5, 5)
        !bd.isAliveCell(3, 2)
    }
}