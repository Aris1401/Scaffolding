import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Cache_locks } from './cache_locks.model';

const baseUrl = 'http://localhost:8080/api/v1/cache_lockss'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class Cache_locksService {

  constructor(

    private http: HttpClient
  ) { }

  getCache_locksById(id : string): Observable<Cache_locks> {
    return this.http.get<Cache_locks>(`${baseUrl}/${id}`)
  }

  getCache_lockss(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getCache_lockssPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createCache_locks(cache_locks: Cache_locks): Observable<Cache_locks> {
      const {key, ...cache_locksWithoutId} = cache_locks;

      return this.http.post<Cache_locks>(baseUrl, cache_locksWithoutId);
  }

  updateCache_locks(id: string, cache_locks: Cache_locks): Observable<Cache_locks> {

    return this.http.put<Cache_locks>(`${baseUrl}/${id}`, cache_locks);
  }

   deleteCache_locks(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
