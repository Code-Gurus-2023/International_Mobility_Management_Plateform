export class Comment {
    public idCmt?: number
    public upVoteCmt?: number
    public contentCmt?: String
    public username?: String
    public creationDate?: Date

    constructor(contentCmt: string) {
        this.contentCmt = contentCmt;
    }
}