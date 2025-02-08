import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Job_batches } from './job_batches.model';

const baseUrl = 'http://localhost:8080/api/v1/job_batchess'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class Job_batchesService {

  constructor(

    private http: HttpClient
  ) { }

  getJob_batchesById(id : string): Observable<Job_batches> {
    return this.http.get<Job_batches>(`${baseUrl}/${id}`)
  }

  getJob_batchess(): Observable<any> {
    return this.http.get<any>(baseUrl);
  }

  getJob_batchessPages(page : number) : Observable<any> {
        return this.http.get<any>(`${baseUrl}/p/${page}`)
  }

  createJob_batches(job_batches: Job_batches): Observable<Job_batches> {
      const {id, ...job_batchesWithoutId} = job_batches;

      return this.http.post<Job_batches>(baseUrl, job_batchesWithoutId);
  }

  updateJob_batches(id: string, job_batches: Job_batches): Observable<Job_batches> {

    return this.http.put<Job_batches>(`${baseUrl}/${id}`, job_batches);
  }

   deleteJob_batches(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
