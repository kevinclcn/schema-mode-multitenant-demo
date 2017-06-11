package books

import grails.gorm.MultiTenant

/**
 * Created by long on 2017/6/11.
 */
class Book implements MultiTenant<Book> {
    String name
}
