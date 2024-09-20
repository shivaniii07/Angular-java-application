import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit { 
  users:any[]=[];

  constructor(private userService:UserService,
    private router:Router){
    this.userService.getUserList().subscribe((res)=>{
      console.log(res);
      
    })
   }

  ngOnInit(): void{
     this.getUsers();
  }
   private getUsers(){
    this.userService.getUserList().subscribe(data =>{
      this.users=data;
      console.log(data);
    });
   }

   updateUser(id:number){
     this.router.navigate(['update-user',id]);
   }

   deleteUser(id:number){
      this.userService.deleteUser(id).subscribe(data =>{
        console.log(data);
        this.getUsers();
      })
   }
}
    
