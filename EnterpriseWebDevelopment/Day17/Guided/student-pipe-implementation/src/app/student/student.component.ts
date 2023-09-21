import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {
  students = [
    {
      id: 1,
      name: 'Gaurav',
      dob: '1998-05-15',
      course: 'Computer Science',
      fees: 5000
    },
    {
      id: 2,
      name: 'Ankush',
      dob: '2000-02-20',
      course: 'Electrical Engineering',
      fees: 1500
    },
    {
      id: 3,
      name: 'Bibhu',
      dob: '1999-11-10',
      course: 'Mechanical Engineering',
      fees: 2200
    },
    {
      id: 4,
      name: 'Abhishek',
      dob: '2001-07-25',
      course: 'Civil Engineering',
      fees: 8300
    }
  ];

  constructor() {}

  ngOnInit() {}
}
