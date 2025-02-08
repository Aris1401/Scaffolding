import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Import_type_maison_travaux } from './import_type_maison_travaux.model';

const baseUrl = 'http://localhost:8080/api/v1/import_type_maison_travauxs'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class Import_type_maison_travauxService {

  constructor(

    private http: HttpClient
  ) { }

  getImport_type_maison_travauxById(id : string): Observable<Import_type_maison_travaux> {
    return this.http.get<Import_type_maison_travaux>(`${baseUrl}/${id}`)
  }

  getImport_type_maison_travauxs(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getImport_type_maison_travauxsPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createImport_type_maison_travaux(import_type_maison_travaux: Import_type_maison_travaux): Observable<Import_type_maison_travaux> {
      const {id, ...import_type_maison_travauxWithoutId} = import_type_maison_travaux;

      return this.http.post<Import_type_maison_travaux>(baseUrl, import_type_maison_travauxWithoutId);
  }

  updateImport_type_maison_travaux(id: string, import_type_maison_travaux: Import_type_maison_travaux): Observable<Import_type_maison_travaux> {

    return this.http.put<Import_type_maison_travaux>(`${baseUrl}/${id}`, import_type_maison_travaux);
  }

   deleteImport_type_maison_travaux(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
