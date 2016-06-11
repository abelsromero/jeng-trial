package jeng.trial.front

import jeng.trial.model.State
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by abelsr on 11/06/2016.
 */
@RestController
@RequestMapping("/cell")
class CellService {

    @RequestMapping("/greeting")
    State getState (Integer x, Integer y) {

    }

}
