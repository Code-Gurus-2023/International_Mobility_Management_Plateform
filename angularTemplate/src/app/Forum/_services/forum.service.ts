import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiPathService } from './api-path.service';
import { Observable } from 'rxjs';
import { Discussion } from '../_Models/Discussion.model';
import { DiscussionDetails } from '../_Models/DiscussionDetails.model';
import { Comment } from '../_Models/Comment.model';

const API_URL = 'api/forum';

@Injectable({
  providedIn: 'root'
})
export class ForumService {

  constructor(private http: HttpClient, private apiPathService: ApiPathService) { }

  getDiscussions(): Observable<Discussion[]> {
      return this.http.get<Discussion[]>(this.apiPathService.path + API_URL)
  }

  addDiscussion(discussion: Discussion){
    return this.http.post<Discussion>(this.apiPathService.path + API_URL + '/addDiscussion', discussion)
  }

  getDiscussionDetails(id: number): Observable<DiscussionDetails> {
    return this.http.get<DiscussionDetails>(this.apiPathService.path + API_URL + '/getDiscussionDetails/' + id)
    //return this.http.get<DiscussionDetails>('http://localhost:8081/espritmobility/api/forum/getDiscussionDetails/' + id)
  }

  addComment(comment: Comment, idCmt: number) {
    return this.http.put<Comment>(this.apiPathService.path + API_URL + '/addComment/' + idCmt, comment)
  }

  getMostViewedDiscussions() {
    return this.http.get<Discussion[]>(this.apiPathService.path + API_URL + '/getMostViewedDiscussions')
  }

  getMostResponededDiscussions() {
    return this.http.get<Discussion[]>(this.apiPathService.path + API_URL + '/getMostRepliedDiscussions')
  }
}
