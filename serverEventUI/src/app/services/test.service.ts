import { NgZone, Injectable, ChangeDetectorRef } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap } from 'rxjs/operators';
import { Employee } from '../model/Employee';

@Injectable()
export class TestService {

  eventSource: any = window['EventSource'];

  constructor(private ngZone: NgZone, private http: HttpClient) { }

  getDataFromServer(): Observable<any> {
    return new Observable<any>(obs => {

        const eventSource = new this.eventSource('http://localhost:8080/emp/employee');

        eventSource.onmessage = event => {

            let data = JSON.parse(event.data);

            // $apply external (window.EventSource) event data
            this.ngZone.run(() => obs.next(data));
        };
        // close connection when observer unsubscribe
        return () => eventSource.close();
    });
  }

  purchase(): Observable<any> {
    return this.http.get<boolean>('http://localhost:8080/emp/producer?empName=DummyName&empId=102').pipe(
      map(purchaseResp => purchaseResp)
    );
  }

}
