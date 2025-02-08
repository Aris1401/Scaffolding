import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Unite } from './unite.model';

const baseUrl = 'http://localhost:8080/api/v1/unites'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class UniteService {

  constructor(

    private http: HttpClient
  ) { }

  getUniteById(id : string): Observable<Unite> {
    return this.http.get<Unite>(`${baseUrl}/${id}`)
  }

  getUnites(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getUnitesPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createUnite(unite: Unite): Observable<Unite> {
      const {utId, ...uniteWithoutId} = unite;

      return this.http.post<Unite>(baseUrl, uniteWithoutId);
  }

  updateUnite(id: string, unite: Unite): Observable<Unite> {

    return this.http.put<Unite>(`${baseUrl}/${id}`, unite);
  }

   deleteUnite(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
