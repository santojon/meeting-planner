package com.mplanner

class Event {

    String eventTitle
    Date startDate
    Date endDate
    List<User> invited = []

    static hasOne = [creator: User]

    static constraints = {
        creator(nullable: false)
        eventTitle(nullable: false)
        startDate(nullable: false)
        endDate(nullable: false)
    }
}
