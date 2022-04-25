import { CompanyService } from './../../service/company.service';
import { Component, OnInit } from '@angular/core';
import { CompanyDto } from 'src/app/model/companyDto.model';

@Component({
    selector: 'app-table',
    templateUrl: './table.component.html',
    styleUrls: ['./table.component.scss']
})

export class TableComponent implements OnInit {

    companies: CompanyDto[];

    constructor(private service: CompanyService){

    }

    ngOnInit(){
            
    }

    loadCompanies() {
        this.service.list().subscribe(
          (companies: CompanyDto[]) => {
            this.companies = companies;
          }, (error: any) => {
            console.log( error);
          }
        );
    }

}