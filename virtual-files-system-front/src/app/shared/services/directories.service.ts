import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Directory } from '../interfaces/directory.interface';
import { RootDirectoryPayload } from '../interfaces/payload.root-directory.interface';

@Injectable({
  providedIn: 'root'
})
export class DirectoriesService {
    httpClient = inject(HttpClient);

    getAll() {
      return this.httpClient.get<Directory[]>("/api/directory/find-all");
    };

    findById(id: number) {
      return this.httpClient.get<Directory>(`/api/directory/find?id=${id}`);
    };

    saveRootDirectory(payload: RootDirectoryPayload) {
      return this.httpClient.post<Directory>("/api/directory/save", payload);
    };
}
