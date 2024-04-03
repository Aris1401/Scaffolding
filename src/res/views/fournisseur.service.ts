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

  getFournisseurs(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getFournisseursPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createFournisseur(fournisseur: Fournisseur): Observable<Fournisseur> {
      const {idFournisseur, ...fournisseurWithoutId} = fournisseur;

      return this.http.post<Fournisseur>(baseUrl, fournisseurWithoutId);
  }

  updateFournisseur(id: string, fournisseur: Fournisseur): Observable<Fournisseur> {

    return this.http.put<Fournisseur>(`${baseUrl}/${id}`, fournisseur);
  }

   deleteFournisseur(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
