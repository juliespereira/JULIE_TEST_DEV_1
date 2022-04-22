package backend

import java.sql.Timestamp
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

        long end = System.currentTimeMillis()
        float time = (end - initial) / 1000F

        println "Tempo gasto: " + time  
        println "Quantidade de Cotações: " + countStock.total

    }

    def getCompanies() {

        ArrayList<CompanyDto> companies = new ArrayList<CompanyDto>();

        def companiesReturn = Company.executeQuery(
            "SELECT * FROM Company" 
        )

        companiesReturn.each{ company ->

            ArrayList<Integer> stocksPriceReturn = new ArrayList<Integer>()

            def stocksReturn = Stock.executeQuery(
                "SELECT s FROM Stoke s, Company c" +
                "WHERE s.company = c.id" +
                "AND c.name LIKE :company" , [company:"${company.name}"]
            )

            stocksReturn.each{ stock ->
                stocksPriceReturn.add("${stock.price}")
            }

            double standardDeviation = standardDeviation(stocksPriceReturn)

            CompanyDto companydto = new CompanyDto("${company.name}", "${company.segment}", standardDeviation)

            companies.add(companydto)
        }

        def returnJson = new groovy.json.JsonBuilder(companies)
        return returnJson.toString()

    }

    public static double standardDeviation(ArrayList<Integer> priceArray){
        
        double total = 0.0, standardDeviation = 0.0;
        int length = priceArray.size();

        for (int i = 0; i < length; i++){
            total += double.valueOf(priceArray[i])
        }

        double average = total/length;

        for (int i = 0; i < length; i++){
            standardDeviation += Math.pow(Double.valueOf(priceArray[i]) - average, 2);
        }

        return Math.sqrt(standardDeviation/length);
    }
}
