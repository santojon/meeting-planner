package com.mplanner

import grails.test.mixin.*
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(MatchController)
class MatchControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        when: 'test'
        boolean b = true

        then: 'is tested'
        assert b
    }
}
