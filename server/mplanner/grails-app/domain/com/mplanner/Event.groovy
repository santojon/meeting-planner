package com.mplanner

class Event {

    String eventTitle
    Date startDate
    Date endDate

    static belongsTo = [creator: User]
    static hasMany = [invited: String]

    static constraints = {
        creator(nullable: false)
        eventTitle(nullable: false)
        startDate(nullable: false, unique: ['creator'])
        endDate(nullable: false, unique: ['creator'])
    }
}
