import com.mplanner.Event
import com.mplanner.User

class BootStrap {

    def init = { servletContext ->
        User user = new User(name: 'user', username: 'user', email: 'user@user.com')
        user.save()
        User admin = new User(name: 'admin', username: 'admin', email: 'admin@admin.com')
        admin.save()

        Event e = new Event(eventTitle: 'test', startDate: new Date(), endDate: new Date(), creator: user)
        e.save()
        Event e2 = new Event(eventTitle: 'test2', startDate: new Date(), endDate: new Date(), creator: user, invited: [admin])
        e2.save()
    }
    def destroy = {
    }
}
