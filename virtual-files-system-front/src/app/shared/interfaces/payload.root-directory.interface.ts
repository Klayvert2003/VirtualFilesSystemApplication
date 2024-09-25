import { Directory } from "./directory.interface";

export type RootDirectoryPayload = Omit<Directory, 'directoryId' | 'createdAt' | 'parentDirectory' | 'subDirectories' | 'files'>

export type SubDirectoryPayload = Omit<Directory, 'directoryId' | 'createdAt' | 'subDirectories' | 'files'>