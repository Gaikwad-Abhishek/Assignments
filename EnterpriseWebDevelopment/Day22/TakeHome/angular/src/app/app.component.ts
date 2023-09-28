import { Component, OnInit } from '@angular/core';
import { CycleService } from './app.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent  {
  title = 'api data';
  newData:any;
  borrowedCycles:any;
  showData = false;
  showBorrowedData = false;
  constructor(private cycleService: CycleService,private http:HttpClient) {}

  ngOnInit()
  {
    // this.cycleService.getCycles().subscribe(res=>{
    //   this.newData=res;
    // })
  
    // this.cycleService.getBorrowedCycles().subscribe(res=>{
    //   this.borrowedCycles=res;
    // })

   
  }

  // toggleDataVisibility() {
  //   this.showData = !this.showData;
  // }
  
  // toggleBorrowedCyclesVisibility() {
  //   this.showBorrowedData = !this.showBorrowedData;
  // }
  

  // borrowCycle(id: number) {


  //   this.cycleService.borrowCycles(id).subscribe(

  //     (response: any) => {

  //       console.log('PUT request successful:', response);
  //       this.ngOnInit();
  //     },

  //        );
  
  // }

  // returnCycle(id:number)
  // {
  //   // console.log(id);
  //   this.cycleService.returnCycles(id).subscribe
  //   (
  //     (Response:any) =>
  //     {
  //       console.log('push',Response);
  //       this.ngOnInit();
  //     },
  //   );


  // }

  // newCycle: any = {};
  // onSubmit():any {
  //   this.cycleService.addCycle(this.newCycle).subscribe(
  //     (response : any) => {
  //       console.log('Cycle added successfully:', response);

  //       this.newCycle = {}; 
  //       this.ngOnInit();

  //     },
  // );
    //}
}