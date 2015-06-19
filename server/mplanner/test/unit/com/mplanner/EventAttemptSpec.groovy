package com.mplanner

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(EventAttempt)
class EventAttemptSpec extends Specification {

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
