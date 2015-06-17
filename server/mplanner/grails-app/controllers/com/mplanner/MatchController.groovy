package com.mplanner

import grails.converters.JSON

import java.text.SimpleDateFormat

class MatchController {
    def matchService

    def match() {
        Map matchedEvents
        Map defaultParams = [controller: 'match', action: 'match', format: null]
        def filtered = params - defaultParams

        if(filtered) {
            User creator
            List invited
            Integer length
            String eventTitle
            Date startDate
            Date endDate

            filtered.each { key, value ->
                switch (key) {
                    case 'creator':
                        creator = User.findByEmail(value.toString())
                        break
                    case 'startDate':
                        try {
                            startDate = new SimpleDateFormat('dd-MM-yyyy:hh-mm').parse(value.toString())
                        } catch (all) {
                            log.error(all.message)
                        }
                        break
                    case 'endDate':
                        try {
                            endDate = new SimpleDateFormat('dd-MM-yyyy:hh-mm').parse(value.toString())
                        } catch (all) {
                            log.error(all.message)
                        }
                        break
                    case 'length':
                        length = value.toString().toInteger()
                        break
                    case 'title':
                        eventTitle = value.toString()
                        break
                    case 'invited':
                        invited = value.toString().replace('[', '').replace(']', '').split(',')
                        break
                }
                //render key + '\n'
                //render value + '</br>'
            }
            //render '</br>'
            matchedEvents = matchService.match(creator, invited, length, eventTitle, startDate, endDate)
            render matchedEvents as JSON
        } else {
            render 'invalid parameters'
        }
    }
}
