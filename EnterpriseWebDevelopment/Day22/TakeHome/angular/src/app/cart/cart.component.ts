import { Component, OnInit } from '@angular/core';
import { CycleService } from '../app.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  cartItems: any[] = []; 
  totalAmount: number = 0; 

  constructor(private cycleService: CycleService) {}

  ngOnInit(): void {
    this.cycleService.getCartData().subscribe((data: any[]) => {
      this.cartItems = data;
      this.calculateTotalAmount(); 
    });
  }

  calculateTotalAmount(): void {
    this.totalAmount = this.cartItems.reduce(
      (total, item) => total + item.cycle.price * item.quantity,
      0
    );
  }

  checkout(totalAmount:number): void {
  this.cycleService.checkout(totalAmount).subscribe(

    (response: any) => {

      console.log('PUT request successful:', response);
      this.totalAmount = 0;
      this.ngOnInit();
    },

    );  
  }

  removeCartItem(cycleId:number,cycleQuantity:number, count:string): void {
    console.log(cycleId);
    const cycleCount = parseInt(count);
    if (cycleCount <= cycleQuantity) {
      this.cycleService.removeCartItem(cycleId,parseInt(count)).subscribe(

        (response: any) => {
    
          console.log('PUT request successful:', response);
          this.ngOnInit();
        },
    
        );
    } else {
      //Invaild count selection notify user. 
    }
    
  }
}
