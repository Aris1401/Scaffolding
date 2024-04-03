import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Commande } from './commande.model';

import { Fournisseur } from './fournisseur.model'
import { FournisseurService } from './fournisseur.service'
import { Immobilier } from './immobilier.model'
import { ImmobilierService } from './immobilier.service'

const baseUrl = 'http://localhost:8080/api/v1/commandes'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class CommandeService {

  constructor(

    private fournisseurService : FournisseurService,
    private immobilierService : ImmobilierService,
    private http: HttpClient
  ) { }

  getCommandeById(id : string): Observable<Commande> {
    return this.http.get<Commande>(`${baseUrl}/${id}`)
  }

  getCommandes(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getCommandesPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createCommande(commande: Commande): Observable<Commande> {
      const {idCommande, ...commandeWithoutId} = commande;

      const idFournisseur$ = this.fournisseurService.getFournisseurById(commandeWithoutId.idFournisseur);
      const idImmobilier$ = this.immobilierService.getImmobilierById(commandeWithoutId.idImmobilier);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          idFournisseur$.pipe(
              tap((data) => {
                  commandeWithoutId.fournisseur = data;
              }),
          ),
          idImmobilier$.pipe(
              tap((data) => {
                  commandeWithoutId.immobilier = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(commandeWithoutId);
              return this.http.post<Commande>(baseUrl, commandeWithoutId);
          })
      );

  }

  updateCommande(id: string, commande: Commande): Observable<Commande> {

      const idFournisseur$ = this.fournisseurService.getFournisseurById(commande.idFournisseur);
      const idImmobilier$ = this.immobilierService.getImmobilierById(commande.idImmobilier);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          idFournisseur$.pipe(
              tap((data) => {
                  commande.fournisseur = data;
              }),
          ),
          idImmobilier$.pipe(
              tap((data) => {
                  commande.immobilier = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(commande);
              return this.http.post<Commande>(baseUrl, commande);
          })
      );

  }

   deleteCommande(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
