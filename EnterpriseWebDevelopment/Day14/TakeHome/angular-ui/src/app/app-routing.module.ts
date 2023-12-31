import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListCyclesComponent } from './list-cycles/list-cycles.component';
import { BorrowComponent } from './borrow/borrow.component';
import { ReturnComponent } from './return/return.component';
import { RestockComponent } from './restock/restock.component';

const routes: Routes = [
  { path: '', redirectTo: '/list-data', pathMatch: 'full' },
  { path: 'list-data', component: ListCyclesComponent },
  { path: 'borrow', component: BorrowComponent },
  { path: 'return', component: ReturnComponent },
  { path: 'restock', component: RestockComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
