import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiPathService } from './api-path.service';
import { Discussion } from 'src/app/Forum/_Models/Discussion.model';
import { Observable } from 'rxjs';

const API_URL = 'api/forum';

@Injectable({
  providedIn: 'root'
})
export class ForumService {

  constructor(private http: HttpClient, private apiPathService: ApiPathService) { }

  getDiscussions(): Observable<Discussion[]> {
      return this.http.get<Discussion[]>(this.apiPathService.path + API_URL)
  }
}
