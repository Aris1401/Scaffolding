import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Realisation_travaux } from './realisation_travaux.model';

import { Devis } from './devis.model'
import { DevisService } from './devis.service'
import { TypeDeFinition } from './typeDeFinition.model'
import { TypeDeFinitionService } from './typeDeFinition.service'
import { TypeDeMaison } from './typeDeMaison.model'
import { TypeDeMaisonService } from './typeDeMaison.service'
import { Utilisateur } from './utilisateur.model'
import { UtilisateurService } from './utilisateur.service'

const baseUrl = 'http://localhost:8080/api/v1/realisation_travauxs'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class Realisation_travauxService {

  constructor(

    private devisService : DevisService,
    private typeDeFinitionService : TypeDeFinitionService,
    private typeDeMaisonService : TypeDeMaisonService,
    private utilisateurService : UtilisateurService,
    private http: HttpClient
  ) { }

  getRealisation_travauxById(id : string): Observable<Realisation_travaux> {
    return this.http.get<Realisation_travaux>(`${baseUrl}/${id}`)
  }

  getRealisation_travauxs(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getRealisation_travauxsPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createRealisation_travaux(realisation_travaux: Realisation_travaux): Observable<Realisation_travaux> {
      const {rtId, ...realisation_travauxWithoutId} = realisation_travaux;

      const rtIdDevis$ = this.devisService.getDevisById(realisation_travauxWithoutId.rtIdDevis);
      const rtIdTypeDeFinition$ = this.typeDeFinitionService.getTypeDeFinitionById(realisation_travauxWithoutId.rtIdTypeDeFinition);
      const rtIdTypeDeMaison$ = this.typeDeMaisonService.getTypeDeMaisonById(realisation_travauxWithoutId.rtIdTypeDeMaison);
      const rtIdUtilisateur$ = this.utilisateurService.getUtilisateurById(realisation_travauxWithoutId.rtIdUtilisateur);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          rtIdDevis$.pipe(
              tap((data) => {
                  realisation_travauxWithoutId.devis = data;
              }),
          ),
          rtIdTypeDeFinition$.pipe(
              tap((data) => {
                  realisation_travauxWithoutId.typeDeFinition = data;
              }),
          ),
          rtIdTypeDeMaison$.pipe(
              tap((data) => {
                  realisation_travauxWithoutId.typeDeMaison = data;
              }),
          ),
          rtIdUtilisateur$.pipe(
              tap((data) => {
                  realisation_travauxWithoutId.utilisateur = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(realisation_travauxWithoutId);
              return this.http.post<Realisation_travaux>(baseUrl, realisation_travauxWithoutId);
          })
      );

  }

  updateRealisation_travaux(id: string, realisation_travaux: Realisation_travaux): Observable<Realisation_travaux> {

      const rtIdDevis$ = this.devisService.getDevisById(realisation_travaux.rtIdDevis);
      const rtIdTypeDeFinition$ = this.typeDeFinitionService.getTypeDeFinitionById(realisation_travaux.rtIdTypeDeFinition);
      const rtIdTypeDeMaison$ = this.typeDeMaisonService.getTypeDeMaisonById(realisation_travaux.rtIdTypeDeMaison);
      const rtIdUtilisateur$ = this.utilisateurService.getUtilisateurById(realisation_travaux.rtIdUtilisateur);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          rtIdDevis$.pipe(
              tap((data) => {
                  realisation_travaux.devis = data;
              }),
          ),
          rtIdTypeDeFinition$.pipe(
              tap((data) => {
                  realisation_travaux.typeDeFinition = data;
              }),
          ),
          rtIdTypeDeMaison$.pipe(
              tap((data) => {
                  realisation_travaux.typeDeMaison = data;
              }),
          ),
          rtIdUtilisateur$.pipe(
              tap((data) => {
                  realisation_travaux.utilisateur = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(realisation_travaux);
              return this.http.post<Realisation_travaux>(baseUrl, realisation_travaux);
          })
      );

  }

   deleteRealisation_travaux(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
