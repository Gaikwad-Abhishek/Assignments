import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCyclesComponent } from './add-cycles.component';

describe('AddCyclesComponent', () => {
  let component: AddCyclesComponent;
  let fixture: ComponentFixture<AddCyclesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddCyclesComponent]
    });
    fixture = TestBed.createComponent(AddCyclesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
