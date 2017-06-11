package books

import grails.converters.JSON
import grails.gorm.multitenancy.Tenants

import static grails.gorm.multitenancy.Tenants.withId

/**
 * Created by long on 2017/6/11.
 */
class BookController {

    def save() {
        def data = request.JSON
        def book = withId(Tenants.currentId()) {
            def book = new Book(name: data.name)
            book.save()
        }

        render book as JSON
    }


    def index() {
        List<Book> books = withId(Tenants.currentId()) {
            Book.list()
        }
        render books as JSON
    }
}
