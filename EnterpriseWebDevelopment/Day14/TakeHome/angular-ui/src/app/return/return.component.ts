import { Component } from '@angular/core';
import { CycleService } from '../cycle.service';

@Component({
  selector: 'app-return',
  templateUrl: './return.component.html',
  styleUrls: ['./return.component.css']
})
export class ReturnComponent {
  returnData = { id: '', count: 0 };
  returnResult = '';
  cycles: any[] = [];
  constructor(private cycleService: CycleService) {}

  ngOnInit(): void {
    this.getCycles();
  }

  getCycles(): void {
    this.cycleService.listAvailableCycles()
      .subscribe(cycles => this.cycles = cycles);
  }

  returnCycle(cycle:any) {
    this.returnData.id = cycle.id;
    this.returnData.count = cycle.countToReturn;
    this.cycleService.returnCycle(this.returnData).subscribe(
      (response) => {
        this.returnResult = 'Cycle returned successfully.';
        this.ngOnInit();
      },
      (error) => {
        if (error.status === 404) {
          this.returnResult = 'Cycle not found.';
        } else {
          this.returnResult = 'An error occurred.';
        }
      }
    );
  }
}
