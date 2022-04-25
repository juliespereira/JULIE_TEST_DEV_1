package backend

import java.sql.Timestamp
import grails.gorm.transactions.Transactional
import java.util.*

@Transactional
class StockService {

    def getStocks(String company, int numbersOfHoursUntilNow) {

        long initial = System.currentTimeMillis()

        final Timestamp timesTampInitial = new Timestamp(System.currentTimeMillis() - java.time.Duration.ofHours(numbersOfHoursUntilNow).toMillis())

        def stocks = Stock.executeQuery(
            "SELECT s FROM Stock s, Company c " +
            "WHERE s.company = c.id " +
            "AND c.name LIKE :company " +
            "AND s.priceDate > :initialDate ", [company:company, initialDate:timesTampInitial]
        )

        def countStock = Stock.executeQuery(
            "SELECT count(s) AS total FROM Stock s, Company c " +
            "WHERE s.company = c.id " + 
            "AND c.name LIKE :company " +
            "AND s.priceDate > :initialDate ", [company:company, initialDate:timesTampInitial]
        )

        stocks.each { stock ->
            println "${stock.price}"
        }

        long end = System.currentTimeMillis()
        float time = (end - initial) / 1000F

        println "Tempo gasto: " + time  
        println "Quantidade de Cotações: " + countStock

    }
}
