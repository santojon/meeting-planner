package com.mplanner

import grails.transaction.Transactional

@Transactional
class MatchService {

    Map match(User creator, List invited, Integer length, String eventTitle, Date startDate, Date endDate) {
        List<Set<Event>> invitedEvents = [[]]
        Set creatorEvents = creator.events

        invited.eachWithIndex { user, i ->
            User invitedUser = User.findByEmail(user.toString())

            if (invitedUser?.events) {
                List userEvents = Event.findAllByCreator(invitedUser)
                println(userEvents*.properties)
                userEvents.each { println(creator.isInvited(it)); println(it.invited); println(creator.email) }
                creatorEvents.addAll(Event.findAllByCreator(invitedUser).each { creator.isInvited(it) ? it : null })
                invitedEvents[i].addAll(invitedUser.events)
            } else {
                invitedEvents[i].addAll([])
            }
        }

        [creatorEvents: creatorEvents, invitedEvents: invitedEvents]
    }
}
