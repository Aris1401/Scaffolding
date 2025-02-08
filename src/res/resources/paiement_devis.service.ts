import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Paiement_devis } from './paiement_devis.model';

import { RealisationTravaux } from './realisationTravaux.model'
import { RealisationTravauxService } from './realisationTravaux.service'

const baseUrl = 'http://localhost:8080/api/v1/paiement_deviss'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class Paiement_devisService {

  constructor(

    private realisationTravauxService : RealisationTravauxService,
    private http: HttpClient
  ) { }

  getPaiement_devisById(id : string): Observable<Paiement_devis> {
    return this.http.get<Paiement_devis>(`${baseUrl}/${id}`)
  }

  getPaiement_deviss(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getPaiement_devissPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createPaiement_devis(paiement_devis: Paiement_devis): Observable<Paiement_devis> {
      const {pdId, ...paiement_devisWithoutId} = paiement_devis;

      const pdIdRealisationTravaux$ = this.realisationTravauxService.getRealisationTravauxById(paiement_devisWithoutId.pdIdRealisationTravaux);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          pdIdRealisationTravaux$.pipe(
              tap((data) => {
                  paiement_devisWithoutId.realisationTravaux = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(paiement_devisWithoutId);
              return this.http.post<Paiement_devis>(baseUrl, paiement_devisWithoutId);
          })
      );

  }

  updatePaiement_devis(id: string, paiement_devis: Paiement_devis): Observable<Paiement_devis> {

      const pdIdRealisationTravaux$ = this.realisationTravauxService.getRealisationTravauxById(paiement_devis.pdIdRealisationTravaux);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          pdIdRealisationTravaux$.pipe(
              tap((data) => {
                  paiement_devis.realisationTravaux = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(paiement_devis);
              return this.http.post<Paiement_devis>(baseUrl, paiement_devis);
          })
      );

  }

   deletePaiement_devis(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
