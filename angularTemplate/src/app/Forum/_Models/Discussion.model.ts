export class Discussion {
    public idDsc?: number
    public nameDsc?: string
    public viewsDsc?: number
    public nbrMessageDsc?: number
    public lastMessageDate?: Date
    public isActive?: boolean


    constructor(nameDsc: string) {
        this.nameDsc = nameDsc;
      }
}