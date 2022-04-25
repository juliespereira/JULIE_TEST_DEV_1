import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CompanyDto } from 'src/app/model/companyDto.model';
import { Observable } from 'rxjs';


@Injectable({
    providedIn: 'root'
})

export class CompanyService {
    constructor(private httpClient: HttpClient) {
    }

    public list(): Observable<CompanyDto[]> {
        return this.httpClient.get<CompanyDto[]>('http://localhost:8080/company/index');
    }
}