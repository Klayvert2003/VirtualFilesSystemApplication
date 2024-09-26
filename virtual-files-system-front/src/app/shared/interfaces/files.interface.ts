import { Directory } from "./directory.interface";

export interface File {
    id: number;
    fileName: string;
    createdAt?: string;
    directory: Directory | null
}

/*
"files": [
    {
        "id": 1,
        "fileName": "nome_arquivo.extensao",
        "createdAt": "YYYY-MM-DD"
    }
]
*/