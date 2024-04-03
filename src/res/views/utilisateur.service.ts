import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Utilisateur } from './utilisateur.model';

const baseUrl = 'http://localhost:8080/api/v1/utilisateurs'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {

  constructor(

    private http: HttpClient
  ) { }

  getUtilisateurById(id : string): Observable<Utilisateur> {
    return this.http.get<Utilisateur>(`${baseUrl}/${id}`)
  }

  getUtilisateurs(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getUtilisateursPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createUtilisateur(utilisateur: Utilisateur): Observable<Utilisateur> {
      const {id, ...utilisateurWithoutId} = utilisateur;

      return this.http.post<Utilisateur>(baseUrl, utilisateurWithoutId);
  }

  updateUtilisateur(id: string, utilisateur: Utilisateur): Observable<Utilisateur> {

    return this.http.put<Utilisateur>(`${baseUrl}/${id}`, utilisateur);
  }

   deleteUtilisateur(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
