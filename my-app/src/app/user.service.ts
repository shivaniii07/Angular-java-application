import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  private baseUrl='http://localhost:8080/api/v1/Users';
  constructor(private httpClient:HttpClient) { }
  getUserList():Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.baseUrl}`);
  }

  createUser(user:User): Observable<object>{
    return this.httpClient.post(`${this.baseUrl}`,user);
  }

  getUserById(id:number): Observable<User>{
    return this.httpClient.get<User>(`${this.baseUrl}/${id}`);
  }

  updateUser(id:number,user:User):Observable<object>{
    return this.httpClient.put(`${this.baseUrl}/${id}`,user);
  }

  deleteUser(id:number):Observable<object>{
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }
}
