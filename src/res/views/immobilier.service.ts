import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Immobilier } from './immobilier.model';

const baseUrl = 'http://localhost:8080/api/v1/immobiliers'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class ImmobilierService {

  constructor(

    private http: HttpClient
  ) { }

  getImmobilierById(id : string): Observable<Immobilier> {
    return this.http.get<Immobilier>(`${baseUrl}/${id}`)
  }

  getImmobiliers(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getImmobiliersPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createImmobilier(immobilier: Immobilier): Observable<Immobilier> {
      const {idImmobilier, ...immobilierWithoutId} = immobilier;

      return this.http.post<Immobilier>(baseUrl, immobilierWithoutId);
  }

  updateImmobilier(id: string, immobilier: Immobilier): Observable<Immobilier> {

    return this.http.put<Immobilier>(`${baseUrl}/${id}`, immobilier);
  }

   deleteImmobilier(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
