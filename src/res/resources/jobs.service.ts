import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Jobs } from './jobs.model';

const baseUrl = 'http://localhost:8080/api/v1/jobss'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class JobsService {

  constructor(

    private http: HttpClient
  ) { }

  getJobsById(id : string): Observable<Jobs> {
    return this.http.get<Jobs>(`${baseUrl}/${id}`)
  }

  getJobss(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getJobssPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createJobs(jobs: Jobs): Observable<Jobs> {
      const {id, ...jobsWithoutId} = jobs;

      return this.http.post<Jobs>(baseUrl, jobsWithoutId);
  }

  updateJobs(id: string, jobs: Jobs): Observable<Jobs> {

    return this.http.put<Jobs>(`${baseUrl}/${id}`, jobs);
  }

   deleteJobs(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
