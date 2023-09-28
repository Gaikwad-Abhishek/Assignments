import { Component } from '@angular/core';
import { CycleService } from '../app.service';

@Component({
  selector: 'app-add-cycles',
  templateUrl: './add-cycles.component.html',
  styleUrls: ['./add-cycles.component.scss']
})
export class AddCyclesComponent {
  constructor( private cycleService :CycleService){}

  newCycle: any = {};
  onSubmit():any {
    this.cycleService.addCycle(this.newCycle).subscribe(
      (response : any) => {
        console.log('Cycle added successfully:', response);

        this.newCycle = {}; 

      },
  );
}}
