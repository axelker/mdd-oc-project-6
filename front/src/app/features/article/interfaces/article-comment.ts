import { SummaryResponse } from "../../../shared/interfaces/SummaryResponse";

export interface ArticleComment {
    id:number;
    articleId:number;
    owner:SummaryResponse;
    message:string;
}