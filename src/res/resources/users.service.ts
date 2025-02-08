import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Users } from './users.model';

const baseUrl = 'http://localhost:8080/api/v1/userss'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(

    private http: HttpClient
  ) { }

  getUsersById(id : string): Observable<Users> {
    return this.http.get<Users>(`${baseUrl}/${id}`)
  }

  getUserss(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getUserssPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createUsers(users: Users): Observable<Users> {
      const {id, ...usersWithoutId} = users;

      return this.http.post<Users>(baseUrl, usersWithoutId);
  }

  updateUsers(id: string, users: Users): Observable<Users> {

    return this.http.put<Users>(`${baseUrl}/${id}`, users);
  }

   deleteUsers(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
