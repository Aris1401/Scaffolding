import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { tap, switchMap } from 'rxjs/operators';
import { Configuration } from './configuration.model';

const baseUrl = 'http://localhost:8080/api/v1/configurations'; // Remplacer par votre URL d'API

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {

  constructor(

    private http: HttpClient
  ) { }

  getConfigurationById(id : string): Observable<Configuration> {
    return this.http.get<Configuration>(`${baseUrl}/${id}`)
  }

  getConfigurations(): Observable<Configuration[]> {
    return this.http.get<Configuration[]>(baseUrl);
  }

  createConfiguration(configuration: Configuration): Observable<Configuration> {
      const {id, ...configurationWithoutId} = configuration;

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(configurationWithoutId);
              return this.http.post<Configuration>(baseUrl, configurationWithoutId);
          })
      );
  }

  updateConfiguration(id: string, configuration: Configuration): Observable<Configuration> {

      // Combine all observables into a single observable using forkJoin
      return forkJoin([

      ]).pipe(
          // Switch to the HTTP POST request once all data is fetched and updated
          switchMap(() => {
              console.log(configuration);
              return this.http.post<Configuration>(baseUrl, configuration);
          })
      );

    return this.http.put<Configuration>(`${baseUrl}/${id}`, configuration);
  }

   deleteConfiguration(id : string): Observable<void> {
    return this.http.delete<void>(`${baseUrl}/${id}`);
  }
}
