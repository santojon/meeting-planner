package com.mplanner

import grails.test.mixin.*
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    User user

    def setup() {
        mockForConstraintsTests(User)
        user = new User()
        user.name = 'user'
        user.username = 'user'
        user.email = 'user@user.com'
    }

    //region username
    void "test username: null username"() {
        when: 'the username is null'
        user.username = null

        then: 'the instance should not be validated'
        !user.validate()
        user.hasErrors()
        user.errors['username'] == 'nullable'
    }

    void "test Name: username is not unique"() {
        when: 'the username is not unique'
        user.username = 'user'
        user.save(flush: true)
        User u2 = new User(username: 'user')

        then:
        !u2.validate()
        u2.hasErrors()
        u2.errors['username'] == 'unique'
    }

    //region name
    void "test name: null name"() {
        when: 'the name is null'
        user.name = null

        then: 'the instance should not be validated'
        !user.validate()
        user.hasErrors()
        user.errors['name'] == 'nullable'
    }

    //region email
    void "test email: null email"() {
        when: 'the email is null'
        user.email = null

        then: 'the instance should not be validated'
        !user.validate()
        user.hasErrors()
        user.errors['email'] == 'nullable'
    }

    void "test Name: email is not unique"() {
        when: 'the email is not unique'
        user.email = 'user@user.com'
        user.save(flush: true)
        User u2 = new User(email: 'user@user.com')

        then:
        !u2.validate()
        u2.hasErrors()
        u2.errors['email'] == 'unique'
    }

    void "test email: invalid email"() {
        when: 'the email is invalid'
        user.email = 'user'

        then: 'the instance should not be validated'
        !user.validate()
        user.hasErrors()
        user.errors['email'] == 'email'
    }

    def cleanup() {
    }
}
