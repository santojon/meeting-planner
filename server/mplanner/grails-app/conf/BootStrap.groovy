import com.mplanner.Event
import com.mplanner.User

import java.text.SimpleDateFormat

class BootStrap {

    def init = { servletContext ->
        User admin = new User(name: 'admin', username: 'admin', email: 'admin@admin.com')
        admin.save()
        User user = new User(name: 'user', username: 'user', email: 'user@user.com')
        user.save()

        Map dates = [
                '29-06-2015:15-00': '29-06-2015:17-00',
                '20-06-2015:15-00': '20-06-2015:17-00',
                '22-06-2015:08-00': '22-06-2015:08-30'
        ]

        dates.eachWithIndex { key, value, i ->
            new Event(eventTitle: "test${i}", startDate: new SimpleDateFormat('dd-MM-yyyy:hh-mm').parse(key.toString()), endDate: new SimpleDateFormat('dd-MM-yyyy:hh-mm').parse(value.toString()), creator: i % 2 == 0 ? admin : user, invited: i % 2 == 0 ? ["asd${i}@fg.com", "invited${i}@creator.com"] : [admin.email]).save()
        }
    }
    def destroy = {
    }
}
