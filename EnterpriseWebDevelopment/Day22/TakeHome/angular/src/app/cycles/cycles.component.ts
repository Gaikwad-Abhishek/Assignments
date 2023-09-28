import { Component, OnInit } from '@angular/core';
import { CycleService } from '../app.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-cycles',
  templateUrl: './cycles.component.html',
  styleUrls: ['./cycles.component.scss']
})
export class CyclesComponent implements OnInit{
  cycles: any;
  showCycles: boolean = false;
  constructor( private cycleService :CycleService, private authService:AuthService,private router:Router){
  }
  
  ngOnInit() {
    console.log("cycle-data");
    this.cycleService.getCycles().subscribe((res: any) => {
      this.cycles = res;
    });
  }


  toggleDataVisibility() {
    this.showCycles = !this.showCycles;
    this.ngOnInit();
  }

  borrowCycle(id: number, count: string) {


    this.cycleService.addToCart(id,parseInt(count)).subscribe(

      (response: any) => {

        console.log('PUT request successful:', response);
        // this.cycleService.setData(response);
        this.ngOnInit();
      },

      );
  
  }


}
