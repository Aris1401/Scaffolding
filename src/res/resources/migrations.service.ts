import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Migrations } from './migrations.model';

const baseUrl = 'http://localhost:8080/api/v1/migrationss'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class MigrationsService {

  constructor(

    private http: HttpClient
  ) { }

  getMigrationsById(id : string): Observable<Migrations> {
    return this.http.get<Migrations>(`${baseUrl}/${id}`)
  }

  getMigrationss(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getMigrationssPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createMigrations(migrations: Migrations): Observable<Migrations> {
      const {id, ...migrationsWithoutId} = migrations;

      return this.http.post<Migrations>(baseUrl, migrationsWithoutId);
  }

  updateMigrations(id: string, migrations: Migrations): Observable<Migrations> {

    return this.http.put<Migrations>(`${baseUrl}/${id}`, migrations);
  }

   deleteMigrations(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
