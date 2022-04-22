package backend

import java.sql.Timestamp
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import java.time.chrono.Chronology
import java.time.chrono.IsoChronology
import java.time.temporal.ChronoUnit

class BootStrap {

    def init = { servletContext ->
        def microsoft = new Company (name: 'Microsoft', segment: 'Tecnology').save()
        def tesla = new Company (name: 'Tesla', segment: 'Vehicle').save()
        def mcDonalds = new Company (name: 'McDonalds', segment: 'Food').save()

        //Tempo em milissegundos do momento da execução do sistema
        Timestamp tsNow = new Timestamp(System.currentTimeMillis())

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(tsNow.getTime())

        calendar.set(Calendar.DATE, tsNow.getDay() - 31)

        Timestamp tsActual = new Timestamp(System.currentTimeMillis())
        tsActual.setTime(calendar.getTimeInMillis())

        tsActual.setHours(10)
        tsActual.setMinutes(0)

        Random random = new Random()

        for(int i = 30; i > 0; i--){

            while (tsActual.getHours() < 18) {

                int rand = random.nextInt(range) + min
                int rand1 = random.nextInt(range) + min
                int rand2 = random.nextInt(range) + min

                Stock stock = new Stock(price: rand, priceDate: tsActual)
                stock.comp = microsoft
                stock.save(failOnError: true)
                Stock stock1 = new Stock(price: rand1, priceDate: tsActual)
                stock1.comp = tesla;
                stock1.save(failOnError: true)
                Stock stock2 = new Stock(price: rand2, priceDate: tsActual)
                stock2.comp = mcDonalds
                stock2.save(failOnError: true)

                tsActual.setMinutes(tsActual.getMinutes() + 1)

            }

            calendar.setTimeInMillis(tsNow.getTime())
            calendar.set(Calendar.DATE, tsNow.getDay() - i)

            tsActual.setTime(calendar.getTimeInMillis())

            tsActual.setHours(10)
            tsActual.setMinutes(0)

        }

    }
    def destroy = {
    }
}
