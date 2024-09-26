import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { File } from '../interfaces/files.interface';
import { FilePayload } from '../interfaces/payload.file.interface';

@Injectable({
  providedIn: 'root'
})
export class FilesService {
    httpClient = inject(HttpClient);

    findById(fileId: number) {
      return this.httpClient.get<File>(`/api/file/find?id=${fileId}`);
    }

    saveFile(payload: FilePayload) {
      return this.httpClient.post<File>("/api/file/save", payload);
    };

    updateFile(payload: File) {
      return this.httpClient.put<File>("/api/file/update", payload);
    };

    deleteFile(fileId: number) {
      return this.httpClient.delete(`/api/file/delete/${fileId}`);
    };
}
