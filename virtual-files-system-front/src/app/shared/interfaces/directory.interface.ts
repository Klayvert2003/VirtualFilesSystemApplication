import { File } from "./files.interface";

export interface Directory {
    directoryId: number;
    directoryName: string;
    createdAt: string;
    parentDirectory: Directory | null;
    subDirectories: Directory[];
    files: File[];
}


/*
[
    {
        "directoryId": 1,
        "directoryName": "nome_diretorio",
        "createdAt": "YYYY-MM-DD",
        "parentDirectory": null,
        "subDirectories": [
            {
                "directoryId": 1,
                "directoryName": "nome_diretorio",
                "createdAt": "YYYY-MM-DD",
                "parentDirectory": null,
                "subDirectories": [],
                "files": [
                    {
                        "id": 1,
                        "fileName": "file.extension",
                        "createdAt": "YYYY-MM-DD"
                    }
                ]
            }
        ],
        "files": [
            {
                "id": 1,
                "fileName": "file.extension",
                "createdAt": "YYYY-MM-DD"
            }
        ]
    }
]
*/