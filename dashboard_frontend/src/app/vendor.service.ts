import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';


const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class VendorService {

    private apiUrl = 'http://localhost:8080/postDetails';
    private getVendorList = 'http://localhost:8080/allvendors'; // URL to web api
    private searchByVendorName = 'http://localhost:8080/vendor';
    private searchByUserName = 'http://localhost:8080/user';
    private searchByMonth = 'http://localhost:8080/month';
    private update = 'http://localhost:8080/update';
    private delete = 'http://localhost:8080/deleteVendor';
    // let requestOptions = new RequestOptions({ headers:null, withCredentials: 
    //     true });

    vendorDetails: any[]

    constructor(private http: HttpClient) { }

    addVendor(vendorDetails: any[]): Observable<any>  {


        return this.http.post(this.apiUrl, vendorDetails, httpOptions).pipe(
            tap((result) => console.log('result-->', result))
        );
    }

    getVendor(): Observable<any> {
        return this.http.get(this.getVendorList).pipe(

        );
    }

    searchByUsername(userName: string): Observable<any>{
        const url = `${this.searchByUserName}/${userName}`;
        return this.http.get(url).pipe(
            tap((result) => console.log('result-->', result))
        );

    }

    searchByVendorname(vendorName: string): Observable<any>{
        const url = `${this.searchByVendorName}/${vendorName}`;
        return this.http.get(url).pipe(
            tap((result) => console.log('result-->', result))
        );

    }

    searchByYear(date:any): Observable<any>{
        const url = `${this.searchByMonth}/${date}`;
        return this.http.get(url).pipe(
            tap((result) => console.log('result-->', result))
        );
    }

    deleteVendor(id: any): Observable<any>{
        const url = `${this.delete}/${id}`;
        return this.http.delete(url).pipe(
            tap((result) => console.log('result-->', result))
        );

    }

    updateVendor(details): Observable<any>{
        // console.log('from update API', details)
        var id = details.id;
        const url = `${this.update}/${id}`;
        return this.http.put(url, details).pipe(
            tap((result) => console.log('result-->', result))
        );

    }

    setDetails(details){
        this.vendorDetails = details;   
       }
   
       getDetails(){
          return this.vendorDetails;
          }
   

}