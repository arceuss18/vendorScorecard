import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { VendorService } from '../vendor.service';
import { SnackBarComponent } from '../snackbar.component'
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  submitted: boolean;
  vendorList: any[];
  postResult: any[];
  // vendorScoreMonth: any;
  vendor: any[] = [
    { id: 1, name: 'vendor1' },
    { id: 2, name: 'vendor2' },
    { id: 3, name: 'vendor3' },
    { id: 4, name: 'vendor4' },
    { id: 5, name: 'vendor5' }
  ];
  month: any[] = [
    { id: 1, name: 'January' },
    { id: 2, name: 'February' },
    { id: 3, name: 'March' },
    { id: 4, name: 'April' },
    { id: 5, name: 'May' },
    { id: 6, name: 'June' },
    { id: 7, name: 'July' },
    { id: 8, name: 'August' },
    { id: 9, name: 'September' },
    { id: 10, name: 'October' },
    { id: 11, name: 'November' },
    { id: 12, name: 'December' }
  ];

  year: any[] = [
    { id: 1, name: '2015' },
    { id: 2, name: '2016' },
    { id: 3, name: '2017' },
    { id: 4, name: '2018' },
    { id: 5, name: '2019' },
  ];

  registerForm = this.fb.group({
    username: ['', Validators.compose([Validators.minLength(2)])],
    userEmail: ['', Validators.compose([Validators.required, Validators.pattern(/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/)])],
    vendorname: ['', Validators.required],
    vendorScore: ['', Validators.compose([Validators.required, Validators.pattern(/^0?[1-5]{1}(\.\d{1,2})?$/)])],
    month: ['', Validators.required],
    year: ['', Validators.required],
    vendorScoreMonth: ['']
  });

  constructor(private snackBar: MatSnackBar, private fb: FormBuilder, private vendorService: VendorService) { }

  ngOnInit() {
    this.getVendors();
  }

  getVendors(): void {
    this.vendorService.getVendor().subscribe((data: any[]) => {
      console.log(data);
      this.vendorList = data;
    });

  }

  onSubmit() {
    console.warn(this.registerForm.value);
    console.log('onSubmit called');
    this.vendorService.addVendor(this.registerForm.value).subscribe((data) => {
      console.log('from onsubmit', data);
      this.postResult = data.message;
      if (this.postResult != undefined) {
        // alert(this.postResult)
        this.showSnackbar(this.postResult);
        if (data.status == 'SUCCESS') {
          this.resetFormValues();
        }
      }
    });

  }

  resetFormValues() {
    this.registerForm.reset();
  }

  beforeSubmit() {
    var date = new Date(this.registerForm.controls.month.value + '-' + '01' + '-' + this.registerForm.controls.year.value);
    this.registerForm.controls.vendorScoreMonth.setValue(date.toDateString())
    // var date = new  Date (this.vendorScoreMonth);
    // console.log(date)
  }

  showSnackbar(result: any) {
    console.log('from snackbar', result)
    this.snackBar.openFromComponent(SnackBarComponent, {
      duration: 5000,
      data: { message: result }
    });

  }

}



