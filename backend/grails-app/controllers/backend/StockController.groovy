package backend

class StockController {

    def index() { 
        stockService.getStocks("Microsoft", 30)
    }

    def stockService

}
