package backend

class Company {

    String name;
    String segment;

    static hashMany = [stock:Stock]

    static constraints = {
        name = nullable: false, maxsize: 255, blank: false, unique: true
        segment = nullable: false, maxsize: 255, blank: false
    }

}
