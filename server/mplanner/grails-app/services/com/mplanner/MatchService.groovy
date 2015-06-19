package com.mplanner

import grails.transaction.Transactional

@Transactional
class MatchService {

    /**
     * Get the parameters passed from controller to match
     * @param creator
     * @param invited
     * @param length
     * @param eventTitle
     * @param startDate
     * @param endDate
     * @return a map of matched Events
     */
    Map match(User creator, List invited, Integer length, String eventTitle, Date startDate, Date endDate) {

        // get all events for creator and all invited users
        def (Set creatorEvents, List<Set> invitedEvents) = getAllEventsFrom(creator, invited)

        // filter events by startDate and endDate
        (creatorEvents, invitedEvents) = filterByDates(creatorEvents, invitedEvents, startDate, endDate)

        // invert lists
        //(creatorEvents, invitedEvents) = generateFreeEventsListsInRange(creatorEvents, invitedEvents, startDate, endDate)

        // filter by length
        (creatorEvents, invitedEvents) = filterByLength(creatorEvents, invitedEvents, length)

        // match lists

        // create potential events (attempts)

        // return result list
        [creatorEvents: creatorEvents, invitedEvents: invitedEvents]
    }

    /**
     * Helper method to get all events from event related persons
     * @param creator
     * @param invited
     * @return the respective collections for creator and invited
     */
    private getAllEventsFrom(User creator, List invited) {
        List<Set<Event>> invitedEvents = [[]]
        Set creatorEvents = Event.findAllByCreator(creator)

        invited.eachWithIndex { user, i ->
            User invitedUser = User.findByEmail(user.toString())

            if (invitedUser) {
                Set userEvents = Event.findAllByCreator(invitedUser)
                userEvents.each {
                    creator.isInvited(it) ? creatorEvents.add(it) : {}
                }
                invitedEvents[i].addAll(userEvents)
            } else {
                invitedEvents[i].addAll([])
            }
        }
        [creatorEvents, invitedEvents]
    }

    /**
     * Filter the collections by dates
     * @param creatorEvents
     * @param invitedEvents
     * @param startDate
     * @param endDate
     * @return
     */
    private filterByDates(Set creatorEvents, List<Set> invitedEvents, Date startDate, Date endDate) {
        Set creatorFilteredEvents = []
        List<Set> invitedFilteredEvents = [[]]

        creatorEvents.each { Event event ->
            (event.startDate >= startDate && event.endDate <= endDate ) ? creatorFilteredEvents.add(event) : {}
        }

        invitedEvents.eachWithIndex { userEvents, i ->
            userEvents.each { Event event ->
                (event.startDate >= startDate && event.endDate <= endDate ) ? invitedFilteredEvents[i].add(event) : {}
            }
        }
        [creatorFilteredEvents, invitedFilteredEvents]
    }

    /**
     * Get the negative lists for the ranged dates
     * @param creatorEvents
     * @param invitedEvents
     * @param startDate
     * @param endDate
     * @return
     */
    private generateFreeEventsListsInRange(Set creatorEvents, List<Set> invitedEvents, Date startDate, Date endDate) {
        Set creatorNegativeEvents = []
        List<Set> invitedNegativeEvents = [[]]

        creatorEvents.each { Event event ->
            (event.startDate >= startDate && event.endDate <= endDate ) ? creatorNegativeEvents.add(event) : {}
        }

        invitedEvents.eachWithIndex { userEvents, i ->
            userEvents.each { Event event ->
                (event.startDate >= startDate && event.endDate <= endDate ) ? invitedNegativeEvents[i].add(event) : {}
            }
        }
        [creatorNegativeEvents, invitedNegativeEvents]
    }

    /**
     * Filter the collections by dates
     * @param creatorEvents
     * @param invitedEvents
     * @param startDate
     * @param endDate
     * @return
     */
    private filterByLength(Set creatorEvents, List<Set> invitedEvents, Integer length) {
        Set creatorFilteredEvents = []
        List<Set> invitedFilteredEvents = [[]]

        creatorEvents.each { Event event ->
            ((event.endDate.time - event.startDate.time) / 60000 >= length) ? creatorFilteredEvents.add(event) : {}
        }

        invitedEvents.eachWithIndex { userEvents, i ->
            userEvents.each { Event event ->
                ((event.endDate.time - event.startDate.time) / 60000 >= length) ? invitedFilteredEvents[i].add(event) : {}
            }
        }
        [creatorFilteredEvents, invitedFilteredEvents]
    }
}
