import { Component, OnInit } from '@angular/core';
import { CycleService } from '../cycle.service';

@Component({
  selector: 'app-restock',
  templateUrl: './restock.component.html',
  styleUrls: ['./restock.component.css']
})
export class RestockComponent implements OnInit {
  restockData = { id: '', count: 0 };
  restockResult = '';
  cycles: any[] = [];
  constructor(private cycleService: CycleService) {}

  ngOnInit(): void {
    this.getCycles();
  }

  getCycles(): void {
    this.cycleService.listAvailableCycles()
      .subscribe(cycles => this.cycles = cycles);
  }

  restockCycle(cycle:any) {
    this.restockData.id = cycle.id;
    this.restockData.count = cycle.countToRestock;
    
    this.cycleService.restockCycle(this.restockData).subscribe(
      (response) => {
        this.restockResult = 'Cycle restocked successfully.';
        this.ngOnInit();
      },
      (error) => {
        if (error.status === 404) {
          this.restockResult = 'Cycle not found.';
        } else {
          this.restockResult = 'An error occurred.';
        }
      }
    );
  }
}
