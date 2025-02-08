import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Type_de_finition } from './type_de_finition.model';

const baseUrl = 'http://localhost:8080/api/v1/type_de_finitions'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class Type_de_finitionService {

  constructor(

    private http: HttpClient
  ) { }

  getType_de_finitionById(id : string): Observable<Type_de_finition> {
    return this.http.get<Type_de_finition>(`${baseUrl}/${id}`)
  }

  getType_de_finitions(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getType_de_finitionsPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createType_de_finition(type_de_finition: Type_de_finition): Observable<Type_de_finition> {
      const {tfId, ...type_de_finitionWithoutId} = type_de_finition;

      return this.http.post<Type_de_finition>(baseUrl, type_de_finitionWithoutId);
  }

  updateType_de_finition(id: string, type_de_finition: Type_de_finition): Observable<Type_de_finition> {

    return this.http.put<Type_de_finition>(`${baseUrl}/${id}`, type_de_finition);
  }

   deleteType_de_finition(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
