import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Utilisateur } from './utilisateur.model';

import { Genre } from './genre.model'
import { GenreService } from './genre.service'
import { ProfilUtilisateur } from './profilUtilisateur.model'
import { ProfilUtilisateurService } from './profilUtilisateur.service'

const baseUrl = 'http://localhost:8080/api/v1/utilisateurs'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {

  constructor(

    private genreService : GenreService,
    private profilUtilisateurService : ProfilUtilisateurService,
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
      const {uId, ...utilisateurWithoutId} = utilisateur;

      const uIdGenre$ = this.genreService.getGenreById(utilisateurWithoutId.uIdGenre);
      const uIdProfilUtilisateur$ = this.profilUtilisateurService.getProfilUtilisateurById(utilisateurWithoutId.uIdProfilUtilisateur);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          uIdGenre$.pipe(
              tap((data) => {
                  utilisateurWithoutId.genre = data;
              }),
          ),
          uIdProfilUtilisateur$.pipe(
              tap((data) => {
                  utilisateurWithoutId.profilUtilisateur = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(utilisateurWithoutId);
              return this.http.post<Utilisateur>(baseUrl, utilisateurWithoutId);
          })
      );

  }

  updateUtilisateur(id: string, utilisateur: Utilisateur): Observable<Utilisateur> {

      const uIdGenre$ = this.genreService.getGenreById(utilisateur.uIdGenre);
      const uIdProfilUtilisateur$ = this.profilUtilisateurService.getProfilUtilisateurById(utilisateur.uIdProfilUtilisateur);

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

          uIdGenre$.pipe(
              tap((data) => {
                  utilisateur.genre = data;
              }),
          ),
          uIdProfilUtilisateur$.pipe(
              tap((data) => {
                  utilisateur.profilUtilisateur = data;
              }),
          ),
      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(utilisateur);
              return this.http.post<Utilisateur>(baseUrl, utilisateur);
          })
      );

  }

   deleteUtilisateur(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
