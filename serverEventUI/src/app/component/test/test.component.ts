import { Component, OnInit } from '@angular/core';
import { TestService } from '../../services/test.service';
import { Employee } from '../../model/Employee';


@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  resp: Employee;
  message: String;

  constructor(private testService: TestService) { }

  ngOnInit() {
    this.resp = new Employee();
    this.getBooking();
  }

  getBooking(): void {
    this.testService.getDataFromServer().subscribe(employeeResp => {
      this.resp = employeeResp;
    });
  }

  purchase() {
    this.testService.purchase().subscribe(resp => {
      this.message = resp;
    });
  }
}
