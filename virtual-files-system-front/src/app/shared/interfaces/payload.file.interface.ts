import { File } from "./files.interface";

export type FilePayload = Omit<File, 'id' | 'createdAt'>