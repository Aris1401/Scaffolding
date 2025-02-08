import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Import_realisation } from './import_realisation.model';

const baseUrl = 'http://localhost:8080/api/v1/import_realisations'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class Import_realisationService {

  constructor(

    private http: HttpClient
  ) { }

  getImport_realisationById(id : string): Observable<Import_realisation> {
    return this.http.get<Import_realisation>(`${baseUrl}/${id}`)
  }

  getImport_realisations(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getImport_realisationsPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createImport_realisation(import_realisation: Import_realisation): Observable<Import_realisation> {
      const {id, ...import_realisationWithoutId} = import_realisation;

      return this.http.post<Import_realisation>(baseUrl, import_realisationWithoutId);
  }

  updateImport_realisation(id: string, import_realisation: Import_realisation): Observable<Import_realisation> {

    return this.http.put<Import_realisation>(`${baseUrl}/${id}`, import_realisation);
  }

   deleteImport_realisation(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
