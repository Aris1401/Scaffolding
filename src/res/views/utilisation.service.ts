import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Utilisation } from './utilisation.model';

import { Immobilier } from './immobilier.model'
import { ImmobilierService } from './immobilier.service'

const baseUrl = 'http://localhost:8080/api/v1/utilisations'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class UtilisationService {

  constructor(

    private immobilierService : ImmobilierService,
    private http: HttpClient
  ) { }

  getUtilisationById(id : string): Observable<Utilisation> {
    return this.http.get<Utilisation>(`${baseUrl}/${id}`)
  }

  getUtilisations(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getUtilisationsPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createUtilisation(utilisation: Utilisation): Observable<Utilisation> {
      const {idUtilisation, ...utilisationWithoutId} = utilisation;

      const idImmobilier$ = this.immobilierService.getImmobilierById(utilisationWithoutId.idImmobilier);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          idImmobilier$.pipe(
              tap((data) => {
                  utilisationWithoutId.immobilier = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(utilisationWithoutId);
              return this.http.post<Utilisation>(baseUrl, utilisationWithoutId);
          })
      );

  }

  updateUtilisation(id: string, utilisation: Utilisation): Observable<Utilisation> {

      const idImmobilier$ = this.immobilierService.getImmobilierById(utilisation.idImmobilier);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          idImmobilier$.pipe(
              tap((data) => {
                  utilisation.immobilier = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(utilisation);
              return this.http.post<Utilisation>(baseUrl, utilisation);
          })
      );

  }

   deleteUtilisation(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
