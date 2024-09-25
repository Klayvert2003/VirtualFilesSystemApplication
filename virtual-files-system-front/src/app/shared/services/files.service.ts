import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { File } from '../interfaces/files.interface';
import { FilePayload } from '../interfaces/payload.file.interface';

@Injectable({
  providedIn: 'root'
})
export class FilesService {
    httpClient = inject(HttpClient);

    saveFile(payload: FilePayload) {
      return this.httpClient.post<File>("/api/file/save", payload);
    };
}
