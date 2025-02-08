import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Details_devis } from './details_devis.model';

import { Devis } from './devis.model'
import { DevisService } from './devis.service'
import { TypeDeTravaux } from './typeDeTravaux.model'
import { TypeDeTravauxService } from './typeDeTravaux.service'
import { Unite } from './unite.model'
import { UniteService } from './unite.service'

const baseUrl = 'http://localhost:8080/api/v1/details_deviss'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class Details_devisService {

  constructor(

    private devisService : DevisService,
    private typeDeTravauxService : TypeDeTravauxService,
    private uniteService : UniteService,
    private http: HttpClient
  ) { }

  getDetails_devisById(id : string): Observable<Details_devis> {
    return this.http.get<Details_devis>(`${baseUrl}/${id}`)
  }

  getDetails_deviss(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getDetails_devissPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createDetails_devis(details_devis: Details_devis): Observable<Details_devis> {
      const {ddId, ...details_devisWithoutId} = details_devis;

      const ddIdDevis$ = this.devisService.getDevisById(details_devisWithoutId.ddIdDevis);
      const ddIdTypeTravaux$ = this.typeDeTravauxService.getTypeDeTravauxById(details_devisWithoutId.ddIdTypeTravaux);
      const ddIdUnite$ = this.uniteService.getUniteById(details_devisWithoutId.ddIdUnite);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          ddIdDevis$.pipe(
              tap((data) => {
                  details_devisWithoutId.devis = data;
              }),
          ),
          ddIdTypeTravaux$.pipe(
              tap((data) => {
                  details_devisWithoutId.typeDeTravaux = data;
              }),
          ),
          ddIdUnite$.pipe(
              tap((data) => {
                  details_devisWithoutId.unite = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(details_devisWithoutId);
              return this.http.post<Details_devis>(baseUrl, details_devisWithoutId);
          })
      );

  }

  updateDetails_devis(id: string, details_devis: Details_devis): Observable<Details_devis> {

      const ddIdDevis$ = this.devisService.getDevisById(details_devis.ddIdDevis);
      const ddIdTypeTravaux$ = this.typeDeTravauxService.getTypeDeTravauxById(details_devis.ddIdTypeTravaux);
      const ddIdUnite$ = this.uniteService.getUniteById(details_devis.ddIdUnite);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          ddIdDevis$.pipe(
              tap((data) => {
                  details_devis.devis = data;
              }),
          ),
          ddIdTypeTravaux$.pipe(
              tap((data) => {
                  details_devis.typeDeTravaux = data;
              }),
          ),
          ddIdUnite$.pipe(
              tap((data) => {
                  details_devis.unite = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(details_devis);
              return this.http.post<Details_devis>(baseUrl, details_devis);
          })
      );

  }

   deleteDetails_devis(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
