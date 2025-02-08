import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Mois } from './mois.model';

const baseUrl = 'http://localhost:8080/api/v1/moiss'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class MoisService {

  constructor(

    private http: HttpClient
  ) { }

  getMoisById(id : string): Observable<Mois> {
    return this.http.get<Mois>(`${baseUrl}/${id}`)
  }

  getMoiss(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getMoissPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createMois(mois: Mois): Observable<Mois> {
      const {mId, ...moisWithoutId} = mois;

      return this.http.post<Mois>(baseUrl, moisWithoutId);
  }

  updateMois(id: string, mois: Mois): Observable<Mois> {

    return this.http.put<Mois>(`${baseUrl}/${id}`, mois);
  }

   deleteMois(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
