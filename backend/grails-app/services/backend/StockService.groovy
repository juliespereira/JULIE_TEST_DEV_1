package backend

import grails.gorm.transactions.Transactional

@Transactional
class StockService {

    def getStocks(String company, int numbersOfHoursUntilNow) {

        long initial = System.currentTimeMillis()
        long numbersOfMillisUntilNow = java.time.Duration.ofHours(numbersOfHoursUntilNow).toMillis()

        Timestamp timesTampInitial = new Timestamp(initial - numbersOfMillisUntilNow)

        def stocks = Stock.executeQuery(
            "SELECT s.* FROM Stock s, Company c " +
            "WHERE s.company = c.id " +
            "AND c.name LIKE :company " +
            "AND s.priceDate > :initialDate ", [company:company, initialDate:timesTampInitial]
        )

        def countStock = Stock.executeQuery(
            "SELECT count(s.*) AS total FROM Stock s, Company c " +
            "WHERE s.company = c.id " +
            "AND c.name LIKE :company " +
            "AND s.priceDate > :initialDate ", [company:company, initialDate:timesTampInitial]
        )

        stocks.each { stock ->
            println "${stock.price}"
        }

        long final = System.currentTimeMillis()
        float time = (final - initial) / 1000F

        println "Tempo gasto: " + time  
        println "Quantidade de Cotações: " + countStock.total

    }

    def getCompanies() {

        ArrayList<CompanyDto> companies = new ArrayList<CompanyDto>();

        def companiesReturn = Company.executeQuery(
            
        )

    }
}
