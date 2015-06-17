package com.mplanner

import grails.test.mixin.*
import spock.lang.*


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Event)
class EventSpec extends Specification {

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
