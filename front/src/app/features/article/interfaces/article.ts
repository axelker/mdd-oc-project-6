export interface Article {
    id:number;
    name:string
    description: string;
    ownerId: number;
    themeId: number;
    createdAt: Date;
}