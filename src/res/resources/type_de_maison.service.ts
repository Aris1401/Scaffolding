import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Type_de_maison } from './type_de_maison.model';

const baseUrl = 'http://localhost:8080/api/v1/type_de_maisons'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class Type_de_maisonService {

  constructor(

    private http: HttpClient
  ) { }

  getType_de_maisonById(id : string): Observable<Type_de_maison> {
    return this.http.get<Type_de_maison>(`${baseUrl}/${id}`)
  }

  getType_de_maisons(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getType_de_maisonsPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createType_de_maison(type_de_maison: Type_de_maison): Observable<Type_de_maison> {
      const {tmId, ...type_de_maisonWithoutId} = type_de_maison;

      return this.http.post<Type_de_maison>(baseUrl, type_de_maisonWithoutId);
  }

  updateType_de_maison(id: string, type_de_maison: Type_de_maison): Observable<Type_de_maison> {

    return this.http.put<Type_de_maison>(`${baseUrl}/${id}`, type_de_maison);
  }

   deleteType_de_maison(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
