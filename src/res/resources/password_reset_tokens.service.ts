import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Password_reset_tokens } from './password_reset_tokens.model';

const baseUrl = 'http://localhost:8080/api/v1/password_reset_tokenss'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class Password_reset_tokensService {

  constructor(

    private http: HttpClient
  ) { }

  getPassword_reset_tokensById(id : string): Observable<Password_reset_tokens> {
    return this.http.get<Password_reset_tokens>(`${baseUrl}/${id}`)
  }

  getPassword_reset_tokenss(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getPassword_reset_tokenssPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createPassword_reset_tokens(password_reset_tokens: Password_reset_tokens): Observable<Password_reset_tokens> {
      const {email, ...password_reset_tokensWithoutId} = password_reset_tokens;

      return this.http.post<Password_reset_tokens>(baseUrl, password_reset_tokensWithoutId);
  }

  updatePassword_reset_tokens(id: string, password_reset_tokens: Password_reset_tokens): Observable<Password_reset_tokens> {

    return this.http.put<Password_reset_tokens>(`${baseUrl}/${id}`, password_reset_tokens);
  }

   deletePassword_reset_tokens(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
