package books

class UrlMappings {

    static mappings = {

        "/books"(controller:"book", action: "save", method: "POST")
        "/books"(controller: "book", action: "index", method: "GET")


        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
