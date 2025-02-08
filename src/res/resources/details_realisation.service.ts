import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Details_realisation } from './details_realisation.model';

import { RealisationTravaux } from './realisationTravaux.model'
import { RealisationTravauxService } from './realisationTravaux.service'
import { TypeDeTravaux } from './typeDeTravaux.model'
import { TypeDeTravauxService } from './typeDeTravaux.service'
import { Unite } from './unite.model'
import { UniteService } from './unite.service'

const baseUrl = 'http://localhost:8080/api/v1/details_realisations'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class Details_realisationService {

  constructor(

    private realisationTravauxService : RealisationTravauxService,
    private typeDeTravauxService : TypeDeTravauxService,
    private uniteService : UniteService,
    private http: HttpClient
  ) { }

  getDetails_realisationById(id : string): Observable<Details_realisation> {
    return this.http.get<Details_realisation>(`${baseUrl}/${id}`)
  }

  getDetails_realisations(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getDetails_realisationsPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createDetails_realisation(details_realisation: Details_realisation): Observable<Details_realisation> {
      const {drId, ...details_realisationWithoutId} = details_realisation;

      const drIdRealisationTravaux$ = this.realisationTravauxService.getRealisationTravauxById(details_realisationWithoutId.drIdRealisationTravaux);
      const drIdTypeTravaux$ = this.typeDeTravauxService.getTypeDeTravauxById(details_realisationWithoutId.drIdTypeTravaux);
      const drIdUnite$ = this.uniteService.getUniteById(details_realisationWithoutId.drIdUnite);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          drIdRealisationTravaux$.pipe(
              tap((data) => {
                  details_realisationWithoutId.realisationTravaux = data;
              }),
          ),
          drIdTypeTravaux$.pipe(
              tap((data) => {
                  details_realisationWithoutId.typeDeTravaux = data;
              }),
          ),
          drIdUnite$.pipe(
              tap((data) => {
                  details_realisationWithoutId.unite = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(details_realisationWithoutId);
              return this.http.post<Details_realisation>(baseUrl, details_realisationWithoutId);
          })
      );

  }

  updateDetails_realisation(id: string, details_realisation: Details_realisation): Observable<Details_realisation> {

      const drIdRealisationTravaux$ = this.realisationTravauxService.getRealisationTravauxById(details_realisation.drIdRealisationTravaux);
      const drIdTypeTravaux$ = this.typeDeTravauxService.getTypeDeTravauxById(details_realisation.drIdTypeTravaux);
      const drIdUnite$ = this.uniteService.getUniteById(details_realisation.drIdUnite);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          drIdRealisationTravaux$.pipe(
              tap((data) => {
                  details_realisation.realisationTravaux = data;
              }),
          ),
          drIdTypeTravaux$.pipe(
              tap((data) => {
                  details_realisation.typeDeTravaux = data;
              }),
          ),
          drIdUnite$.pipe(
              tap((data) => {
                  details_realisation.unite = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(details_realisation);
              return this.http.post<Details_realisation>(baseUrl, details_realisation);
          })
      );

  }

   deleteDetails_realisation(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
