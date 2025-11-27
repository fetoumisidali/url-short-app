export interface CreateUrlRequest{
    url:string
}
export interface ShortUrlResponse {
    shortUrl:string
}

export interface Url {
    id:string
    originalUrl:string,
    shortUrl:string
}

export interface DatabaseUrl{
    id:string,
    longUrl:string,
    shortId:string,
    createdAt: string
}