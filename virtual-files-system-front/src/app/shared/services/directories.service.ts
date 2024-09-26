import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Directory } from '../interfaces/directory.interface';
import { RootDirectoryPayload, SubDirectoryPayload } from '../interfaces/payload.root-directory.interface';

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

    saveSubDirectory(payload: SubDirectoryPayload) {
      return this.httpClient.post<Directory>("/api/directory/save", payload);
    };

    deleteDirectory(directoryId: number) {
      return this.httpClient.delete(`/api/directory/delete/${directoryId}`);
    };
}
