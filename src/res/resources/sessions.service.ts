import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Sessions } from './sessions.model';

const baseUrl = 'http://localhost:8080/api/v1/sessionss'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class SessionsService {

  constructor(

    private http: HttpClient
  ) { }

  getSessionsById(id : string): Observable<Sessions> {
    return this.http.get<Sessions>(`${baseUrl}/${id}`)
  }

  getSessionss(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getSessionssPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createSessions(sessions: Sessions): Observable<Sessions> {
      const {id, ...sessionsWithoutId} = sessions;

      return this.http.post<Sessions>(baseUrl, sessionsWithoutId);
  }

  updateSessions(id: string, sessions: Sessions): Observable<Sessions> {

    return this.http.put<Sessions>(`${baseUrl}/${id}`, sessions);
  }

   deleteSessions(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
