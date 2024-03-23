import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Fournisseur } from './fournisseur.model';

const baseUrl = 'http://localhost:8080/api/v1/fournisseurs'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class FournisseurService {

  constructor(

    private http: HttpClient
  ) { }

  getFournisseurById(id : string): Observable<Fournisseur> {
    return this.http.get<Fournisseur>(`${baseUrl}/${id}`)
  }

  getFournisseurs(): Observable<Fournisseur[]> {
    return this.http.get<Fournisseur[]>(baseUrl);
  }

  createFournisseur(fournisseur: Fournisseur): Observable<Fournisseur> {
      const {idFournisseur, ...fournisseurWithoutId} = fournisseur;

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(fournisseurWithoutId);
              return this.http.post<Fournisseur>(baseUrl, fournisseurWithoutId);
          })
      );
  }

  updateFournisseur(id: string, fournisseur: Fournisseur): Observable<Fournisseur> {

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(fournisseur);
              return this.http.post<Fournisseur>(baseUrl, fournisseur);
          })
      );

    return this.http.put<Fournisseur>(`${baseUrl}/${id}`, fournisseur);
  }

   deleteFournisseur(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
