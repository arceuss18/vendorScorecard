import { Component, OnInit } from '@angular/core';
import { VendorService } from '../vendor.service';
import { SnackBarComponent } from '../snackbar.component'
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  updateDetails: any[];
  updateResult: any;
  isDisabled = true;
  vendorList: any[] = [
    { id: 1, name: 'vendor1' },
    { id: 2, name: 'vendor2' },
    { id: 3, name: 'vendor3' },
    { id: 4, name: 'vendor4' },
    { id: 5, name: 'vendor5' }
  ];
  month: any[] = [
    { id: 'Jan', name: 'January' },
    { id: 'Feb', name: 'February' },
    { id: 'Mar', name: 'March' },
    { id: 'Apr', name: 'April' },
    { id: 'May', name: 'May' },
    { id: 'Jun', name: 'June' },
    { id: 'Jul', name: 'July' },
    { id: 'Aug', name: 'August' },
    { id: 'Sept', name: 'September' },
    { id: 'Oct', name: 'October' },
    { id: 'Nov', name: 'November' },
    { id: 'Dec', name: 'December' }
  ];

  year: any[] = [
    { id: 1, name: '2015' },
    { id: 2, name: '2016' },
    { id: 3, name: '2017' },
    { id: 4, name: '2018' },
    { id: 5, name: '2019' },
  ];

  constructor( private vendorService: VendorService,  private snackBar: MatSnackBar,) { }

  ngOnInit() {
   this.updateDetails = this.vendorService.getDetails();
   console.log('from update oninit', this.updateDetails)
  }
  
  updateVendorDetails(){
  //  var details = localStorage.getItem('details');
  //  this.updateDetails = details;
   console.log(this.updateDetails)
  this.vendorService.updateVendor(this.updateDetails).subscribe((data) => {
    console.log('from update', data);
    this.updateResult = data.message;
    if(this.updateResult != undefined){
      // alert(this.postResult)
      this.showSnackbar(this.updateResult);
    }
  });
  this.resetValues();
  }


resetValues(){
  this.updateDetails = [];
  // this.showSnackbar();
}

showSnackbar(result: any){
  console.log('from snackbar', result)
this.snackBar.openFromComponent(SnackBarComponent, {
  duration: 5000,
  data: {message: result}
});

}

}
