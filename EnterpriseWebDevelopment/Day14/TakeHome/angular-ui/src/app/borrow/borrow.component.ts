import { Component, OnInit } from '@angular/core';
import { CycleService } from '../cycle.service';  

@Component({
  selector: 'app-borrow',
  templateUrl: './borrow.component.html',
  styleUrls: ['./borrow.component.css']
})
export class BorrowComponent implements OnInit {
  borrowData = { id: '', count: 1 };
  borrowResult = '';
  cycles: any[] = [];
  constructor(private cycleService: CycleService) {}

  ngOnInit(): void {
    this.getCycles();
  }

  getCycles(): void {
    this.cycleService.listAvailableCycles()
      .subscribe(cycles => this.cycles = cycles);
  }

  borrowCycle(cycle:any) {
    this.borrowData.id = cycle.id;
    this.borrowData.count = cycle.countToBorrow;
    this.cycleService.borrowCycle(this.borrowData).subscribe(
      (response) => {
        this.borrowResult = 'Cycle borrowed successfully.';
        this.ngOnInit();
      },
      (error) => {
        if (error.status === 404) {
          this.borrowResult = 'Cycle not found.';
        } else if (error.status === 400) {
          this.borrowResult = 'Insufficient stock.';
        } else {
          this.borrowResult = 'An error occurred.';
        }
      }
    );
  }
}
