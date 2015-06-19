package com.mplanner

class EventAttempt {

    String eventTitle
    Date startDate
    Date endDate
    User creator

    static belongsTo = [creator: User]
    static hasMany = [invited: String]

    static constraints = {
        creator(nullable: false)
        eventTitle(nullable: false)
        startDate(nullable: false)
        endDate(nullable: false)
    }

    Event toEvent() {
        return this as Event
    }
}
