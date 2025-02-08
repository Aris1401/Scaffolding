import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Type_de_travaux } from './type_de_travaux.model';

const baseUrl = 'http://localhost:8080/api/v1/type_de_travauxs'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class Type_de_travauxService {

  constructor(

    private http: HttpClient
  ) { }

  getType_de_travauxById(id : string): Observable<Type_de_travaux> {
    return this.http.get<Type_de_travaux>(`${baseUrl}/${id}`)
  }

  getType_de_travauxs(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getType_de_travauxsPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createType_de_travaux(type_de_travaux: Type_de_travaux): Observable<Type_de_travaux> {
      const {ttId, ...type_de_travauxWithoutId} = type_de_travaux;

      return this.http.post<Type_de_travaux>(baseUrl, type_de_travauxWithoutId);
  }

  updateType_de_travaux(id: string, type_de_travaux: Type_de_travaux): Observable<Type_de_travaux> {

    return this.http.put<Type_de_travaux>(`${baseUrl}/${id}`, type_de_travaux);
  }

   deleteType_de_travaux(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
