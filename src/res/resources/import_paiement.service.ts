import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Import_paiement } from './import_paiement.model';

const baseUrl = 'http://localhost:8080/api/v1/import_paiements'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class Import_paiementService {

  constructor(

    private http: HttpClient
  ) { }

  getImport_paiementById(id : string): Observable<Import_paiement> {
    return this.http.get<Import_paiement>(`${baseUrl}/${id}`)
  }

  getImport_paiements(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getImport_paiementsPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createImport_paiement(import_paiement: Import_paiement): Observable<Import_paiement> {
      const {id, ...import_paiementWithoutId} = import_paiement;

      return this.http.post<Import_paiement>(baseUrl, import_paiementWithoutId);
  }

  updateImport_paiement(id: string, import_paiement: Import_paiement): Observable<Import_paiement> {

    return this.http.put<Import_paiement>(`${baseUrl}/${id}`, import_paiement);
  }

   deleteImport_paiement(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
