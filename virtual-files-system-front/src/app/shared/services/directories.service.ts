import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Directory } from '../interfaces/directory.interface';

@Injectable({
  providedIn: 'root'
})
export class DirectoriesService {
    httpClient = inject(HttpClient);

    getAll() {
      return this.httpClient.get<Directory[]>("/api/directory/find-all");
    };
}
