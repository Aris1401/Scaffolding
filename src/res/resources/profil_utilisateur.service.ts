import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Profil_utilisateur } from './profil_utilisateur.model';

const baseUrl = 'http://localhost:8080/api/v1/profil_utilisateurs'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class Profil_utilisateurService {

  constructor(

    private http: HttpClient
  ) { }

  getProfil_utilisateurById(id : string): Observable<Profil_utilisateur> {
    return this.http.get<Profil_utilisateur>(`${baseUrl}/${id}`)
  }

  getProfil_utilisateurs(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getProfil_utilisateursPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createProfil_utilisateur(profil_utilisateur: Profil_utilisateur): Observable<Profil_utilisateur> {
      const {puId, ...profil_utilisateurWithoutId} = profil_utilisateur;

      return this.http.post<Profil_utilisateur>(baseUrl, profil_utilisateurWithoutId);
  }

  updateProfil_utilisateur(id: string, profil_utilisateur: Profil_utilisateur): Observable<Profil_utilisateur> {

    return this.http.put<Profil_utilisateur>(`${baseUrl}/${id}`, profil_utilisateur);
  }

   deleteProfil_utilisateur(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
