package backend

import grails.gorm.transactions.Transactional

@Transactional
class CompanyService {

    def getCompanies() {

        ArrayList<CompanyDto> companies = new ArrayList<CompanyDto>();

        def companiesReturn = Company.executeQuery(
            "SELECT c FROM Company c" 
        )

        companiesReturn.each{ company ->

            ArrayList<Integer> stocksPriceReturn = new ArrayList<Integer>()

            def stocksReturn = Stock.executeQuery(
                "SELECT s FROM Stock s, Company c " +
                "WHERE s.company = c.id " +
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
            total += Double.valueOf(priceArray[i])
        }

        double average = total/length;

        for (int i = 0; i < length; i++){
            standardDeviation += Math.pow(Double.valueOf(priceArray[i]) - average, 2);
        }

        return Math.sqrt(standardDeviation/length);
    }
}
