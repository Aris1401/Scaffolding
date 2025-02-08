import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Genre } from './genre.model';

const baseUrl = 'http://localhost:8080/api/v1/genres'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  constructor(

    private http: HttpClient
  ) { }

  getGenreById(id : string): Observable<Genre> {
    return this.http.get<Genre>(`${baseUrl}/${id}`)
  }

  getGenres(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getGenresPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createGenre(genre: Genre): Observable<Genre> {
      const {gId, ...genreWithoutId} = genre;

      return this.http.post<Genre>(baseUrl, genreWithoutId);
  }

  updateGenre(id: string, genre: Genre): Observable<Genre> {

    return this.http.put<Genre>(`${baseUrl}/${id}`, genre);
  }

   deleteGenre(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
