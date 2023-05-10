import { Component, OnInit } from '@angular/core';
import { Discussion } from '../_Models/Discussion.model';
import { ForumService } from '../_services/forum.service';

@Component({
  selector: 'app-forum-stats-responds',
  templateUrl: './forum-stats-responds.component.html',
  styleUrls: ['./forum-stats-responds.component.css']
})
export class ForumStatsRespondsComponent implements OnInit {

  discussions: Discussion[] = []
  constructor(private forumService: ForumService) { }

  ngOnInit(): void {
    this.get()
  }

  get() {
    return this.forumService.getMostResponededDiscussions().subscribe(
      (data) => {
        this.discussions = data
      }
    )
  }

}
