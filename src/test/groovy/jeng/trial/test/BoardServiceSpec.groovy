package jeng.trial.test

import groovy.json.JsonSlurper
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import jeng.trial.model.Coordinate
import spock.lang.Ignore

/**
 * Created by abelsr on 11/06/2016.
 *
 * Note: for local dev, do NOT run in CI
 */
@Ignore
class BoardServiceSpec extends spock.lang.Specification {

    private static final String BASE_URL = 'http://localhost:8080/jeng-trial/'

    def "should test Service creating a random board"() {
        when:
        String result = createGetRequest('board/test')

        then:
        result != null
    }

    def "should create a random board and retrieve it"() {
        when:
        String boardId = createGetRequest('board/test')
        def result = createGetRequest("board/$boardId", true)

        then:
        result.id == boardId
        result.cells.size() > 0
        result.height > 0
        result.width > 0
    }

    def "should create a pre-defined board and retrieve it"() {

        when:
        String boardId = createPostRequest('board/')
        def result = createGetRequest("board/$boardId", true)

        then:
        result.id == boardId
        result.cells.size() > 0
        result.height > 0
        result.width > 0
    }

    def "should create a random board and iterate through state"() {

        when:
        String boardId = createPostRequest('board/')
        def board = createGetRequest("board/$boardId", true)
        def cells = createGetRequest("board/next", true)

        then:
        result.id == boardId
        result.cells.size() > 0
        result.height > 0
        result.width > 0
    }

    /**
     * Helper methods
     */
    private def createGetRequest(String path, boolean json = false) {
        def result
        new HTTPBuilder(BASE_URL).request(Method.GET) {
            uri.path = path
//            headers = [
//                    Authorization : "Token $token",
//                    'Content-Type': 'application/json'
//            ]
            if (json) {
                response.success = {
                    result = new JsonSlurper().parse(it.entity.content)
                }
            } else {
                response.success = { resp ->
                    result = resp.entity.content.text
                }
            }
        }
        return result
    }

    private def createPostRequest(String path, boolean json = false) {
        def result
        new HTTPBuilder(BASE_URL).request(Method.POST) {
            uri.path = path
            send(ContentType.URLENC, [
                    height: 9,
                    width : 11,
                    cells : [new Coordinate(0, 1), new Coordinate(2, 2)]
            ])
            if (json) {
                response.success = {
                    result = new JsonSlurper().parse(it.entity.content)
                }
            } else {
                response.success = { resp ->
                    result = resp.entity.content.text
                }
            }
        }
        return result
    }


}