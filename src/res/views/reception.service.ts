import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Reception } from './reception.model';

import { Commande } from './commande.model'
import { CommandeService } from './commande.service'

const baseUrl = 'http://localhost:8080/api/v1/receptions'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class ReceptionService {

  constructor(

    private commandeService : CommandeService,
    private http: HttpClient
  ) { }

  getReceptionById(id : string): Observable<Reception> {
    return this.http.get<Reception>(`${baseUrl}/${id}`)
  }

  getReceptions(): Observable<Reception[]> {
    return this.http.get<Reception[]>(baseUrl);
  }

  createReception(reception: Reception): Observable<Reception> {
      const {idReception, ...receptionWithoutId} = reception;

      const idCommande$ = this.commandeService.getCommandeById(receptionWithoutId.idCommande);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          idCommande$.pipe(
              tap((data) => {
                  receptionWithoutId.commande = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(receptionWithoutId);
              return this.http.post<Reception>(baseUrl, receptionWithoutId);
          })
      );
  }

  updateReception(id: string, reception: Reception): Observable<Reception> {

      const idCommande$ = this.commandeService.getCommandeById(reception.idCommande);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          idCommande$.pipe(
              tap((data) => {
                  reception.commande = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(reception);
              return this.http.post<Reception>(baseUrl, reception);
          })
      );

    return this.http.put<Reception>(`${baseUrl}/${id}`, reception);
  }

   deleteReception(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
