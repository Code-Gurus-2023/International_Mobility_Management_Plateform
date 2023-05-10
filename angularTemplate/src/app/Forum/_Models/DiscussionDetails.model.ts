import { Comment } from "./Comment.model"


export class DiscussionDetails {
    public id?: number
    public question?: String
    public views?: number
    public comments?: Comment[]
}