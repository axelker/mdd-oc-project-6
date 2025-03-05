import { SummaryResponse } from "../../../shared/interfaces/SummaryResponse";

export interface Article {
    id:number;
    name:string
    description: string;
    owner: SummaryResponse;
    theme: SummaryResponse;
    createdAt: Date;
}