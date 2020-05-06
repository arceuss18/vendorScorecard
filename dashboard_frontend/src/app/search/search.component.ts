import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { VendorService } from '../vendor.service';
import { DialogComponent } from '../dialog.component';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { Router } from '@angular/router';
import { SnackBarComponent } from '../snackbar.component'
import { MatSnackBar } from '@angular/material';
declare var tableau: any;
declare var tableauSoftware: any;
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  userName: string;
  vendorName: string;
  email: string;
  score: any;
  date: Date;
  showMonth: any;
  showYear: any;
  selectedMonth: string;
  selectedYear: any;
  details: any[];
  id: any;
  isSearch = false;
  message: any;
  updateDetails: any[];
  updatedName: string;
  updatedVendor: string;
  updatedScore: any;
  viz: any;
  vendorList: any[] = [
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

  // updateForm = this.fb.group({
  //   username: ['', Validators.compose([Validators.minLength(2)])],
  //   email: ['', Validators.compose([Validators.required, Validators.pattern(/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/)])],
  //   vendor: ['', Validators.required],
  //   score: ['', Validators.required],
  //   date: ['', Validators.required],
  // });


  constructor(
    private snackBar: MatSnackBar,
    public dialog: MatDialog,
    private vendorService: VendorService,
    private fb: FormBuilder,
    private router: Router
  ) {

  }

  ngOnInit() {
    // var placeholderDiv = document.getElementById('vizContainer');
    // var url = "https://tableau-stg.netapp.com";
    // var options = {
    //   hideTabs: true,
    //   width: "80%",
    //   height: "500px",
    //   onFirstInteractive: function () {
    //     // The viz is now ready and can be safely used.
    //     console.log("Run this code when the viz has finished     loading.");
    //   }
    // };
    // this.viz = new tableauSoftware.Viz(placeholderDiv, url, options);
  }

  openDialog(id: any): void {
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '400px',
      data: { dataId: id }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.id = result;
      if (this.id != undefined) {
        this.deleteVendor(this.id)
      }

    });
  }

  searchVendorDetails() {
    this.isSearch = true;
    if (this.vendorName != undefined) {
      this.vendorService.searchByVendorname(this.vendorName).subscribe((data) => {
        console.log('search by vendor', data, this.vendorName);
        for (let obj of data) {
          var dt = obj.vendorScoreMonth;
          dt = dt.split(' ');
          obj['showMonth'] = dt[1]
          obj['showYear'] = dt[3]
        }
        this.details = data;
        console.log(this.details);
      });
    }
    else if (this.userName != undefined) {
      this.vendorService.searchByUsername(this.userName).subscribe((data) => {
        for (let obj of data) {
          var dt = obj.vendorScoreMonth;
          dt = dt.split(' ');
          obj['showMonth'] = dt[1]
          obj['showYear'] = dt[3]
        }
        this.details = data;
        console.log(this.details);
      });
    }
    else {
      var date = new Date(this.selectedMonth + '-' + '01' + '-' + this.selectedYear);
      var getByMonth = date.toDateString();
      this.vendorService.searchByYear(getByMonth).subscribe((data) => {
        console.log('search by month', data);
        for (let obj of data) {
          var dt = obj.vendorScoreMonth;
          dt = dt.split(' ');
          obj['showMonth'] = dt[1]
          obj['showYear'] = dt[3]
        }
        this.details = data;
        console.log(this.details);
      });
    }
    this.resetValues();
  }

  updateVendor(details) {
    // this.isSearch = false;
    // this.isUpdate = true;
    // localStorage.setItem('vendorDetails', details);

    // console.log('update id', id)
    // var selectedVendor = this.details.filter(
    //   item => item.id == id)
    // console.log('filter details', selectedVendor);
    // this.updateDetails = selectedVendor
    // console.log('update details', this.updateDetails);
    // this.vendorService.updateVendor(details).subscribe((data: any[]) => {
    //   this.updateDetails = data;
    //   console.log('update', this.updateDetails);
    // });
    this.vendorService.setDetails(details);
    this.router.navigate(['/update']);

  }

  resetValues() {
    this.userName = '';
    this.vendorName = '';
    this.selectedMonth = '';
    this.selectedYear = '';
  }

  deleteVendor(id: any) {
    this.vendorService.deleteVendor(id).subscribe((data) => {
      this.message = data.message;
      this.details = this.details.filter(detail => detail.id !== id);
      this.showSnackbar(this.message);
    });
  }

  showSnackbar(result: any) {
    this.snackBar.openFromComponent(SnackBarComponent, {
      duration: 5000,
      data: { message: result }
    });
  }

}
