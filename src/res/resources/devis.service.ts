import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Devis } from './devis.model';

import { TypeDeMaison } from './typeDeMaison.model'
import { TypeDeMaisonService } from './typeDeMaison.service'

const baseUrl = 'http://localhost:8080/api/v1/deviss'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class DevisService {

  constructor(

    private typeDeMaisonService : TypeDeMaisonService,
    private http: HttpClient
  ) { }

  getDevisById(id : string): Observable<Devis> {
    return this.http.get<Devis>(`${baseUrl}/${id}`)
  }

  getDeviss(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getDevissPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createDevis(devis: Devis): Observable<Devis> {
      const {dId, ...devisWithoutId} = devis;

      const dTypeDeMaison$ = this.typeDeMaisonService.getTypeDeMaisonById(devisWithoutId.dTypeDeMaison);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          dTypeDeMaison$.pipe(
              tap((data) => {
                  devisWithoutId.typeDeMaison = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(devisWithoutId);
              return this.http.post<Devis>(baseUrl, devisWithoutId);
          })
      );

  }

  updateDevis(id: string, devis: Devis): Observable<Devis> {

      const dTypeDeMaison$ = this.typeDeMaisonService.getTypeDeMaisonById(devis.dTypeDeMaison);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          dTypeDeMaison$.pipe(
              tap((data) => {
                  devis.typeDeMaison = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(devis);
              return this.http.post<Devis>(baseUrl, devis);
          })
      );

  }

   deleteDevis(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
