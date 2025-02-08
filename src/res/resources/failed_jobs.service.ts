import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Failed_jobs } from './failed_jobs.model';

const baseUrl = 'http://localhost:8080/api/v1/failed_jobss'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class Failed_jobsService {

  constructor(

    private http: HttpClient
  ) { }

  getFailed_jobsById(id : string): Observable<Failed_jobs> {
    return this.http.get<Failed_jobs>(`${baseUrl}/${id}`)
  }

  getFailed_jobss(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getFailed_jobssPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createFailed_jobs(failed_jobs: Failed_jobs): Observable<Failed_jobs> {
      const {id, ...failed_jobsWithoutId} = failed_jobs;

      return this.http.post<Failed_jobs>(baseUrl, failed_jobsWithoutId);
  }

  updateFailed_jobs(id: string, failed_jobs: Failed_jobs): Observable<Failed_jobs> {

    return this.http.put<Failed_jobs>(`${baseUrl}/${id}`, failed_jobs);
  }

   deleteFailed_jobs(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
