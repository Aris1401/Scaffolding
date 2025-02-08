import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Cache } from './cache.model';

const baseUrl = 'http://localhost:8080/api/v1/caches'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class CacheService {

  constructor(

    private http: HttpClient
  ) { }

  getCacheById(id : string): Observable<Cache> {
    return this.http.get<Cache>(`${baseUrl}/${id}`)
  }

  getCaches(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getCachesPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createCache(cache: Cache): Observable<Cache> {
      const {key, ...cacheWithoutId} = cache;

      return this.http.post<Cache>(baseUrl, cacheWithoutId);
  }

  updateCache(id: string, cache: Cache): Observable<Cache> {

    return this.http.put<Cache>(`${baseUrl}/${id}`, cache);
  }

   deleteCache(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
