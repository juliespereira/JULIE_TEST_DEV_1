package backend

class BootStrap {

    def init = { servletContext ->
        def microsoft = new Company (name: 'Microsoft', segment: 'Tecnology').save()
        def tesla = new Company (name: 'Tesla', segment: 'Vehicle').save()
        def mcDonalds = new Company (name: 'McDonalds', segment: 'Food').save()

        //Tempo em milissegundos do momento da execução do sistema
        Timestamp tsAtual = new Timestamp(System.currentTimeMillis())

        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(tsAtual.getTime())

        cl.set(Calendar.DATE, tsAtual.getDay() - 31)

        Timestamp tsInicial = new Timestamp(System.currentTimeMillis())
        tsInicial.setTime(cl.getTimeInMillis())

        tsInicial.setHours(10)
        tsInicial.setMinutes(0)

        Random rn = new Random()

        for(int i = 30; i > 0; i--){

            while (tsInicial.getHours() < 18) {

                int rand = rn.nextInt(range) + min
                int rand1 = rn.nextInt(range) + min
                int rand2 = rn.nextInt(range) + min

                Stock stock = new Stock(price: rand, priceDate: tsInicial)
                stock.comp = microsoft
                stock.save(failOnError: true)
                Stock stock1 = new Stock(price: rand1, priceDate: tsInicial)
                stock1.comp = tesla;
                stock1.save(failOnError: true)
                Stock stock2 = new Stock(price: rand2, priceDate: tsInicial)
                stock2.comp = mcDonalds
                stock2.save(failOnError: true)

                tsInicial.setMinutes(tsInicial.getMinutes() + 1)

            }

            cl.setTimeInMillis(tsAtual.getTime())
            cl.set(Calendar.DATE, tsAtual.getDay() - i)

            tsInicial.setTime(cl.getTimeInMillis())

            tsInicial.setHours(10)
            tsInicial.setMinutes(0)

        }

    }
    def destroy = {
    }
}
