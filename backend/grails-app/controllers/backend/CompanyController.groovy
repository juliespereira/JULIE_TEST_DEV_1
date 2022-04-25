package backend

class CompanyController {

    def index() { 
        render companyService.getCompanies()
    }

    def companyService
}
