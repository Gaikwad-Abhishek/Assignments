import { Component, OnInit } from '@angular/core';
import { CycleService } from '../cycle.service';

@Component({
  selector: 'app-list-cycles',
  templateUrl: './list-cycles.component.html',
  styleUrls: ['./list-cycles.component.css']
})
export class ListCyclesComponent implements  OnInit {
  cycles: any[] = [];

  constructor(private cycleService: CycleService) {}

  ngOnInit() {
    this.getCycles();
  }

  getCycles() {
    this.cycleService.listAvailableCycles().subscribe((data) => {
      this.cycles = data;
    });
  }
}
